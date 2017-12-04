package com.example.zhaoming;

public class InfoEntity {
	
	private String value;
	private String timestamp;
	private String sensor_id;
	private String device_id;
	public InfoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InfoEntity(String value, String timestamp, String sensor_id,
			String device_id) {
		super();
		this.value = value;
		this.timestamp = timestamp;
		this.sensor_id = sensor_id;
		this.device_id = device_id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSensor_id() {
		return sensor_id;
	}
	public void setSensor_id(String sensor_id) {
		this.sensor_id = sensor_id;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	@Override
	public String toString() {
		return "InfoEntity [value=" + value + ", timestamp=" + timestamp
				+ ", sensor_id=" + sensor_id + ", device_id=" + device_id + "]";
	}
	
	
}
