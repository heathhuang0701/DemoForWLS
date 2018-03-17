package com.liqi.android.demo_for_wls.symbols;

import com.liqi.android.finance.component.model.QuoteUpdate;
import com.liqi.android.finance.component.model.Symbol;

import java.util.ArrayList;

/**
 * Created by heath on 2018/3/15.
 */

public class FakeData {

    public static ArrayList<Symbol> genSymbols() {
        ArrayList<Symbol> array = new ArrayList<>();
        Symbol symbol_1 = new Symbol();
        symbol_1.name = "味全";
        symbol_1.code = "food1";
        array.add(symbol_1);

        Symbol symbol_2 = new Symbol();
        symbol_2.name = "味王";
        symbol_2.code = "food2";
        array.add(symbol_2);

        Symbol symbol_3 = new Symbol();
        symbol_3.name = "大成";
        symbol_3.code = "food3";
        array.add(symbol_3);

        Symbol symbol_4 = new Symbol();
        symbol_4.name = "大飲";
        symbol_4.code = "food4";
        array.add(symbol_4);

        Symbol symbol_5 = new Symbol();
        symbol_5.name = "卜蜂";
        symbol_5.code = "food5";
        array.add(symbol_5);

        Symbol symbol_6 = new Symbol();
        symbol_6.name = "統一";
        symbol_6.code = "food6";
        array.add(symbol_6);

        Symbol symbol_7 = new Symbol();
        symbol_7.name = "愛之味";
        symbol_7.code = "food7";
        array.add(symbol_7);

        Symbol symbol_8 = new Symbol();
        symbol_8.name = "泰山";
        symbol_8.code = "food8";
        array.add(symbol_8);

        Symbol symbol_9 = new Symbol();
        symbol_9.name = "福壽";
        symbol_9.code = "food9";
        array.add(symbol_9);

        Symbol symbol_10 = new Symbol();
        symbol_10.name = "台榮";
        symbol_10.code = "food10";
        array.add(symbol_10);

        Symbol symbol_11 = new Symbol();
        symbol_11.name = "福懋油";
        symbol_11.code = "food11";
        array.add(symbol_11);

        Symbol symbol_12 = new Symbol();
        symbol_12.name = "佳格";
        symbol_12.code = "food12";
        array.add(symbol_12);

        Symbol symbol_13 = new Symbol();
        symbol_13.name = "聯華";
        symbol_13.code = "food13";
        array.add(symbol_13);

        Symbol symbol_14 = new Symbol();
        symbol_14.name = "聯華食";
        symbol_14.code = "food14";
        array.add(symbol_14);

        Symbol symbol_15 = new Symbol();
        symbol_15.name = "大統益";
        symbol_15.code = "food15";
        array.add(symbol_15);

        return array;
    }

