package com.example.admin.testsound;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * TODO: 1.¼��ʱ����������ڲ��ŵ���Ƶ����Ҫ��ͣ 2.��Ƶ�Ի��򳤶���Ҫ����¼��ʱ�䳤�̱仯
 */

public class MainActivity extends Activity {
	private AudioRecordButton btnRecord;
	private ListView voiceList;
	private ArrayAdapter<Recorder> mAdapter;
	private List<Recorder> mDatas = new ArrayList<Recorder>();

	private AnimationDrawable animation;
	private View voiceAnim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		voiceList = (ListView) findViewById(R.id.voiceList);
		btnRecord = (AudioRecordButton) findViewById(R.id.btnRecord);
		btnRecord.setAudioRecordFinishListener(new MyAudioRecordFinishListener());

		mAdapter = new VoiceListAdapter(this, mDatas);
		voiceList.setAdapter(mAdapter);
		voiceList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ���Ŷ���
				if (animation != null) {
					voiceAnim
							.setBackgroundResource(R.drawable.icon_voice_ripple);
					voiceAnim = null;
				}
				voiceAnim = view.findViewById(R.id.voiceAnim);
				voiceAnim.setBackgroundResource(R.drawable.anim_play_audio);
				animation = (AnimationDrawable) voiceAnim.getBackground();
				animation.start();
				// ������Ƶ
				MediaManager.playSound(mDatas.get(position).filePath,
						new MediaPlayer.OnCompletionListener() {
							@Override
							public void onCompletion(MediaPlayer mp) {
								voiceAnim
										.setBackgroundResource(R.drawable.icon_voice_ripple);
							}
						});

			}
		});
	}

	class MyAudioRecordFinishListener implements AudioRecordButton.AudioRecordFinishListener
	{

		@Override
		public void onFinish(float second, String filePath) {
			// TODO Auto-generated method stub
			Recorder recorder = new Recorder(second, filePath);
			mDatas.add(recorder);
			mAdapter.notifyDataSetChanged();
			voiceList.setSelection(mDatas.size() - 1);
		}

	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		MediaManager.release();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MediaManager.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MediaManager.resume();
	}

}
