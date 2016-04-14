package com.pw.policeweixin;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

/**
 * Created by wuxinyu on 16/3/26.
 */
public class PoliceWeixinApplication extends Application {
    private final static String TAG = "PwApplication";

    private static PoliceWeixinApplication mInstance;

    private Stack<Activity> mActivityStack = null;

    public static PoliceWeixinApplication getInstance() {
        if (mInstance == null) {
            mInstance = new PoliceWeixinApplication();
        }
        return mInstance;
    }

    public PoliceWeixinApplication() {
        mInstance = this;
    }

    public void pushActivity(Activity activity) {
        if (null == mActivityStack) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.push(activity);
    }

    public void popActivity(Activity activity) {
        if (null != mActivityStack && !mActivityStack.isEmpty()
                && null != activity) {
            mActivityStack.remove(activity);
        }
    }

    public void finishAllActivity() {
        while (!mActivityStack.isEmpty()) {
            Activity activity = mActivityStack.pop();
            if (!activity.isFinishing() && !activity.isDestroyed()) {
                activity.finish();
            }
        }
        mActivityStack.clear();
    }
}
