package com.liqi.android.demo_for_wls.utils;

import android.content.Intent;

/**
 * Created by heath on 2018/3/16.
 */

public class InterfaceUtil {

    public interface IStartActivity {
        void callActivity(Intent intent);

        void callActivityForResult(Intent intent, int requestCode);
    }

    public interface IListDataChange {
        void notifyDataSetChanged();
    }
}
