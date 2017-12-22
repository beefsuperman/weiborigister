package com.kunyang.android.wbeach.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kunyang.android.wbeach.Object.UserInfo;

import java.util.ArrayList;
import java.util.List;

import static com.kunyang.android.wbeach.db.UserDbSchema.*;

/**
 * Created by 坤阳 on 2017/12/15.
 */

public class UserLab {
    private static UserLab sUserLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static UserLab get(Context context){
        if (sUserLab==null){
            sUserLab=new UserLab(context);
        }
        return sUserLab;
    }

    private UserLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase=new UserBaseHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(UserInfo user){
        ContentValues values=new ContentValues();
        values.put(UserTable.Cols.ID,user.getId().toString());
        values.put(UserTable.Cols.USERID,user.getUserId().toString());
        values.put(UserTable.Cols.USERNAME,user.getUserName().toString());
        values.put(UserTable.Cols.TOKEN,user.getToken().toString());
        values.put(UserTable.Cols.TOKENSECRET,user.getTokenSecret().toString());
        values.put(UserTable.Cols.USERICON,user.getUserIcon().toString());

        return values;
    }

    public void addUser(UserInfo user){
        ContentValues values=getContentValues(user);

        mDatabase.insert(UserTable.NAME,null,values);
    }

    public void updateUser(UserInfo user){
        String id=user.getId().toString();
        ContentValues values=getContentValues(user);

        mDatabase.update(UserTable.NAME,values, UserTable.Cols.ID+" = ? ",
                new String[]{id});
    }

    private UserCursorWrapper queryUsers(String whereClause,String[] whereArgs){
        Cursor cursor=mDatabase.query(
                UserTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new UserCursorWrapper(cursor);
    }

    public List<UserInfo> getUsers(){
        List<UserInfo> userInfos=new ArrayList<>();

        UserCursorWrapper cursor=queryUsers(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                userInfos.add(cursor.getUserInfo());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return userInfos;
    }

    public UserInfo getUser(String id){
        UserCursorWrapper cursor=queryUsers(
                UserTable.Cols.ID+" = ? ",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount()==0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getUserInfo();
        }finally {
            cursor.close();
        }
    }
}
