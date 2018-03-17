package com.liqi.android.demo_for_wls.model;

import io.reactivex.subjects.ReplaySubject;

/**
 * Created by heath on 2018/3/14.
 */

public class Symbol extends QuoteUpdate {

    public ReplaySubject<Symbol> quoteUpdateSubject = ReplaySubject.createWithSize(1);

    public void processQuoteUpdatePacket(QuoteUpdate quote) {
        this.name = quote.name;
        this.code = quote.code;
        this.price = quote.price;
        this.updown = quote.updown;
        this.updownRatio = quote.updownRatio;
        this.bidPrice = quote.bidPrice;
        this.askPrice = quote.askPrice;
        this.volume = quote.volume;
        this.totalVolume = quote.totalVolume;
        this.bidVolume = quote.bidVolume;
        this.askVolume = quote.askVolume;
        this.high = quote.high;
        this.low = quote.low;
        this.open = quote.open;
        this.amplitude = quote.amplitude;
        this.preclose = quote.preclose;
        this.time = quote.time;

        quoteUpdateSubject.onNext(this);
    }
}
