package com.liqi.android.demo_for_wls.symbols;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.liqi.android.demo_for_wls.BaseFragment;
import com.liqi.android.demo_for_wls.R;
import com.liqi.android.demo_for_wls.model.Symbol;
import com.liqi.android.demo_for_wls.utils.InterfaceUtil;
import com.liqi.android.demo_for_wls.vm.SymbolViewModel;

import java.util.ArrayList;

/**
 * Created by heath on 2018/3/14.
 */

public class SymbolListFragment extends BaseFragment implements InterfaceUtil.IListDataChange {

    public static SymbolListFragment newInstance(Bundle args) {
        SymbolListFragment fragment = new SymbolListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private SymbolBriefAdapter adapter_1;
    private SymbolInfoAdapter adapter_2;
    private ArrayList<ListView> listViews = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout mRootView = (LinearLayout) inflater.inflate(R.layout.wls_fragment_symbol_list, container, false);
        ListView listView_names = mRootView.findViewById(R.id.listview_product_name);
        ListView listView_infos = mRootView.findViewById(R.id.listview_product_info);

        ArrayList<Symbol> symbols = (ArrayList<Symbol>) getArguments().getSerializable("symbols");
        ArrayList<SymbolViewModel> viewModels = new ArrayList<>();
        for (Symbol symbol : symbols) {
            SymbolViewModel viewModel = new SymbolViewModel(getContext(), this, symbol);
            viewModels.add(viewModel);
        }

        adapter_1 = new SymbolBriefAdapter(viewModels);
        listView_names.setAdapter(adapter_1);
        adapter_2 = new SymbolInfoAdapter(viewModels);
        listView_infos.setAdapter(adapter_2);
        listViews.add(listView_names);
        listViews.add(listView_infos);

        MyScrollListener mListener = new MyScrollListener();
        for (ListView item : listViews) {
            item.setOnScrollListener(mListener);
        }

        return mRootView;
    }

    @Override
    public void notifyDataSetChanged() {
        if (adapter_1 != null) {
            adapter_1.notifyDataSetChanged();
        }
        if (adapter_2 != null) {
            adapter_2.notifyDataSetChanged();
        }
    }

    private class SymbolBriefAdapter extends BaseAdapter {

        private ArrayList<SymbolViewModel> symbols;

        public SymbolBriefAdapter(ArrayList<SymbolViewModel> array) {
            symbols = array;
        }

        @Override
        public int getCount() {
            return symbols.size();
        }

        @Override
        public SymbolViewModel getItem(int position) {
            return symbols.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder viewHolder;
            SymbolViewModel viewModel = getItem(position);

            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(mContext).inflate(R.layout.wls_cell_symbol_name, null);
                viewHolder.tv_symbol_name = view.findViewById(R.id.tv_symbol_name);
                viewHolder.chart = view.findViewById(R.id.chart);

                viewHolder.chart.setPinchZoom(false);
                viewHolder.chart.setDrawGridBackground(false);
                viewHolder.chart.getLegend().setEnabled(false);
                viewHolder.chart.setDescription(null);

                XAxis xAxis = viewHolder.chart.getXAxis();
                xAxis.setEnabled(false);
                YAxis leftAxis = viewHolder.chart.getAxisLeft();
                leftAxis.setEnabled(false);
                YAxis rightAxis = viewHolder.chart.getAxisRight();
                rightAxis.setEnabled(false);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.tv_symbol_name.setText(viewModel.name.getValue());

            if (
                    !viewModel.high.getValue().equals("-") &&
                    !viewModel.low.getValue().equals("-") &&
                    !viewModel.open.getValue().equals("-") &&
                    !viewModel.price.getValue().equals("-")
            ) {
                viewHolder.chart.resetTracking();
                if (!viewHolder.chart.isEmpty()) {
                    viewHolder.chart.clearValues();
                }
                ArrayList<CandleEntry> entries = new ArrayList<CandleEntry>();
                CandleEntry entry = new CandleEntry(
                        0,
                        Float.parseFloat(viewModel.high.getValue()),
                        Float.parseFloat(viewModel.low.getValue()),
                        Float.parseFloat(viewModel.open.getValue()),
                        Float.parseFloat(viewModel.price.getValue())
                );
                entries.add(entry);
                CandleDataSet dataSet = new CandleDataSet(entries, "");
                dataSet.setShadowColor(Color.parseColor("#666666"));
                dataSet.setShadowWidth(0.7f);
                dataSet.setIncreasingColor(Color.RED);
                dataSet.setDecreasingPaintStyle(Paint.Style.FILL);
                dataSet.setDecreasingColor(Color.rgb(122, 242, 84));
                dataSet.setIncreasingPaintStyle(Paint.Style.FILL);
                dataSet.setNeutralColor(Color.parseColor("#666666"));

                String[] labels = new String[]{""};
                CandleData data = new CandleData(labels, dataSet);
                data.setHighlightEnabled(false);
                data.setDrawValues(false);

                viewHolder.chart.setData(data);
                viewHolder.chart.invalidate();
            }

            return view;
        }

