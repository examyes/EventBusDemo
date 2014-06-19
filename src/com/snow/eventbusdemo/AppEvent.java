package com.snow.eventbusdemo;

public class AppEvent {
	
	public static final int TEST = 1;
	private int type;

	public AppEvent(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
