package com.example.rototium;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements  PermissionInterface {
    private int requestCode = 10000;
    Button openlog;
    Button logtodesktop;
    Button seeversion;
    Button logcattodesktop;
    Button openguiji;
    Button  anr;
    BufferedReader reader = null;
    String content = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openlog=findViewById(R.id.openlog);
        logcattodesktop=findViewById(R.id.getlog);
        seeversion=findViewById(R.id.seeversion);
        logcattodesktop=findViewById(R.id.logcat);
        openguiji=findViewById(R.id.openguiji);
        anr=findViewById(R.id.anr);
        seeversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String packageName="com.baidu.naviauto";
                PackageManager packageManager = getPackageManager();
                PackageInfo packInfo = null;
                try {
                    packInfo = packageManager.getPackageInfo(packageName,0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                String version = packInfo.versionName;
                Log.i("version",version);
                Toast.makeText(MainActivity.this,version,Toast.LENGTH_LONG).show();
            }
        });
      openlog.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent( );
              intent.setClassName(MainActivity.this,"com.baidu.naviauto/com.baidu.naviauto.NaviAutoActivity.class");
              startActivity(intent);
          }
      });


      logcattodesktop.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View view) {
             SyncThread thread=new SyncThread("one");
             thread.start();
          }
      });
      openguiji.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent( );
              intent.setClassName("com.navi.tracker","com.navi.tracker.NaviTrackerActivity");
              startActivity(intent);
          }
      });
      anr.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });
    }

    @Override
    public int getPermissionsRequestCode() {
        return 0;
    }

    @Override
    public void requestPermissionsSuccess() {

    }

    @Override
    public void requestPermissionsFail() {

    }
}


