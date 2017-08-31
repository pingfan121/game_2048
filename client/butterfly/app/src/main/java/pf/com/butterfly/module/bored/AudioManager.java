package pf.com.butterfly.module.bored;

import android.media.MediaRecorder;

import java.io.File;
import java.util.UUID;

public class AudioManager
{

	private MediaRecorder mediaRecorder;
	private String dir;
	private String currentFilePath;

	private static AudioManager audioInstance;

	public boolean isPrepared = false;

	private AudioManager(String dir) {
		this.dir = dir;
	}

	public static AudioManager getInstance(String dir) {
		if (audioInstance == null) {
			synchronized (AudioManager.class) {
				if (audioInstance == null) {
					audioInstance = new AudioManager(dir);
				}
			}
		}
		return audioInstance;
	}

	//准备录音
	public boolean prepareAudio()
	{
		try
		{
			isPrepared = false;

			File fileDir = new File(dir);

			if (!fileDir.exists())
				fileDir.mkdirs();

			String fileName = generateFileName();
			File file = new File(fileDir, fileName);

			currentFilePath = file.getAbsolutePath();
			mediaRecorder = new MediaRecorder();

			mediaRecorder.setOutputFile(file.getAbsolutePath());

			mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

			mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);

			mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

			mediaRecorder.prepare();
			mediaRecorder.start();

			isPrepared = true;



		} catch (Exception e)
		{

			e.printStackTrace();
		}

		return isPrepared;
	}

	//录音名字
	private String generateFileName() {
		return UUID.randomUUID().toString() + ".amr";
	}

	//得到音量等级
	public int getVoiceLevel(int maxLevel) {
		if (isPrepared) {
			try
			{
				return maxLevel * mediaRecorder.getMaxAmplitude() / 32768 + 1;
			}
			catch (Exception e)
			{
			}
		}
		return 1;
	}

	public void release()
	{
		if(isPrepared==true)
		{
			mediaRecorder.stop();
			mediaRecorder.release();
			mediaRecorder = null;
		}

	}

	//取消录音
	public void cancel()
	{
		release();

		if (currentFilePath != null) {
			File file = new File(currentFilePath);
			file.delete();
			currentFilePath = null;
		}
	}

	public String getCurrentPath() {
		return currentFilePath;
	}
}
