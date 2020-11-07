package com.example.rototium;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SyncThread extends Thread {


    SyncThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        //execRootCmd("adb shell dumpsys window | grep mCurrentFocus");
        //getAppVersionName()
        //String oldfile="/sdcard/BaiduMapAuto/bnav/log";
        //String newfile="/sdcard/BaiduMapAuto/bnav/sxf ";
        //copyFolder(oldfile,newfile);
       creatFile("/sdcard/BaiduMapAuto","zhangyan01");
    }

    /**
     * 在SD卡上创建目录
     */


        public void creatFile(String path, String fileName) {
        File file = new File(path, fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void copyFolder(String oldPath, String newPath) {
        try {
            File newFile = new File(newPath);
            if (!newFile.exists()) {
                if (!newFile.mkdirs()) {
                    Log.e("--Method--", "copyFolder: cannot create directory.");
                }
            }
            File oldFile = new File(oldPath);
            String[] files = oldFile.list();
            File temp;
            for (String file : files) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file);
                } else {
                    temp = new File(oldPath + File.separator + file);
                }

                if (temp.isDirectory()) {   //如果是子文件夹
                    copyFolder(oldPath + "/" + file, newPath + "/" + file);
                } else {
                    FileInputStream fileInputStream = null;
                    try {
                        fileInputStream = new FileInputStream(temp);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(newPath + "/" + temp.getName());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    byte[] buffer = new byte[1024];
                    int byteRead = 0;
                    while (true) {
                        try {
                            if (!((byteRead = fileInputStream.read(buffer)) != -1)) break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        fileOutputStream.write(buffer, 0, byteRead);
                    }
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



