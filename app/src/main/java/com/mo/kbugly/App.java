package com.mo.kbugly;

import android.app.Application;

import com.mo.kbuglylib.BuglyUtil;

/**
 * @ author：mo
 * @ data：2019/5/30:10:28
 * @ 功能：
 */
public class App extends Application {
    String key = "993b585d6a";

    @Override
    public void onCreate() {
        super.onCreate();
        BuglyUtil.InitBugly(getApplicationContext(), key, true);
    }


}
