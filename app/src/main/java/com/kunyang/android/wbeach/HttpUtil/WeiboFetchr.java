package com.kunyang.android.wbeach.HttpUtil;

import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 坤阳 on 2017/12/16.
 */

public class WeiboFetchr {

    public static final String TAG="WeiboFetchr";

    public byte[] getUrlBytes(String urlSpec) throws IOException{
        URL url=new URL(urlSpec);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();

        try {
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            InputStream in=connection.getInputStream();

            if (connection.getResponseCode()!=HttpURLConnection.HTTP_OK){
                throw new IOException(connection.getResponseMessage()+": with "+urlSpec);
            }

            int bytesRead=0;
            byte[] buffer=new byte[1024];
            while ((bytesRead=in.read(buffer))>0){
                out.write(buffer,0,bytesRead);
            }
            out.close();
            return out.toByteArray();
        }finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException{
        return new String(getUrlBytes(urlSpec));
    }

    public void fetchItems(){
        try {
            String url=Uri.parse("https://api.weibo.com/2/statuses/home_timeline.json")
                    .buildUpon()
                    .appendQueryParameter("access_token","2.00rOWSMDZgd5ZE96b30ecb394NrpNB")
                    .build().toString();
            String jsonString=getUrlString(url);
            Log.i(TAG,"Received JSON: "+jsonString);
        }catch (IOException ioe){
            Log.e(TAG,"Failed to fetch items",ioe);
        }
    }
}