    public static ArrayList<QuoteUpdate> genQuotes() {
        ArrayList<QuoteUpdate> array = new ArrayList<>();
        QuoteUpdate quote_1 = new QuoteUpdate();
        quote_1.name = "味全";
        quote_1.code = "food1";
        quote_1.price = "24.00";
        quote_1.updown = "0.20";
        quote_1.updownRatio = "0.86";
        quote_1.bidPrice = "23.50";
        quote_1.askPrice = "23.55";
        quote_1.volume = "1";
        quote_1.totalVolume = "632";
        quote_1.bidVolume = "30";
        quote_1.askVolume = "27";
        quote_1.high = "23.50";
        quote_1.low = "23.15";
        quote_1.open = "23.40";
        quote_1.amplitude = "1.5";
        quote_1.preclose = "23.30";
        quote_1.time = "13:10:05";
        array.add(quote_1);

        QuoteUpdate quote_2 = new QuoteUpdate();
        quote_2.name = "味王";
        quote_2.code = "food2";
        quote_2.price = "26.10";
        quote_2.updown = "0.20";
        quote_2.updownRatio = "0.86";
        quote_2.bidPrice = "23.50";
        quote_2.askPrice = "23.55";
        quote_2.volume = "1";
        quote_2.totalVolume = "18";
        quote_2.bidVolume = "30";
        quote_2.askVolume = "27";
        quote_2.high = "23.50";
        quote_2.low = "23.15";
        quote_2.open = "26.10";
        quote_2.amplitude = "1.5";
        quote_2.preclose = "26.10";
        quote_2.time = "13:10:05";
        array.add(quote_2);

        QuoteUpdate quote_3 = new QuoteUpdate();
        quote_3.name = "大成";
        quote_3.code = "food3";
        quote_3.price = "35.20";
        quote_3.updown = "0.20";
        quote_3.updownRatio = "0.86";
        quote_3.bidPrice = "23.50";
        quote_3.askPrice = "23.55";
        quote_3.volume = "1";
        quote_3.totalVolume = "1303";
        quote_3.bidVolume = "30";
        quote_3.askVolume = "27";
        quote_3.high = "23.50";
        quote_3.low = "23.15";
        quote_3.open = "35.50";
        quote_3.amplitude = "1.5";
        quote_3.preclose = "35.30";
        quote_3.time = "13:10:05";
        array.add(quote_3);

        QuoteUpdate quote_4 = new QuoteUpdate();
        quote_4.name = "大飲";
        quote_4.code = "food4";
        quote_4.price = "15.40";
        quote_4.updown = "0.20";
        quote_4.updownRatio = "0.86";
        quote_4.bidPrice = "23.50";
        quote_4.askPrice = "23.55";
        quote_4.volume = "1";
        quote_4.totalVolume = "13";
        quote_4.bidVolume = "30";
        quote_4.askVolume = "27";
        quote_4.high = "23.50";
        quote_4.low = "23.15";
        quote_4.open = "15.35";
        quote_4.amplitude = "1.5";
        quote_4.preclose = "15.35";
        quote_4.time = "13:10:05";
        array.add(quote_4);

        QuoteUpdate quote_5 = new QuoteUpdate();
        quote_5.name = "卜蜂";
        quote_5.code = "food5";
        quote_5.price = "67.70";
        quote_5.updown = "0.20";
        quote_5.updownRatio = "0.86";
        quote_5.bidPrice = "23.50";
        quote_5.askPrice = "23.55";
        quote_5.volume = "1";
        quote_5.totalVolume = "616";
        quote_5.bidVolume = "30";
        quote_5.askVolume = "27";
        quote_5.high = "23.50";
        quote_5.low = "23.15";
        quote_5.open = "68.00";
        quote_5.amplitude = "1.5";
        quote_5.preclose = "67.70";
        quote_5.time = "13:10:05";
        array.add(quote_5);

        QuoteUpdate quote_6 = new QuoteUpdate();
        quote_6.name = "統一";
        quote_6.code = "food6";
        quote_6.price = "69.00";
        quote_6.updown = "0.20";
        quote_6.updownRatio = "0.86";
        quote_6.bidPrice = "23.50";
        quote_6.askPrice = "23.55";
        quote_6.volume = "1";
        quote_6.totalVolume = "3173";
        quote_6.bidVolume = "30";
        quote_6.askVolume = "27";
        quote_6.high = "23.50";
        quote_6.low = "23.15";
        quote_6.open = "69.30";
        quote_6.amplitude = "1.5";
        quote_6.preclose = "69.30";
        quote_6.time = "13:10:05";
        array.add(quote_6);

        QuoteUpdate quote_7 = new QuoteUpdate();
        quote_7.name = "愛之味";
        quote_7.code = "food7";
        quote_7.price = "7.47";
        quote_7.updown = "0.20";
        quote_7.updownRatio = "0.86";
        quote_7.bidPrice = "23.50";
        quote_7.askPrice = "23.55";
        quote_7.volume = "1";
        quote_7.totalVolume = "328";
        quote_7.bidVolume = "30";
        quote_7.askVolume = "27";
        quote_7.high = "23.50";
        quote_7.low = "23.15";
        quote_7.open = "7.48";
        quote_7.amplitude = "1.5";
        quote_7.preclose = "7.44";
        quote_7.time = "13:10:05";
        array.add(quote_7);

        QuoteUpdate quote_8 = new QuoteUpdate();
        quote_8.name = "泰山";
        quote_8.code = "food8";
        quote_8.price = "17.10";
        quote_8.updown = "0.20";
        quote_8.updownRatio = "0.86";
        quote_8.bidPrice = "23.50";
        quote_8.askPrice = "23.55";
        quote_8.volume = "1";
        quote_8.totalVolume = "1709";
        quote_8.bidVolume = "30";
        quote_8.askVolume = "27";
        quote_8.high = "23.50";
        quote_8.low = "23.15";
        quote_8.open = "17.00";
        quote_8.amplitude = "1.5";
        quote_8.preclose = "17.05";
        quote_8.time = "13:10:05";
        array.add(quote_8);

        QuoteUpdate quote_9 = new QuoteUpdate();
        quote_9.name = "福壽";
        quote_9.code = "food9";
        quote_9.price = "18.50";
        quote_9.updown = "0.20";
        quote_9.updownRatio = "0.86";
        quote_9.bidPrice = "23.50";
        quote_9.askPrice = "23.55";
        quote_9.volume = "1";
        quote_9.totalVolume = "6";
        quote_9.bidVolume = "30";
        quote_9.askVolume = "27";
        quote_9.high = "23.50";
        quote_9.low = "23.15";
        quote_9.open = "18.35";
        quote_9.amplitude = "1.5";
        quote_9.preclose = "18.50";
        quote_9.time = "13:10:05";
        array.add(quote_9);

        QuoteUpdate quote_10 = new QuoteUpdate();
        quote_10.name = "台榮";
        quote_10.code = "food10";
        quote_10.price = "10.95";
        quote_10.updown = "0.20";
        quote_10.updownRatio = "0.86";
        quote_10.bidPrice = "23.50";
        quote_10.askPrice = "23.55";
        quote_10.volume = "1";
        quote_10.totalVolume = "83";
        quote_10.bidVolume = "30";
        quote_10.askVolume = "27";
        quote_10.high = "23.50";
        quote_10.low = "23.15";
        quote_10.open = "10.90";
        quote_10.amplitude = "1.5";
        quote_10.preclose = "10.90";
        quote_10.time = "13:10:05";
        array.add(quote_10);

        QuoteUpdate quote_11 = new QuoteUpdate();
        quote_11.name = "福懋油";
        quote_11.code = "food11";
        quote_11.price = "71.50";
        quote_11.updown = "0.20";
        quote_11.updownRatio = "0.86";
        quote_11.bidPrice = "23.50";
        quote_11.askPrice = "23.55";
        quote_11.volume = "1";
        quote_11.totalVolume = "34";
        quote_11.bidVolume = "30";
        quote_11.askVolume = "27";
        quote_11.high = "23.50";
        quote_11.low = "23.15";
        quote_11.open = "70.00";
        quote_11.amplitude = "1.5";
        quote_11.preclose = "72.00";
        quote_11.time = "13:10:05";
        array.add(quote_11);

        QuoteUpdate quote_12 = new QuoteUpdate();
        quote_12.name = "佳格";
        quote_12.code = "food12";
        quote_12.price = "67.90";
        quote_12.updown = "0.20";
        quote_12.updownRatio = "0.86";
        quote_12.bidPrice = "23.50";
        quote_12.askPrice = "23.55";
        quote_12.volume = "1";
        quote_12.totalVolume = "598";
        quote_12.bidVolume = "30";
        quote_12.askVolume = "27";
        quote_12.high = "23.50";
        quote_12.low = "23.15";
        quote_12.open = "67.80";
        quote_12.amplitude = "1.5";
        quote_12.preclose = "67.80";
        quote_12.time = "13:10:05";
        array.add(quote_12);

        QuoteUpdate quote_13 = new QuoteUpdate();
        quote_13.name = "聯華";
        quote_13.code = "food13";
        quote_13.price = "36.85";
        quote_13.updown = "0.20";
        quote_13.updownRatio = "0.86";
        quote_13.bidPrice = "23.50";
        quote_13.askPrice = "23.55";
        quote_13.volume = "1";
        quote_13.totalVolume = "1054";
        quote_13.bidVolume = "30";
        quote_13.askVolume = "27";
        quote_13.high = "23.50";
        quote_13.low = "23.15";
        quote_13.open = "37.00";
        quote_13.amplitude = "1.5";
        quote_13.preclose = "37.00";
        quote_13.time = "13:10:05";
        array.add(quote_13);

        QuoteUpdate quote_14 = new QuoteUpdate();
        quote_14.name = "聯華食";
        quote_14.code = "food14";
        quote_14.price = "35.10";
        quote_14.updown = "0.20";
        quote_14.updownRatio = "0.86";
        quote_14.bidPrice = "23.50";
        quote_14.askPrice = "23.55";
        quote_14.volume = "1";
        quote_14.totalVolume = "94";
        quote_14.bidVolume = "30";
        quote_14.askVolume = "27";
        quote_14.high = "23.50";
        quote_14.low = "23.15";
        quote_14.open = "35.25";
        quote_14.amplitude = "1.5";
        quote_14.preclose = "35.25";
        quote_14.time = "13:10:05";
        array.add(quote_14);

        QuoteUpdate quote_15 = new QuoteUpdate();
        quote_15.name = "大統益";
        quote_15.code = "food15";
        quote_15.price = "91.30";
        quote_15.updown = "0.20";
        quote_15.updownRatio = "0.86";
        quote_15.bidPrice = "23.50";
        quote_15.askPrice = "23.55";
        quote_15.volume = "1";
        quote_15.totalVolume = "15";
        quote_15.bidVolume = "30";
        quote_15.askVolume = "27";
        quote_15.high = "23.50";
        quote_15.low = "23.15";
        quote_15.open = "91.60";
        quote_15.amplitude = "1.5";
        quote_15.preclose = "91.60";
        quote_15.time = "13:10:05";
        array.add(quote_15);

        return array;
    }
}
