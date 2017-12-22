package com.kunyang.android.wbeach.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.kunyang.android.wbeach.db.UserDbSchema.*;

/**
 * Created by 坤阳 on 2017/12/15.
 */

public class UserBaseHelper extends SQLiteOpenHelper {

    public static final int VERSION=1;
    public static final String DATABASE_NAME="userBase.db";

    public UserBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ UserTable.NAME+"("+
        " _id integer primary key autoincrement, "+
                UserTable.Cols.ID+","+
        UserTable.Cols.USERID+","+
        UserTable.Cols.USERNAME+","+
                UserTable.Cols.TOKEN+","+
                UserTable.Cols.TOKENSECRET+","+
                UserTable.Cols.USERICON+")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