        public class ViewHolder {
            CandleStickChart chart;
            TextView tv_symbol_name;
        }
    }

    private class SymbolInfoAdapter extends BaseAdapter {

        private ArrayList<SymbolViewModel> symbols;

        public SymbolInfoAdapter(ArrayList<SymbolViewModel> array) {
            symbols = array;
        }

        @Override
        public int getCount() {
            return symbols.size();
        }

        @Override
        public SymbolViewModel getItem(int position) {
            return symbols.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder viewHolder;
            SymbolViewModel model = getItem(position);

            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(mContext).inflate(R.layout.wls_cell_symbol, null);
                viewHolder.tv_price = view.findViewById(R.id.tv_price);
                viewHolder.tv_updown = view.findViewById(R.id.tv_updown);
                viewHolder.tv_updown_ratio = view.findViewById(R.id.tv_updown_ratio);
                viewHolder.tv_bid_price = view.findViewById(R.id.tv_bid_price);
                viewHolder.tv_ask_price = view.findViewById(R.id.tv_ask_price);
                viewHolder.tv_volume = view.findViewById(R.id.tv_volume);
                viewHolder.tv_total_volume = view.findViewById(R.id.tv_total_volume);
                viewHolder.tv_bid_volume = view.findViewById(R.id.tv_bid_volume);
                viewHolder.tv_ask_volume = view.findViewById(R.id.tv_ask_volume);
                viewHolder.tv_high = view.findViewById(R.id.tv_high);
                viewHolder.tv_low = view.findViewById(R.id.tv_low);
                viewHolder.tv_open = view.findViewById(R.id.tv_open);
                viewHolder.tv_amplitude = view.findViewById(R.id.tv_amplitude);
                viewHolder.tv_preclose = view.findViewById(R.id.tv_preclose);
                viewHolder.tv_time = view.findViewById(R.id.tv_time);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.tv_price.setText(model.price.getValue());
            viewHolder.tv_updown.setText(model.updown.getValue());
            viewHolder.tv_updown_ratio.setText(model.updownRatio.getValue());
            viewHolder.tv_bid_price.setText(model.bidPrice.getValue());
            viewHolder.tv_ask_price.setText(model.askPrice.getValue());
            viewHolder.tv_volume.setText(model.volume.getValue());
            viewHolder.tv_total_volume.setText(model.totalVolume.getValue());
            viewHolder.tv_bid_volume.setText(model.bidVolume.getValue());
            viewHolder.tv_ask_volume.setText(model.askVolume.getValue());
            viewHolder.tv_high.setText(model.high.getValue());
            viewHolder.tv_low.setText(model.low.getValue());
            viewHolder.tv_open.setText(model.open.getValue());
            viewHolder.tv_amplitude.setText(model.amplitude.getValue());
            viewHolder.tv_preclose.setText(model.preclose.getValue());
            viewHolder.tv_time.setText(model.time.getValue());

            viewHolder.tv_price.setTextColor(model.textColor.getValue());
            viewHolder.tv_updown.setTextColor(model.textColor.getValue());
            viewHolder.tv_updown_ratio.setTextColor(model.textColor.getValue());

            return view;
        }

        public class ViewHolder {
            TextView tv_price;
            TextView tv_updown;
            TextView tv_updown_ratio;
            TextView tv_bid_price;
            TextView tv_ask_price;
            TextView tv_volume;
            TextView tv_total_volume;
            TextView tv_bid_volume;
            TextView tv_ask_volume;
            TextView tv_high;
            TextView tv_low;
            TextView tv_open;
            TextView tv_amplitude;
            TextView tv_preclose;
            TextView tv_time;
        }
    }

    private class MyScrollListener implements AbsListView.OnScrollListener {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == SCROLL_STATE_IDLE
                    || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                View subView = view.getChildAt(0);
                if (subView != null) {
                    final int top = subView.getTop();
                    final int position = view.getFirstVisiblePosition();
                    for (ListView item : listViews) {
                        item.setSelectionFromTop(position, top);
                    }
                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            View subView = view.getChildAt(0);
            if (subView != null) {
                final int top = subView.getTop();
                for (ListView item : listViews) {
                    item.setSelectionFromTop(firstVisibleItem, top);
                }
            }
        }

    }
}
