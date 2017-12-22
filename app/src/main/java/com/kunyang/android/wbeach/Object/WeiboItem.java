package com.kunyang.android.wbeach.Object;

import android.graphics.drawable.Drawable;

/**
 * Created by 坤阳 on 2017/12/16.
 */

public class WeiboItem {
    private Drawable friendImg;
    private String name;
    private String date;
    private String content;

    public Drawable getFriendImg() {
        return friendImg;
    }

    public WeiboItem setFriendImg(Drawable friendImg) {
        this.friendImg = friendImg;
        return this;
    }

    public String getName() {
        return name;
    }

    public WeiboItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getDate() {
        return date;
    }

    public WeiboItem setDate(String date) {
        this.date = date;
        return this;
    }

    public String getContent() {
        return content;
    }

    public WeiboItem setContent(String content) {
        this.content = content;
        return this;
    }
}
