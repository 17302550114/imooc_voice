package com.imooc.imooc_voice.api;

//import com.imooc.imooc_voice.model.discory.BaseRecommandModel;
//import com.imooc.imooc_voice.model.discory.BaseRecommandMoreModel;
//import com.imooc.imooc_voice.model.discory.RecommandBodyValue;
//import com.imooc.imooc_voice.model.friend.BaseFriendModel;
//import com.imooc.imooc_voice.model.user.User;

import com.imooc.imooc_voice.model.user.User;
import com.imooc.lib_network.okhttp.CommonOkHttpClient;
import com.imooc.lib_network.okhttp.listener.DisposeDataHandle;
import com.imooc.lib_network.okhttp.listener.DisposeDataListener;
import com.imooc.lib_network.okhttp.request.CommonRequest;
import com.imooc.lib_network.okhttp.request.RequestParams;

/**
 * 请求中心
 */
public class RequestCenter {

    static class HttpConstants {
//        private static final String ROOT_URL = "http://imooc.com/api";
        private static final String ROOT_URL = "http://39.97.122.129";

        /**
         * 首页请求接口
         */
        private static String HOME_RECOMMAND = ROOT_URL + "/module_voice/home_recommand";

        private static String HOME_RECOMMAND_MORE = ROOT_URL + "/module_voice/home_recommand_more";

        private static String HOME_FRIEND = ROOT_URL + "/module_voice/home_friend";

        /**
         * 登陆接口
         */
        public static String LOGIN = ROOT_URL + "/module_voice/login_phone";
    }

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.post(CommonRequest.createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    /**
     * 用户登陆请求
     */
    public static void login(DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("mb", "18734924592");
        params.put("pwd", "999999q");
        System.out.println("111");
        RequestCenter.postRequest(HttpConstants.LOGIN, params, listener, User.class);
    }
}
