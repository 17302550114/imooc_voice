package com.imooc.lib_audio.medialayer.core;

import android.media.MediaPlayer;
import android.widget.MediaController;

import java.io.IOException;

/**
 * 帶状态的MediaPlayer
 */
public class CusomediaPlayer extends MediaPlayer implements MediaPlayer.OnCompletionListener {

    private OnCompletionListener mCompleteListener;
    public enum Status {
        IDLE, INITIALIZED, STARTED, PAUSED, STOPPED, COMPLETED
    }

    private Status mState;

    public CusomediaPlayer() {
        super();
        mState = Status.IDLE;
        super.setOnCompletionListener(this);
    }

    @Override
    public void reset() {
        super.reset();
        mState = Status.IDLE;
    }

    @Override
    public void setDataSource(String path) throws IOException, IllegalArgumentException, IllegalStateException, SecurityException {
        super.setDataSource(path);
        mState = Status.INITIALIZED;
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();
        mState = Status.STARTED;
    }

    @Override
    public void pause() throws IllegalStateException {
        super.pause();
        mState = Status.PAUSED;
    }

    @Override
    public void stop() throws IllegalStateException {
        super.stop();
        mState = Status.STOPPED;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mState = Status.COMPLETED;
    }

    public Status getState(){
        return mState;
    }

    public boolean isComplete(){
        return mState == Status.COMPLETED;
    }

    public void setCompleteListener(OnCompletionListener listener){
        mCompleteListener = listener;

    }
}
