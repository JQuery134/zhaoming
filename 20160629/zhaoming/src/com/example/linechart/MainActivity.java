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
        //�¶�
        temperature_mChart=(LineChart) findViewById(R.id.temperature_lineChart);
        //ʪ��
        humidity_mChart=(LineChart) findViewById(R.id.humidity_lineChart);
        //����
        power_mChart=(LineChart) findViewById(R.id.power_lineChart);
        //�¶ȵ���ش���
        temperature_mChart_initChart();
        //ʪ�ȵ���ش���
        humidity_mChart_initChart();
        //���ĵ���ش���
        power_mChart_initChart();
    }

    /**
     * �¶����ݳ�ʼ��
     * 1.��ʼ��LineChart
     * 2.�������x��y������
     * 3.ˢ��ͼ��
     */
    private void temperature_mChart_initChart() {
        /**
         * ====================1.��ʼ��-��������===========================
         */
    	
        // �Ƿ�������ͼ����ӱ߿�
        temperature_mChart.setDrawGridBackground(true);
        temperature_mChart.setDrawBorders(true);
        // �������½�����
        temperature_mChart.setDescription("�¶�:��λ���϶�");
        //����͸����
        temperature_mChart.setAlpha(0.8f);
        //����������µ������ߵ���ɫ
        temperature_mChart.setBorderColor(Color.rgb(213, 216, 214));
        //���ø�����ʾ
       // temperature_mChart.setHighlightEnabled(true);
        //�����Ƿ���Դ�������Ϊfalse�������϶������ŵ�
        temperature_mChart.setTouchEnabled(true);
        //�����Ƿ������ק
        temperature_mChart.setDragEnabled(false);
        //�����Ƿ��������
        temperature_mChart.setScaleEnabled(true);
        //�����Ƿ���������С
        temperature_mChart.setPinchZoom(true);
        /**
         * ====================2.���ֵ��������-���ɲ���===========================
         */
        // ����ͼ�ĵ㣬���սʿ�Ĳ��ֺ�����
        MyMarkView mv = new MyMarkView(this);
        temperature_mChart.setMarkerView(mv);
        // ��������
        LineData data = getLineData_temperature();
        temperature_mChart.setData(data);
        /**
         * ====================3.x��y����Ч����ˢ��ͼ���===========================
         */
        //��X�����Ķ���
        temperature_mChart.animateX(4000);
        temperature_mChart.animateY(3000);   //��Y�����Ķ���
        temperature_mChart.animateXY(3000, 3000);    //��XY��һ�����Ķ���
        //������С������
        temperature_mChart.setScaleMinima(0.5f, 1f);
        Legend l = temperature_mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);  //����ͼ��������ʾ������
        l.setTextSize(15);
        l.setTextColor(Color.rgb(104, 241, 175));
        l.setFormSize(30f);
        // ˢ��ͼ��
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

        LineDataSet set1 = new LineDataSet(yVals, "�����¶ȵı仯");
        set1.setDrawCubic(true);  //��������ΪԲ������
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //���ð����ķ�Χ���������ɫ
        set1.setDrawCircles(true);  //������Բ��
        set1.setLineWidth(2f);    //�����ߵĿ��
        set1.setCircleSize(5f);   //����СԲ�Ĵ�С
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));    //�������ߵ���ɫ

        return new LineData(xVals, set1);
    }
    
    /**
     * ʪ�ȳ�ʼ��
     */
    private void humidity_mChart_initChart() {
        /**
         * ====================1.��ʼ��-��������===========================
         */
    	
        // �Ƿ�������ͼ����ӱ߿�
    	humidity_mChart.setDrawGridBackground(true);
        humidity_mChart.setDrawBorders(true);
        // �������½�����
        humidity_mChart.setDescription("ʪ��");
        //����͸����
        humidity_mChart.setAlpha(0.8f);
        //����������µ������ߵ���ɫ
        humidity_mChart.setBorderColor(Color.rgb(213, 216, 214));
        //���ø�����ʾ
       // humidity_mChart.setHighlightEnabled(true);
        //�����Ƿ���Դ�������Ϊfalse�������϶������ŵ�
        humidity_mChart.setTouchEnabled(true);
        //�����Ƿ������ק
        humidity_mChart.setDragEnabled(false);
        //�����Ƿ��������
        humidity_mChart.setScaleEnabled(true);
        //�����Ƿ���������С
        humidity_mChart.setPinchZoom(true);
        /**
         * ====================2.���ֵ��������-���ɲ���===========================
         */
        // ����ͼ�ĵ㣬���սʿ�Ĳ��ֺ�����
        MyMarkView mv = new MyMarkView(this);
        humidity_mChart.setMarkerView(mv);
        // ��������
        LineData data = getLineData_humidity();
        humidity_mChart.setData(data);
        /**
         * ====================3.x��y����Ч����ˢ��ͼ���===========================
         */
        //��X�����Ķ���
        humidity_mChart.animateX(4000);
        humidity_mChart.animateY(3000);   //��Y�����Ķ���
        humidity_mChart.animateXY(3000, 3000);    //��XY��һ�����Ķ���
        //������С������
        humidity_mChart.setScaleMinima(0.5f, 1f);
        Legend l = humidity_mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);  //����ͼ��������ʾ������
        l.setTextSize(15);
        l.setTextColor(Color.rgb(104, 241, 175));
        l.setFormSize(30f);
        // ˢ��ͼ��
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

        LineDataSet set1 = new LineDataSet(yVals, "����ʪ�ȵı仯");
        set1.setDrawCubic(true);  //��������ΪԲ������
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //���ð����ķ�Χ���������ɫ
        set1.setDrawCircles(true);  //������Բ��
        set1.setLineWidth(2f);    //�����ߵĿ��
        set1.setCircleSize(5f);   //����СԲ�Ĵ�С
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));    //�������ߵ���ɫ

        return new LineData(xVals, set1);
    }
    
    
    /**
     * ���ĳ�ʼ��
     */
    private void power_mChart_initChart() {
        /**
         * ====================1.��ʼ��-��������===========================
         */
    	
        // �Ƿ�������ͼ����ӱ߿�
    	power_mChart.setDrawGridBackground(true);
        power_mChart.setDrawBorders(true);
        // �������½�����
        power_mChart.setDescription("����:��λw");
        //����͸����
        power_mChart.setAlpha(0.8f);
        //����������µ������ߵ���ɫ
        power_mChart.setBorderColor(Color.rgb(213, 216, 214));
        //���ø�����ʾ
       // power_mChart.setHighlightEnabled(true);
        //�����Ƿ���Դ�������Ϊfalse�������϶������ŵ�
        power_mChart.setTouchEnabled(true);
        //�����Ƿ������ק
        power_mChart.setDragEnabled(false);
        //�����Ƿ��������
        power_mChart.setScaleEnabled(true);
        //�����Ƿ���������С
        power_mChart.setPinchZoom(true);
        /**
         * ====================2.���ֵ��������-���ɲ���===========================
         */
        // ����ͼ�ĵ㣬���սʿ�Ĳ��ֺ�����
        MyMarkView mv = new MyMarkView(this);
        humidity_mChart.setMarkerView(mv);
        // ��������
        LineData data = getLineData_power();
        power_mChart.setData(data);
        /**
         * ====================3.x��y����Ч����ˢ��ͼ���===========================
         */
        //��X�����Ķ���
        power_mChart.animateX(4000);
        power_mChart.animateY(3000);   //��Y�����Ķ���
        power_mChart.animateXY(3000, 3000);    //��XY��һ�����Ķ���
        //������С������
        power_mChart.setScaleMinima(0.5f, 1f);
        Legend l = power_mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);  //����ͼ��������ʾ������
        l.setTextSize(15);
        l.setTextColor(Color.rgb(104, 241, 175));
        l.setFormSize(30f);
        // ˢ��ͼ��
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

        LineDataSet set1 = new LineDataSet(yVals, "���ĵı仯");
        set1.setDrawCubic(true);  //��������ΪԲ������
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //���ð����ķ�Χ���������ɫ
        set1.setDrawCircles(true);  //������Բ��
        set1.setLineWidth(2f);    //�����ߵĿ��
        set1.setCircleSize(5f);   //����СԲ�Ĵ�С
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));    //�������ߵ���ɫ

        return new LineData(xVals, set1);
    }
    
}