package com.mo.kbuglylib;

import android.content.Context;
import android.text.TextUtils;

import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @ author：mo
 * @ data：2019/5/30:17:28
 * @ 功能：
 */
public class BuglyUtil {
    /**
     * 普通初始化
     *
     * @param context 上下文
     * @param key     注册时申请的APPID
     * @param isDebug 是否debug模式，
     */
    public static void InitBugly(Context context, String key, boolean isDebug) {
        CrashReport.initCrashReport(context, key, isDebug);
//        //设置渠道
//        CrashReport.setAppChannel(context,"aaa");
//        //设置版本
//        CrashReport.setAppVersion(context,"1.0.0");
    }

    /**
     * 初始化   增加上报进程控制
     *
     * @param context 上下文
     * @param key     注册时申请的APPID
     * @param isDebug 是否debug模式
     */
    public static void InitBuglyProcess(Context context, String key, boolean isDebug) {
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, key, isDebug, strategy);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
