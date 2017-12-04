package com.example.linechart;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import com.example.zhaoming.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

public class MainActivity extends Activity {

    private LineChart temperature_mChart;
    private LineChart humidity_mChart;
    private LineChart power_mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.table_avtivity);
        //温度
        temperature_mChart=(LineChart) findViewById(R.id.temperature_lineChart);
        //湿度
        humidity_mChart=(LineChart) findViewById(R.id.humidity_lineChart);
        //功耗
        power_mChart=(LineChart) findViewById(R.id.power_lineChart);
        //温度的相关处理
        temperature_mChart_initChart();
        //湿度的相关处理
        humidity_mChart_initChart();
        //功耗的相关处理
        power_mChart_initChart();
    }

    /**
     * 温度数据初始化
     * 1.初始化LineChart
     * 2.添加数据x，y轴数据
     * 3.刷新图表
     */
    private void temperature_mChart_initChart() {
        /**
         * ====================1.初始化-自由配置===========================
         */
    	
        // 是否在折线图上添加边框
        temperature_mChart.setDrawGridBackground(true);
        temperature_mChart.setDrawBorders(true);
        // 设置右下角描述
        temperature_mChart.setDescription("温度:单位摄氏度");
        //设置透明度
        temperature_mChart.setAlpha(0.8f);
        //设置网格底下的那条线的颜色
        temperature_mChart.setBorderColor(Color.rgb(213, 216, 214));
        //设置高亮显示
       // temperature_mChart.setHighlightEnabled(true);
        //设置是否可以触摸，如为false，则不能拖动，缩放等
        temperature_mChart.setTouchEnabled(true);
        //设置是否可以拖拽
        temperature_mChart.setDragEnabled(false);
        //设置是否可以缩放
        temperature_mChart.setScaleEnabled(true);
        //设置是否能扩大扩小
        temperature_mChart.setPinchZoom(true);
        /**
         * ====================2.布局点添加数据-自由布局===========================
         */
        // 折线图的点，点击战士的布局和数据
        MyMarkView mv = new MyMarkView(this);
        temperature_mChart.setMarkerView(mv);
        // 加载数据
        LineData data = getLineData_temperature();
        temperature_mChart.setData(data);
        /**
         * ====================3.x，y动画效果和刷新图表等===========================
         */
        //从X轴进入的动画
        temperature_mChart.animateX(4000);
        temperature_mChart.animateY(3000);   //从Y轴进入的动画
        temperature_mChart.animateXY(3000, 3000);    //从XY轴一起进入的动画
        //设置最小的缩放
        temperature_mChart.setScaleMinima(0.5f, 1f);
        Legend l = temperature_mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);  //设置图最下面显示的类型
        l.setTextSize(15);
        l.setTextColor(Color.rgb(104, 241, 175));
        l.setFormSize(30f);
        // 刷新图表
        temperature_mChart.invalidate();
    }

    private LineData getLineData_temperature() {
        String[] xx = {"0:00","4:00", "8:00", "10:00","12:00", "14:00","16:00", "20:00"};
        String[] yy = {"25", "23", "26", "28", "28", "30", "32", "28"};

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xx.length; i++) {
            xVals.add(xx[i]);
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < yy.length; i++) {
            yVals.add(new Entry(Float.parseFloat(yy[i]), i));
        }

        LineDataSet set1 = new LineDataSet(yVals, "室内温度的变化");
        set1.setDrawCubic(true);  //设置曲线为圆滑的线
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //设置包括的范围区域填充颜色
        set1.setDrawCircles(true);  //设置有圆点
        set1.setLineWidth(2f);    //设置线的宽度
        set1.setCircleSize(5f);   //设置小圆的大小
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));    //设置曲线的颜色

        return new LineData(xVals, set1);
    }
    
    /**
     * 湿度初始化
     */
    private void humidity_mChart_initChart() {
        /**
         * ====================1.初始化-自由配置===========================
         */
    	
        // 是否在折线图上添加边框
    	humidity_mChart.setDrawGridBackground(true);
        humidity_mChart.setDrawBorders(true);
        // 设置右下角描述
        humidity_mChart.setDescription("湿度");
        //设置透明度
        humidity_mChart.setAlpha(0.8f);
        //设置网格底下的那条线的颜色
        humidity_mChart.setBorderColor(Color.rgb(213, 216, 214));
        //设置高亮显示
       // humidity_mChart.setHighlightEnabled(true);
        //设置是否可以触摸，如为false，则不能拖动，缩放等
        humidity_mChart.setTouchEnabled(true);
        //设置是否可以拖拽
        humidity_mChart.setDragEnabled(false);
        //设置是否可以缩放
        humidity_mChart.setScaleEnabled(true);
        //设置是否能扩大扩小
        humidity_mChart.setPinchZoom(true);
        /**
         * ====================2.布局点添加数据-自由布局===========================
         */
        // 折线图的点，点击战士的布局和数据
        MyMarkView mv = new MyMarkView(this);
        humidity_mChart.setMarkerView(mv);
        // 加载数据
        LineData data = getLineData_humidity();
        humidity_mChart.setData(data);
        /**
         * ====================3.x，y动画效果和刷新图表等===========================
         */
        //从X轴进入的动画
        humidity_mChart.animateX(4000);
        humidity_mChart.animateY(3000);   //从Y轴进入的动画
        humidity_mChart.animateXY(3000, 3000);    //从XY轴一起进入的动画
        //设置最小的缩放
        humidity_mChart.setScaleMinima(0.5f, 1f);
        Legend l = humidity_mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);  //设置图最下面显示的类型
        l.setTextSize(15);
        l.setTextColor(Color.rgb(104, 241, 175));
        l.setFormSize(30f);
        // 刷新图表
        humidity_mChart.invalidate();
    }
    
    private LineData getLineData_humidity() {
    	String[] xx = {"0:00","4:00", "8:00", "10:00","12:00", "14:00","16:00", "20:00"};
        String[] yy = {"0.25", "0.32","0.23", "0.28", "0.28", "0.26","0.30", "0.28"};

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xx.length; i++) {
            xVals.add(xx[i]);
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < yy.length; i++) {
            yVals.add(new Entry(Float.parseFloat(yy[i]), i));
        }

        LineDataSet set1 = new LineDataSet(yVals, "室内湿度的变化");
        set1.setDrawCubic(true);  //设置曲线为圆滑的线
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //设置包括的范围区域填充颜色
        set1.setDrawCircles(true);  //设置有圆点
        set1.setLineWidth(2f);    //设置线的宽度
        set1.setCircleSize(5f);   //设置小圆的大小
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));    //设置曲线的颜色

        return new LineData(xVals, set1);
    }
    
    
    /**
     * 功耗初始化
     */
    private void power_mChart_initChart() {
        /**
         * ====================1.初始化-自由配置===========================
         */
    	
        // 是否在折线图上添加边框
    	power_mChart.setDrawGridBackground(true);
        power_mChart.setDrawBorders(true);
        // 设置右下角描述
        power_mChart.setDescription("功耗:单位w");
        //设置透明度
        power_mChart.setAlpha(0.8f);
        //设置网格底下的那条线的颜色
        power_mChart.setBorderColor(Color.rgb(213, 216, 214));
        //设置高亮显示
       // power_mChart.setHighlightEnabled(true);
        //设置是否可以触摸，如为false，则不能拖动，缩放等
        power_mChart.setTouchEnabled(true);
        //设置是否可以拖拽
        power_mChart.setDragEnabled(false);
        //设置是否可以缩放
        power_mChart.setScaleEnabled(true);
        //设置是否能扩大扩小
        power_mChart.setPinchZoom(true);
        /**
         * ====================2.布局点添加数据-自由布局===========================
         */
        // 折线图的点，点击战士的布局和数据
        MyMarkView mv = new MyMarkView(this);
        humidity_mChart.setMarkerView(mv);
        // 加载数据
        LineData data = getLineData_power();
        power_mChart.setData(data);
        /**
         * ====================3.x，y动画效果和刷新图表等===========================
         */
        //从X轴进入的动画
        power_mChart.animateX(4000);
        power_mChart.animateY(3000);   //从Y轴进入的动画
        power_mChart.animateXY(3000, 3000);    //从XY轴一起进入的动画
        //设置最小的缩放
        power_mChart.setScaleMinima(0.5f, 1f);
        Legend l = power_mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);  //设置图最下面显示的类型
        l.setTextSize(15);
        l.setTextColor(Color.rgb(104, 241, 175));
        l.setFormSize(30f);
        // 刷新图表
        power_mChart.invalidate();
    }
    
    private LineData getLineData_power() {
    	 String[] xx = {"0:00","4:00", "8:00", "10:00","12:00", "14:00","16:00", "20:00"};
         String[] yy = {"0", "14", "15", "18", "8", "7", "20", "20"};

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xx.length; i++) {
            xVals.add(xx[i]);
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < yy.length; i++) {
            yVals.add(new Entry(Float.parseFloat(yy[i]), i));
        }

        LineDataSet set1 = new LineDataSet(yVals, "功耗的变化");
        set1.setDrawCubic(true);  //设置曲线为圆滑的线
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //设置包括的范围区域填充颜色
        set1.setDrawCircles(true);  //设置有圆点
        set1.setLineWidth(2f);    //设置线的宽度
        set1.setCircleSize(5f);   //设置小圆的大小
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));    //设置曲线的颜色

        return new LineData(xVals, set1);
    }
    
}