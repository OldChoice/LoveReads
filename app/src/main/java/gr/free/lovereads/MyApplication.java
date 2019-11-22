package gr.free.lovereads;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.multidex.MultiDex;

import gr.free.grutils.GrUtilInstance;

/**
 * Create by guorui on 2019/11/8
 * Last update 2019/11/8
 * Description:
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        GrUtilInstance.getInstance(getInstance());

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 获取versionname
     */
    public static String getVersionName() {
        try {
            return MyApplication.getInstance().getPackageManager().getPackageInfo(MyApplication.getInstance().getPackageName(),
                    0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "00.00.00.0";
        }
    }

}

