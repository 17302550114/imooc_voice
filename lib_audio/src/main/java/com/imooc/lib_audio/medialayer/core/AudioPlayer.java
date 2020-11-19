package com.imooc.lib_audio.medialayer.core;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;


/**
 * 播放器事件源
 * 对外发送各种类型的事件
 */
public class AudioPlayer implements MediaPlayer.OnCompletionListener{

    private static final String TAG = "AudioPlayer";
    private static final int TIME_MSG = 0x01;
    private static final int TIME_INVAL = 100;

  @Override
  public void onCompletion(MediaPlayer mp) {

  }
  //真正负责播放的核心MediaPlayer子类

}


