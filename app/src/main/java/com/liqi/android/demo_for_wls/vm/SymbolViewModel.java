package com.liqi.android.demo_for_wls.vm;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.liqi.android.demo_for_wls.R;
import com.liqi.android.demo_for_wls.model.Symbol;
import com.liqi.android.demo_for_wls.model.Variable;
import com.liqi.android.demo_for_wls.utils.InterfaceUtil;

import java.math.BigDecimal;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by heath on 2018/3/14.
 */

public class SymbolViewModel {
    private final String TAG = getClass().getSimpleName();
    private CompositeDisposable disposables;
    private Context mContext;
    private Symbol symbol;
    private InterfaceUtil.IListDataChange iListDataChange;
    public Variable<String> name = new Variable<>("-");
    public Variable<String> code = new Variable<>("-");
    public Variable<String> price = new Variable<>("-");
    public Variable<String> updown = new Variable<>("-");
    public Variable<String> updownRatio = new Variable<>("-");
    public Variable<String> bidPrice = new Variable<>("-");
    public Variable<String> askPrice = new Variable<>("-");
    public Variable<String> volume = new Variable<>("-");
    public Variable<String> totalVolume = new Variable<>("-");
    public Variable<String> bidVolume = new Variable<>("-");
    public Variable<String> askVolume = new Variable<>("-");
    public Variable<String> high = new Variable<>("-");
    public Variable<String> low = new Variable<>("-");
    public Variable<String> open = new Variable<>("-");
    public Variable<String> amplitude = new Variable<>("-");
    public Variable<String> preclose = new Variable<>("-");
    public Variable<String> time = new Variable<>("-");
    public Variable<Integer> textColor = new Variable<>(Color.WHITE);

    private Handler loop_handler;
    private CountDownTimer quote_timer;
    private Runnable loop_task = new Runnable() {
        @Override
        public void run() {
            if (quote_timer != null) {
                quote_timer.cancel();
                quote_timer = null;
            }

            long interval = (long)(Math.random()* 1500 + 500);
            quote_timer = new CountDownTimer(300000, interval) {
                @Override
                public void onTick(long l) {
                    handleQuoteUpdate(symbol);
                }

                @Override
                public void onFinish() {

                }
            };
            quote_timer.start();
            Log.i(TAG, "CountDownTimer start");
        }
    };

    @Override
    protected void finalize() throws Throwable {
        disposables.clear();
        if (quote_timer != null) {
            quote_timer.cancel();
            quote_timer = null;
        }
        if (loop_handler != null) {
            loop_handler.removeCallbacks(loop_task);
        }

        super.finalize();
    }

    public SymbolViewModel(Context _mContext, InterfaceUtil.IListDataChange _iListDataChange, Symbol _symbol) {
        disposables = new CompositeDisposable();
        this.mContext = _mContext;
        this.symbol = _symbol;
        this.iListDataChange = _iListDataChange;

        this.code.setValue(symbol.code);
        this.name.setValue(symbol.name);

//        startFakeQuote();

        DisposableObserver<Symbol> quoteUpdateObserver = new DisposableObserver<Symbol>() {
            @Override
            public void onNext(@NonNull Symbol symbol) {
                handleQuoteUpdate(symbol);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        disposables.add(quoteUpdateObserver);
        symbol.quoteUpdateSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(quoteUpdateObserver);
    }

    private void startFakeQuote() {
        loop_handler = new Handler(Looper.getMainLooper());
        CountDownTimer delay_timer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long l) {}

            @Override
            public void onFinish() {
                loop_handler.post(loop_task);
            }
        };
        delay_timer.start();
    }

