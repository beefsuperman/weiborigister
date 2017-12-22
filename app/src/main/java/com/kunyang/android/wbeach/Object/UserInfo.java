package com.kunyang.android.wbeach.Object;

import android.graphics.drawable.Drawable;

/**
 * Created by 坤阳 on 2017/12/15.
 */

public class UserInfo {
    private String id;
    private String userId;
    private String userName;
    private String token;
    private String tokenSecret;
    private Drawable userIcon;


    public String getId() {
        return id;
    }

    public UserInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserInfo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getToken() {
        return token;
    }

    public UserInfo setToken(String token) {
        this.token = token;
        return this;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public UserInfo setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
        return this;
    }

    public Drawable getUserIcon() {
        return userIcon;
    }

    public UserInfo setUserIcon(Drawable userIcon) {
        this.userIcon = userIcon;
        return this;
    }
}
