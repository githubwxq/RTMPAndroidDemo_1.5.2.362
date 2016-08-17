package com.tencent.rtmp.demo.ui;

import android.app.Application;
import android.util.Log;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.rtmp.TXLivePusher;

public class DemoApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        int[] sdkVer = TXLivePusher.getSDKVersion(); //这里调用TXLivePlayer.getSDKVersion()也是可以的
        if (sdkVer != null && sdkVer.length >= 3) {
            if (sdkVer[0] > 0 && sdkVer[1] > 0) {
                //启动bugly组件，bugly组件为腾讯提供的用于crash上报和分析的开放组件，如果您不需要该组件，可以自行移除
                CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
                strategy.setAppVersion(String.format("%d.%d.%d",sdkVer[0],sdkVer[1],sdkVer[2]));
                CrashReport.initCrashReport(getApplicationContext(),strategy);

                Log.d("rtmpsdk","init crash report");
            }
        }

    }
}