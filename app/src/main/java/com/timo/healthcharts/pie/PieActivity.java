package com.timo.healthcharts.pie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.timo.healthcharts.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description:
 * @Copyright 2018 中金慈云健康科技有限公司
 * @Created by 汤迪 on 2018/11/23
 */
public class PieActivity extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        pieChart = findViewById(R.id.pie_chart);

        initChart();
        setPieData();
    }

    private void initChart() {
        //显示百分比,此时不显示百分号
        pieChart.setUsePercentValues(true);
        //这个方法为true就是环形图，为false就是饼图
        pieChart.setDrawHoleEnabled(false);
        //数据显示动画
        pieChart.animateY(500,Easing.EasingOption.EaseInOutCirc);
    }

    private void setPieData() {
        List<PieEntry> pieList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            PieEntry pieEntry = new PieEntry(new Random().nextInt(20) + 10, "标签" + i);
            pieList.add(pieEntry);
        }
        PieDataSet dataSet = new PieDataSet(pieList, "");
        //设置饼块之间的间隔
        dataSet.setSliceSpace(3f);
        //设置饼块选中时偏离饼图中心的距离
        dataSet.setSelectionShift(5f);
        //设置外部显示数值，默认在内部
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        //设置外部显示数值连接线的颜色
        dataSet.setValueLineColor(Color.RED);

        //数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(50f);
        dataSet.setValueLinePart1Length(0.3f);
        dataSet.setValueLinePart2Length(0.4f);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#66890987"));
        colors.add(Color.parseColor("#6611FF00"));
        colors.add(Color.parseColor("#66FFFF00"));
        //设置饼块的颜色
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        //数值大小
        pieData.setValueTextSize(16f);
        //数值显示百分号
        DecimalFormat mFormat = new DecimalFormat("###,###,##0.00");
        pieData.setValueFormatter(new PercentFormatter(mFormat));

        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
