package com.liqi.android.demo_for_wls.symbols;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.liqi.android.demo_for_wls.R;
import com.liqi.android.finance.component.model.QuoteUpdate;
import com.liqi.android.finance.component.model.Symbol;
import com.liqi.android.finance.component.view.SymbolListFragment;

import java.math.BigDecimal;
import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by heath on 2018/3/17.
 */

public class SymbolsFragment extends SupportFragment {

    public static SymbolsFragment newInstance() {
        return new SymbolsFragment();
    }

    private Handler loop_handler;
    private CountDownTimer quote_timer;
    private Runnable loop_task = new Runnable() {
        @Override
        public void run() {
            /*
                模擬接收行情下行API後的處理，實際使用應由API更新行情
             */
            if (quote_timer != null) {
                quote_timer.cancel();
                quote_timer = null;
            }

            long interval = 500;
            quote_timer = new CountDownTimer(300000, interval) {
                @Override
                public void onTick(long l) {
                    ArrayList<QuoteUpdate> quotes = FakeData.genQuotes();
                    for (int i = 0; i < quotes.size(); i++) {
                        QuoteUpdate fakeQuote = quotes.get(i);

                        double strike = rounded(Double.parseDouble(fakeQuote.price) - 0.7 + Math.random(), 2);
                        fakeQuote.price = String.valueOf(strike);
                        fakeQuote.updown = String.valueOf(rounded(strike - Double.parseDouble(fakeQuote.preclose), 2));
                        fakeQuote.updownRatio = String.valueOf(rounded(Math.abs(Double.parseDouble(fakeQuote.updown)) / Double.parseDouble(fakeQuote.preclose), 2));
                        fakeQuote.bidPrice = String.valueOf(rounded(strike - 0.05, 2));
                        fakeQuote.askPrice = String.valueOf(rounded(strike + 0.05, 2));
                        fakeQuote.bidVolume = String.valueOf((int)(Math.random()* 5));
                        fakeQuote.askVolume = String.valueOf((int)(Math.random()* 5));
                        fakeQuote.high = String.valueOf(rounded(strike + 1, 2));
                        fakeQuote.low = String.valueOf(rounded(strike - 1, 2));

                        /*
                            更新行情
                         */
                        for (int j = 0; j < symbols.size(); j++) {
                            Symbol symbol = symbols.get(j);
                            if (fakeQuote.code.equals(symbol.code)) {
                                symbol.processQuoteUpdatePacket(fakeQuote);
                            }
                        }
                    }
                }

                @Override
                public void onFinish() {

                }
            };
            quote_timer.start();
        }

        private double rounded(double value, int places) {
            BigDecimal bd = new BigDecimal(value);
            BigDecimal bd2 = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
            return Double.parseDouble(bd2.toString());
        }
    };

    private ArrayList<Symbol> symbols;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loop_handler = new Handler(Looper.getMainLooper());
        LinearLayout mRootView = (LinearLayout) inflater.inflate(R.layout.fragment_container, container, false);

        /*
            將商品資料Symbol列表作為參數載入SymbolListFragment後即可呈現商品列表
            使用第三方Fragment，優點在於除了基本的Activity內嵌Fragment之外，也可Fragment內嵌Fragment
            請見https://github.com/YoKeyword/Fragmentation
         */
        symbols = FakeData.genSymbols();
        Bundle bundle = new Bundle();
        bundle.putSerializable("symbols", symbols);
        SymbolListFragment listFragment = SymbolListFragment.newInstance(bundle);
        loadRootFragment(R.id.container, listFragment);

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        loop_handler.post(loop_task);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (quote_timer != null) {
            quote_timer.cancel();
            quote_timer = null;
        }
        if (loop_handler != null) {
            loop_handler.removeCallbacks(loop_task);
        }
    }
}
