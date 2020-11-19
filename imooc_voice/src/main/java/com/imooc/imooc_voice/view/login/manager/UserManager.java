package com.imooc.imooc_voice.view.login.manager;

import com.imooc.imooc_voice.model.user.User;

/**
 * 登陆管理登陆用户信息
 * 单例模式
 */

public class UserManager {
    private static UserManager mInstance;
    private User mUser;

    public static UserManager getInstance() {
        if (mInstance == null) {
            synchronized (UserManager.class) {
                if (mInstance == null) {
                    mInstance = new UserManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 保存用户信息到内存
     */
    public void saveUser(User user) {
        mUser = user;
        saveLocal(user);
    }

    /**
     * 持久化用户信息
     *
     * @param user
     */
    private void saveLocal(User user) {

    }

    public User getUser() {
        return mUser;
    }

    /**
     * 从本地获取
     *
     * @return
     */
    public User getLocal() {
        return null;
    }

    /**
     * @return
     */
    public boolean hasLogined() {
        return getUser() == null ? false : true;
    }

    public void removeUser() {
        mUser = null;
    }

    private void removeLocal() {
    }

}
