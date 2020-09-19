package com.xuyulong.myapplication.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xuyulong.myapplication.R;
import com.xuyulong.myapplication.view.adapter.CommonAdapter;
import com.xuyulong.myapplication.view.adapter.MultiItemTypeAdapter;
import com.xuyulong.myapplication.view.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class IndicatorView extends LinearLayout {

    public static final int DEFAULT_SIZE = 8;

    private final String DIVIDE = "...";

    public int getCurrentIndex() {
        return currentIndex;
    }

    private int currentIndex;
    private int total;

    private TextView tv_total;
    private RecyclerView rv;
    private CommonAdapter<String> commonAdapter;
    private ArrayList<String> dataList;

    public IndicatorView(Context context) {
        super(context);
        initView(context);
    }


    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
        LayoutInflater.from(context).inflate(R.layout.item_indicator, this, true);
        tv_total = findViewById(R.id.tv_total);
        rv = findViewById(R.id.rv);

        dataList = new ArrayList();
        rv.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        commonAdapter = new CommonAdapter<String>(context, R.layout.item_text, dataList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                TextView tv = holder.getView(R.id.tv);
                tv.setText(s);

                if (String.valueOf(currentIndex + 1).equals(s)) {
                    tv.setTextColor(Color.RED);
                } else {
                    tv.setTextColor(Color.GRAY);
                }


            }
        };
        rv.setAdapter(commonAdapter);

        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                String s = dataList.get(position);
                if (DIVIDE.equals(s)) {
                    Toast.makeText(context, DIVIDE, Toast.LENGTH_LONG).show();
                } else {
                    int num = Integer.parseInt(dataList.get(position));
                    refreshData(num - 1, total);
                    Toast.makeText(context, num + "", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }


    public void refreshData(int currentIndex, int total) {
        this.currentIndex = currentIndex;
        this.total = total;
        dataList.clear();
        ArrayList<String> tempList = new ArrayList<>();
        if (total <= DEFAULT_SIZE) {
            for (int i = 0; i < DEFAULT_SIZE; i++) {
                tempList.add(i + 1 + "");
            }
            dataList.addAll(tempList);
        } else {
            int you = currentIndex / 5;
            for (int i = 5 + 5 * you - 4; i <= 5 + 5 * you; i++) {
                tempList.add(i + "");
            }
            tempList.add(DIVIDE);
            tempList.add(total - 1 + "");
            tempList.add(total + "");
            dataList.addAll(tempList);
            if (tempList.indexOf(total + "") != tempList.lastIndexOf(total + "")) {
                dataList.clear();
                List<String> strings = tempList.subList(0, tempList.indexOf(DIVIDE));
                dataList.addAll(strings);
            }
        }
        commonAdapter.notifyDataSetChanged();
    }


}
