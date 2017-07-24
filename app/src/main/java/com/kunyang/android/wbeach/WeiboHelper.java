package com.kunyang.android.wbeach;

import android.net.Uri;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by 坤阳 on 2017/7/24.
 */

public class WeiboHelper {
    private static final String TWITTER_SEARCH =
            "http://api.t.sina.com.cn/search.json?source=4187575515&q=";
    private static final int HTTP_STATUS_OK = 200;
    private static byte[] buff = new byte[1024];

    public static class ApiException extends Exception {
        public ApiException (String msg)
        {
            super (msg);
        }

        public ApiException (String msg, Throwable thr)
        {
            super (msg, thr);
        }
    }
    public static class ParseException extends Exception {
        public ParseException (String msg, Throwable thr)
        {
            super (msg, thr);
        }
    }

    protected static synchronized String downloadFromServer (String keyword)
            throws ApiException
    {
        String url = String.format(TWITTER_SEARCH, Uri.encode(keyword+"&callback=foo"));
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != HTTP_STATUS_OK){
                throw new ApiException("Invalid response from search.twitter.com" +
                        status.toString());
            }
            HttpEntity entity = response.getEntity();
            InputStream ist = entity.getContent();
            ByteArrayOutputStream content = new ByteArrayOutputStream();
            int readCount = 0;
            while ((readCount = ist.read(buff)) != -1)
                content.write(buff, 0, readCount);
            return new String (content.toByteArray());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ApiException("Problem using the API", e);
        }
    }
}
