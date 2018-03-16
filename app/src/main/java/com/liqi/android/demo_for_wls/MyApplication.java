package com.liqi.android.demo_for_wls;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * Created by heath on 2018/3/12.
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        initFragmentation();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initFragmentation() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 BUBBLE: 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.BUBBLE)
                // ture时，遇到异常："Can not perform this action after onSaveInstanceState!"时，会抛出
                // false时，不会抛出，会捕获，可以在handleException()里监听到
                .debug(BuildConfig.DEBUG)
                // 在debug=false时，即线上环境时，上述异常会被捕获并回调ExceptionHandler
                .handleException(new ExceptionHandler() {
                                     @Override
                                     public void onException(Exception e) {
                                         // 建议在该回调处上传至我们的Crash监测服务器
                                         // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
                                         // Bugtags.sendException(e);
                                     }
                                 }
                ).install();
    }
}
