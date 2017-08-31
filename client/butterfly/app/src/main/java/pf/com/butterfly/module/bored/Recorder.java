package pf.com.butterfly.module.bored;

import pf.com.butterfly.adapter.AdapterItemData;

/**
 * Created by admin on 2017/5/15.
 */


public class Recorder extends AdapterItemData
{
    float audioLength;
    String filePath;

    public Recorder(float audioLength, String filePath)
    {
        super();
        this.audioLength = audioLength;
        this.filePath = filePath;
    }

    public float getAudioLength() {
        return audioLength;
    }

    public void setAudioLength(float audioLength) {
        this.audioLength = audioLength;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}