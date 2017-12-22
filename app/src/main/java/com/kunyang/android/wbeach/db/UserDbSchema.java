package com.kunyang.android.wbeach.db;

/**
 * Created by 坤阳 on 2017/12/15.
 */

public class UserDbSchema {
    public static final class UserTable{
        public static final String NAME="users";

        public static final class Cols{
            public static final String ID="id";
            public static final String USERID="userId";
            public static final String USERNAME="userName";
            public static final String TOKEN="token";
            public static final String TOKENSECRET="tokenSecret";
            public static final String USERICON="userIcon";
        }
    }
}
