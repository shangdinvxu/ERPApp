package tarce.testnew;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import butterknife.OnClick;

/**
 * Created by Daniel.Xu on 2017/1/5.
 */

public class MyApplication extends Application {
    private static Context context;
    public static int userID ;
    public static String Url ;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
//        GreenDao的初始化
        GreenDaoManager.getInstance();
        ZXingLibrary.initDisplayOpinion(this);

    }

    //返回
    public static Context getContext(){
        return context;
    }


}
