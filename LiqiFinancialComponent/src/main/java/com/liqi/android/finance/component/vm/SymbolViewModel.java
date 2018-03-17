package com.liqi.android.finance.component.vm;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.liqi.android.finance.component.R;
import com.liqi.android.finance.component.model.Symbol;
import com.liqi.android.finance.component.model.Variable;
import com.liqi.android.finance.component.utils.InterfaceUtil;

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
    public Variable<Integer> textColor = new Variable<>(R.color.equal);

    @Override
    protected void finalize() throws Throwable {
        disposables.clear();

        super.finalize();
    }

    public SymbolViewModel(Context _mContext, InterfaceUtil.IListDataChange _iListDataChange, Symbol _symbol) {
        disposables = new CompositeDisposable();
        this.mContext = _mContext;
        this.symbol = _symbol;
        this.iListDataChange = _iListDataChange;

        this.code.setValue(symbol.code);
        this.name.setValue(symbol.name);

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
}
