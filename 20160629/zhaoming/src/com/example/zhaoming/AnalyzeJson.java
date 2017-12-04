package com.example.zhaoming;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnalyzeJson {

	public static ArrayList<HashMap<String, Object>> Analysis(String jsonStr)
            throws JSONException {
        /******************* ���� ***********************/
        JSONArray jsonArray = null;
        // ��ʼ��list�������
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        jsonArray = new JSONArray(jsonStr);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            // ��ʼ��map�������
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("value", jsonObject.getDouble("value"));
            map.put("timestamp", jsonObject.getString("timestamp"));
            map.put("sensor_id", jsonObject.getString("sensor_id"));
            map.put("device_id", jsonObject.getString("device_id"));
            list.add(map);
        }
        return list;
    }
}
