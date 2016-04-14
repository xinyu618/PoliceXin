package com.px.policexin;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

/**
 * Created by wuxinyu on 16/3/26.
 */
public class PoliceXinApplication extends Application {
    private final static String TAG = "PwApplication";

    private static PoliceXinApplication mInstance;

    private Stack<Activity> mActivityStack = null;

    public static PoliceXinApplication getInstance() {
        if (mInstance == null) {
            mInstance = new PoliceXinApplication();
        }
        return mInstance;
    }

    public PoliceXinApplication() {
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
