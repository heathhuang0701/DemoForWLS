package com.liqi.android.demo_for_wls.main.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liqi.android.demo_for_wls.R;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class BottomBarTab extends FrameLayout {
    private ImageView mIcon;
    private TextView mTvTitle;
    private Context mContext;
    private int mTabPosition = -1;
    private @DrawableRes int icon_on, icon_off;

    public BottomBarTab(Context context, @DrawableRes int icon_on, @DrawableRes int icon_off, CharSequence title) {
        this(context, null, icon_on, icon_off, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int icon_on, int icon_off, CharSequence title) {
        this(context, attrs, 0, icon_on, icon_off, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int icon_on, int icon_off, CharSequence title) {
        super(context, attrs, defStyleAttr);
        init(context, icon_on, icon_off, title);
    }

    private void init(Context context, int icon_on, int icon_off, CharSequence title) {
        mContext = context;
        this.icon_on = icon_on;
        this.icon_off = icon_off;
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        typedArray.recycle();

        LinearLayout lLContainer = new LinearLayout(context);
        lLContainer.setOrientation(LinearLayout.VERTICAL);
        lLContainer.setGravity(Gravity.CENTER);
        LayoutParams paramsContainer = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsContainer.gravity = Gravity.CENTER;
        lLContainer.setLayoutParams(paramsContainer);

        mIcon = new ImageView(context);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 21, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
        params.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        mIcon.setImageResource(icon_off);
        mIcon.setLayoutParams(params);
        lLContainer.addView(mIcon);

        mTvTitle = new TextView(context);
        mTvTitle.setText(title);
        LinearLayout.LayoutParams paramsTv = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTv.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        mTvTitle.setTextSize(10);
        mTvTitle.setTextColor(ContextCompat.getColor(context, R.color.main_tab_unselected));
        mTvTitle.setLayoutParams(paramsTv);
        lLContainer.addView(mTvTitle);

        addView(lLContainer);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            mIcon.setImageResource(icon_on);
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.main_tab_selected));
        } else {
            mIcon.setImageResource(icon_off);
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.main_tab_unselected));
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }
}
