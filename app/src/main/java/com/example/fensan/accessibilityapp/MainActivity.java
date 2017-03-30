package com.example.fensan.accessibilityapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TextView installTv;
    /**
     * 你得引导用户去设置界面吗，你不能让用户自己去找吧。
     */
    private static final Intent sSettingsIntent =
            new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
    //这里就假设想要安装的apk 是扇贝网 并且在sd卡根目录下面
    private static final String FILE_PATH="/mnt/sdcard/shanbeidanci6.0.000.apk";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        installTv=(TextView)this.findViewById(R.id.tv2);
        installTv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //调用安装器去安装我们的apk 一键安装开始啦，如果用户把那个服务打开了的话。
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(FILE_PATH)), "application/vnd.android.package-archive");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.setting) {
            startActivity(sSettingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}