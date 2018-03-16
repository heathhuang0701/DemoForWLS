package com.liqi.android.demo_for_wls;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liqi.android.demo_for_wls.utils.InterfaceUtil;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by heath on 2018/3/14.
 */
public class BaseFragment extends SupportFragment implements InterfaceUtil.IStartActivity {
    protected String TAG = getClass().getSimpleName();
    protected Context mContext;
    private ArrayList<Bitmap> _bitmaps;
    private ArrayList<Drawable> _drawables;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        recycleAllUsedDrawables();
        recycleAllUsedBitmaps();
        System.gc();

        super.onDestroyView();
    }

    protected void addUsedBitmap(Bitmap bmp) {
        if (_bitmaps == null) {
            _bitmaps = new ArrayList<>();
        }
        _bitmaps.add(bmp);
    }

    protected void recycleAllUsedBitmaps() {
        if (_bitmaps == null) {
            return;
        }

        for (Bitmap bmp : _bitmaps) {
            if (bmp != null && !bmp.isRecycled()) {
                bmp.recycle();
                bmp = null;
            }
        }
        _bitmaps = null;
    }

    protected void addUsedDrawable(Drawable drawable) {
        if (_drawables == null) {
            _drawables = new ArrayList<>();
        }
        _drawables.add(drawable);
    }

    protected void recycleAllUsedDrawables() {
        if (_drawables == null) {
            return;
        }

        for (Drawable drawable : _drawables) {
            if (drawable != null) {
                drawable.setCallback(null);
                drawable = null;
            }
        }
        _drawables = null;
    }

    @Override
    public void callActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void callActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }
}
