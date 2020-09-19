package com.xuyulong.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.xuyulong.myapplication.view.IndicatorView;

public class MainActivity extends AppCompatActivity {

    private IndicatorView indivatiorView;

    private int totalPage=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indivatiorView = findViewById(R.id.indivatiorView);

        indivatiorView.refreshData(0, 20);


        findViewById(R.id.btn_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentIndex = indivatiorView.getCurrentIndex();
                currentIndex--;
                if (currentIndex <= 0) {
                    currentIndex = 0;
                }
                indivatiorView.refreshData(currentIndex, totalPage);
            }
        });
        findViewById(R.id.btn_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentIndex = indivatiorView.getCurrentIndex();
                currentIndex++;
                if (currentIndex >= totalPage - 1) {
                    currentIndex = totalPage - 1;
                }
                indivatiorView.refreshData(currentIndex, totalPage);
            }
        });

    }
}