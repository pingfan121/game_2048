package pf.com.butterfly.module.bored;

import android.Manifest;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import pf.com.butterfly.R;
import pf.com.butterfly.manager.DataManager;
import pf.com.butterfly.util.HDLog;
import pf.com.butterfly.util.PermissionManager;

public class AudioRecordButton extends AppCompatButton
{
	private static final int STATE_NORMAL = 1;
	private static final int STATE_RECORDING = 2;
	private static final int STATE_WANT_CANCEL = 3;

	private static final int DISTANCE_CANCEL_Y = 50;

	private int currentState = STATE_NORMAL;

	private boolean isRecording = false;  //是不是正在录音
	private boolean isReady = false;      //是不是准备好了
	private boolean downflag=false;        //按钮是不是按下状态


	private AudioRecordDialog dialogManager;
	private AudioManager audioManager;

	private float mTime;

	public interface RecordFinishCallback
	{
		void callback(float second, String filePath);
	}


	public AudioRecordButton(Context context) {
		this(context, null);
	}

	public AudioRecordButton(Context context, AttributeSet attrs) {
		super(context, attrs);

		dialogManager = new AudioRecordDialog(getContext());

		String dir = Environment.getExternalStorageDirectory()
				+ "/butterfly/chat_audios";

		audioManager = AudioManager.getInstance(dir);

		setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v)
			{
				if(DataManager.checkLogin()==false)
				{
					return false;
				}

				try
				{
					PermissionManager.applyForPermission(Manifest.permission.RECORD_AUDIO,new MyPermissionCallback());
				}
				catch (Exception ex)
				{
					HDLog.error(ex);
				}

				return false;
			}
		});
	}

	//录音权限申请之后的回调
	class MyPermissionCallback implements PermissionManager.PermissionCallback
	{

		@Override
		public void callback(String permissions, Boolean result)
		{
			if(downflag==true)
			{
				if (result == true)
				{
					isReady = true;

					boolean flag = audioManager.prepareAudio();

					if(flag==false)
					{
						HDLog.Toast("初始化录音设备失败");
					}
					else
					{
						//发送准备好了的消息
						mHanlder.sendEmptyMessage(MSG_AUDIO_PREPARED);
					}
				}
				else
				{
					HDLog.Toast("未同意录音权限,无法进行录音");

					//修改按钮状态
				}
			}
		}
	}


	public interface AudioRecordFinishListener
	{
		void onFinish(float second, String filePath);
	}

	private AudioRecordFinishListener audioRecordFinishListener;

	public void setAudioRecordFinishListener(AudioRecordFinishListener listener) {
		audioRecordFinishListener = listener;
	}

	private Runnable getVolumeRunnable = new Runnable() {

		@Override
		public void run() {

			while (isRecording) {
				try {
					Thread.sleep(100);
					mTime += 0.1f;
					mHanlder.sendEmptyMessage(MSG_VOLUME_CHAMGED);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	};

	private static final int MSG_AUDIO_PREPARED = 0x110;
	private static final int MSG_VOLUME_CHAMGED = 0x111;
	private static final int MSG_DIALOG_DISMISS = 0x112;

	private Handler mHanlder = new Handler() {
		public void handleMessage(android.os.Message msg) {

			try
			{
				switch (msg.what) {
					case MSG_AUDIO_PREPARED:
						dialogManager.showDialog();
						isRecording = true;


						new Thread(getVolumeRunnable).start();

						break;
					case MSG_VOLUME_CHAMGED:
						dialogManager.updateVolumeLevel(audioManager.getVoiceLevel(7));
						break;
					case MSG_DIALOG_DISMISS:
						dialogManager.dismissDialog();

						break;

					default:
						break;
				}
			}
			catch (Exception ex)
			{
				HDLog.error(ex);
			}
		};
	};


	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (action)
		{
			case MotionEvent.ACTION_DOWN:
			{
				changeState(STATE_RECORDING);
				downflag = true;

				break;
			}
			case MotionEvent.ACTION_MOVE:
			{
				if (wantCancel(x, y))
				{
					changeState(STATE_WANT_CANCEL);
				}
				break;
			}
			case MotionEvent.ACTION_UP:
			{
				downflag = false;

				if (isReady==false)
				{
					resetState();
					return super.onTouchEvent(event);
				}

				if (isRecording ==false || mTime < 0.6f)
				{
					dialogManager.stateLengthShort();
					audioManager.cancel();
					mHanlder.sendEmptyMessageDelayed(MSG_DIALOG_DISMISS, 1300);
				}
				else if (currentState == STATE_RECORDING)
				{
					dialogManager.dismissDialog();
					audioManager.release();

					// callbackToActivity
					if (audioRecordFinishListener != null)
					{
						audioRecordFinishListener.onFinish(mTime,
								audioManager.getCurrentPath());
					}

				} else if (currentState == STATE_WANT_CANCEL)
				{
					dialogManager.dismissDialog();
					audioManager.cancel();

				}
				resetState();
				break;
			}
			default:
				break;
		}
		return super.onTouchEvent(event);
	}

	private void resetState() {

		isRecording = false;
		isReady = false;
		changeState(STATE_NORMAL);
		mTime = 0;
	}

	private boolean wantCancel(int x, int y) {
		if (x < 0 || x > getWidth()) {
			return true;
		}

		if (y < -DISTANCE_CANCEL_Y || y > getHeight() + DISTANCE_CANCEL_Y) {
			return true;
		}
		return false;
	}

	private void changeState(int state) {

		if (currentState != state) {
			currentState = state;
			switch (state) {
			case STATE_NORMAL:
				setText(R.string.btn_recorder_normal);

				break;
			case STATE_RECORDING:
				setText(R.string.btn_recorder_recording);
				if (isRecording) {
					dialogManager.stateRecording();
				}
				break;
			case STATE_WANT_CANCEL:
				setText(R.string.btn_recorder_want_cancel);
				dialogManager.stateWantCancel();
				break;

			default:
				break;
			}
		}
	}

}