    private void handleQuoteUpdate(Symbol symbol) {
        String price = symbol.price;
        if (!TextUtils.isEmpty(price)) {
            this.price.setValue(price);
        }
        String updown = symbol.updown;
        if (!TextUtils.isEmpty(updown)) {
            this.updown.setValue(updown);
        }
        String updownRatio = symbol.updownRatio;
        if (!TextUtils.isEmpty(updownRatio)) {
            this.updownRatio.setValue(updownRatio);
        }
        String bidPrice = symbol.bidPrice;
        if (!TextUtils.isEmpty(bidPrice)) {
            this.bidPrice.setValue(bidPrice);
        }
        String askPrice = symbol.askPrice;
        if (!TextUtils.isEmpty(askPrice)) {
            this.askPrice.setValue(askPrice);
        }
        String volume = symbol.volume;
        if (!TextUtils.isEmpty(volume)) {
            this.volume.setValue(volume);
        }
        String totalVolume = symbol.totalVolume;
        if (!TextUtils.isEmpty(totalVolume)) {
            this.totalVolume.setValue(totalVolume);
        }
        String bidVolume = symbol.bidVolume;
        if (!TextUtils.isEmpty(bidVolume)) {
            this.bidVolume.setValue(bidVolume);
        }
        String askVolume = symbol.askVolume;
        if (!TextUtils.isEmpty(askVolume)) {
            this.askVolume.setValue(askVolume);
        }
        String high = symbol.high;
        if (!TextUtils.isEmpty(high)) {
            this.high.setValue(high);
        }
        String low = symbol.low;
        if (!TextUtils.isEmpty(low)) {
            this.low.setValue(low);
        }
        String open = symbol.open;
        if (!TextUtils.isEmpty(open)) {
            this.open.setValue(open);
        }
        String amplitude = symbol.amplitude;
        if (!TextUtils.isEmpty(amplitude)) {
            this.amplitude.setValue(amplitude);
        }
        String preclose = symbol.preclose;
        if (!TextUtils.isEmpty(preclose)) {
            this.preclose.setValue(preclose);
        }
        String time = symbol.time;
        if (!TextUtils.isEmpty(time)) {
            this.time.setValue(time);
        }

        if (!TextUtils.isEmpty(price) && !TextUtils.isEmpty(open)) {
            double value = Double.parseDouble(price) - Double.parseDouble(open);
            if (value > 0) {
                textColor.setValue(ContextCompat.getColor(mContext, R.color.up));
            } else if (value < 0) {
                textColor.setValue(ContextCompat.getColor(mContext, R.color.down));
            } else {
                textColor.setValue(ContextCompat.getColor(mContext, R.color.equal));
            }
        }

        if (iListDataChange != null) {
            iListDataChange.notifyDataSetChanged();
        }
    }

//    private void handleQuoteUpdate(Symbol symbol) {
//        double strike = rounded(Double.parseDouble(symbol.price) - 0.7 + Math.random(), 2);
//        String price = String.valueOf(strike);
//        if (!TextUtils.isEmpty(price)) {
//            this.price.setValue(price);
//        }
//        String updown = String.valueOf(rounded(strike - Double.parseDouble(symbol.preclose), 2));
//        if (!TextUtils.isEmpty(updown)) {
//            this.updown.setValue(updown);
//        }
//        String updownRatio = String.valueOf(rounded(Math.abs(Double.parseDouble(updown)) / Double.parseDouble(symbol.preclose), 2));
//        if (!TextUtils.isEmpty(updownRatio)) {
//            this.updownRatio.setValue(updownRatio);
//        }
//        String bidPrice = String.valueOf(rounded(strike - 0.05, 2));
//        if (!TextUtils.isEmpty(bidPrice)) {
//            this.bidPrice.setValue(bidPrice);
//        }
//        String askPrice = String.valueOf(rounded(strike + 0.05, 2));
//        if (!TextUtils.isEmpty(askPrice)) {
//            this.askPrice.setValue(askPrice);
//        }
//        String volume = symbol.volume;
//        if (!TextUtils.isEmpty(volume)) {
//            this.volume.setValue(volume);
//        }
//        symbol.totalVolume = String.valueOf(Integer.valueOf(symbol.totalVolume) + (int)(Math.random()* 5));
//        String totalVolume = symbol.totalVolume;
//        if (!TextUtils.isEmpty(totalVolume)) {
//            this.totalVolume.setValue(totalVolume);
//        }
//        String bidVolume = String.valueOf((int)(Math.random()* 5));
//        if (!TextUtils.isEmpty(bidVolume)) {
//            this.bidVolume.setValue(bidVolume);
//        }
//        String askVolume = String.valueOf((int)(Math.random()* 5));
//        if (!TextUtils.isEmpty(askVolume)) {
//            this.askVolume.setValue(askVolume);
//        }
//        String high = String.valueOf(rounded(strike + 1, 2));
//        if (!TextUtils.isEmpty(high)) {
//            this.high.setValue(high);
//        }
//        String low = String.valueOf(rounded(strike - 1, 2));
//        if (!TextUtils.isEmpty(low)) {
//            this.low.setValue(low);
//        }
//        String open = symbol.open;
//        if (!TextUtils.isEmpty(open)) {
//            this.open.setValue(open);
//        }
//        String amplitude = symbol.amplitude;
//        if (!TextUtils.isEmpty(amplitude)) {
//            this.amplitude.setValue(amplitude);
//        }
//        String preclose = symbol.preclose;
//        if (!TextUtils.isEmpty(preclose)) {
//            this.preclose.setValue(preclose);
//        }
//        String time = symbol.time;
//        if (!TextUtils.isEmpty(time)) {
//            this.time.setValue(time);
//        }
//
//        double value = strike - Double.parseDouble(open);
//        if (value > 0) {
//            textColor.setValue(ContextCompat.getColor(mContext, R.color.up));
//        } else if (value < 0) {
//            textColor.setValue(ContextCompat.getColor(mContext, R.color.down));
//        } else {
//            textColor.setValue(ContextCompat.getColor(mContext, R.color.equal));
//        }
//
//        if (iListDataChange != null) {
//            iListDataChange.notifyDataSetChanged();
//        }
//    }

    private double rounded(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        BigDecimal bd2 = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
        return Double.parseDouble(bd2.toString());
    }
}
