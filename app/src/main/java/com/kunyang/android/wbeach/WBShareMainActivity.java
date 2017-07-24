package com.kunyang.android.wbeach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 坤阳 on 2017/7/23.
 */

public class WBShareMainActivity extends Activity{

    /** 微博分享按钮 */
    private Button mShareButton;

    /** 微博 ALL IN ONE 分享按钮 */
    private Button mShareAllInOneButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_main);
        initialize();
    }

    private void initialize() {
        /**
         * 初始化 UI
         */
        // 设置提示文本
        ((TextView)findViewById(R.id.register_app_to_weibo_hint)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView)findViewById(R.id.weibosdk_demo_support_api_level_hint)).setMovementMethod(LinkMovementMethod.getInstance());

        // 设置微博客户端相关信息
        //String installInfo = getString(isInstalledWeibo ? R.string.weibosdk_demo_has_installed_weibo : R.string.weibosdk_demo_has_installed_weibo);
        //((TextView)findViewById(R.id.weibosdk_demo_is_installed_weibo)).setText(installInfo);
        //((TextView)findViewById(R.id.weibosdk_demo_support_api_level)).setText("\t" + supportApiLevel);

        // 设置注册按钮对应回调
        ((Button) findViewById(R.id.register_app_to_weibo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 注册到新浪微博
                Toast.makeText(WBShareMainActivity.this,
                        R.string.weibosdk_demo_toast_register_app_to_weibo, Toast.LENGTH_LONG).show();

                mShareButton.setEnabled(true);
                mShareAllInOneButton.setEnabled(true);
            }
        });

        // 设置分享按钮对应回调
        mShareButton = (Button) findViewById(R.id.share_to_weibo);
        mShareButton.setEnabled(true);
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WBShareMainActivity.this, WBShareActivity.class);
                i.putExtra(WBShareActivity.KEY_SHARE_TYPE, WBShareActivity.SHARE_CLIENT);
                startActivity(i);
            }
        });

        // 设置ALL IN ONE分享按钮对应回调
        mShareAllInOneButton = (Button) findViewById(R.id.share_to_weibo_all_in_one);
        mShareAllInOneButton.setEnabled(false);
        mShareAllInOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WBShareMainActivity.this, WBShareActivity.class);
                i.putExtra(WBShareActivity.KEY_SHARE_TYPE, WBShareActivity.SHARE_ALL_IN_ONE);
                startActivity(i);
            }
        });
    }
}
