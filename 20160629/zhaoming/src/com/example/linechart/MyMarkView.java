package com.example.linechart;

import android.content.Context;
import android.widget.TextView;

import com.example.zhaoming.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;

public class MyMarkView extends MarkerView {
    private TextView tvMarkText;

    public MyMarkView(Context context) {
        super(context, R.layout.mark_view);
        tvMarkText = (TextView) findViewById(R.id.tvMarkText);
    }

    @Override
    public void refreshContent(Entry entry, Highlight highlight) {
        if (entry instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) entry;
            tvMarkText.setText("" + Utils.formatNumber(ce.getHigh(), 0, true));
        } else {
            tvMarkText.setText("" + entry.getVal());
        }
    }

	@Override
	public int getXOffset(float xpos) {
		// TODO Auto-generated method stub
		return -(getWidth() / 2);
	}

	@Override
	public int getYOffset(float ypos) {
		// TODO Auto-generated method stub
		return -getHeight() - UnitUtils.dp2px(getContext(), 2);
	}
}