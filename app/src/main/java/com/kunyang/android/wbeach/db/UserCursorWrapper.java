package com.kunyang.android.wbeach.db;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.kunyang.android.wbeach.Object.UserInfo;

import static com.kunyang.android.wbeach.db.UserDbSchema.*;

/**
 * Created by 坤阳 on 2017/12/15.
 */

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public UserInfo getUserInfo(){
        String id=getString(getColumnIndex(UserTable.Cols.ID));
        String userId=getString(getColumnIndex(UserTable.Cols.USERID));
        String userName=getString(getColumnIndex(UserTable.Cols.USERNAME));
        String token=getString(getColumnIndex(UserTable.Cols.TOKEN));
        String tokenSecret=getString(getColumnIndex(UserTable.Cols.TOKENSECRET));
        byte[] in = getBlob(getColumnIndex(UserTable.Cols.USERICON));
        Bitmap bmpout = BitmapFactory.decodeByteArray(in, 0, in.length);
        BitmapDrawable userIcon=new BitmapDrawable(bmpout);

        UserInfo user=new UserInfo();
        user.setId(id);
        user.setUserId(userId);
        user.setUserName(userName);
        user.setToken(token);
        user.setTokenSecret(tokenSecret);
        user.setUserIcon(userIcon);

        return user;
    }



}
