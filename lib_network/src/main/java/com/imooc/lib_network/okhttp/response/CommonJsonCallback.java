package com.imooc.lib_network.okhttp.response;

import android.os.Handler;
import android.os.Looper;


import com.imooc.lib_network.okhttp.exception.OkHttpException;
import com.imooc.lib_network.okhttp.listener.DisposeDataHandle;
import com.imooc.lib_network.okhttp.listener.DisposeDataListener;
import com.imooc.lib_network.okhttp.utils.ResponseEntityToModule;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author vision
 * @function 专门处理JSON的回调
 */
public class CommonJsonCallback implements Callback {

    /**
     * the logic layer exception, may alter in different app
     */
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    private DisposeDataListener mListener;
    public Class<?> mClass;

    private Handler mDeliveryHandler;

    public CommonJsonCallback(DisposeDataHandle handle) {
        mListener = handle.mListener;
        mClass = handle.mClass;
        mDeliveryHandler = new Handler(Looper.getMainLooper());
    }


    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }
    /**
     * 将其它线程的数据转发到UI线程
     */
    private void handleResponse(String result) {
        if (result == null || result.trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, null));
            return;
        }
        try {
            //不需要解析
            if (mClass == null) {
                mListener.onSuccess(result);
            } else {
                //解析为实体对象，可用gson，fastjson替换
                Object obj = ResponseEntityToModule.parseJsonToModule(result, mClass);
                if (obj != null) {
                    mListener.onSuccess(obj);
                } else {
                    mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
        }
    }

}