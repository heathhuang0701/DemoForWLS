package com.liqi.android.demo_for_wls.utils;

import android.content.Intent;

import com.liqi.android.demo_for_wls.model.Symbol;
import com.liqi.android.demo_for_wls.vm.SymbolViewModel;

/**
 * Created by heath on 2018/3/16.
 */

public class InterfaceUtil {

    public interface IListDataChange {
        void notifyDataSetChanged();
    }
}
