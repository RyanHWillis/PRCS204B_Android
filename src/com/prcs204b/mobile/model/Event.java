package com.prcs204b.mobile.model;

public class Event {
	private String mName;
	
	public Event(String name) {
		mName = name;
	}
	
	String getName() {
		return new String(mName);
	}
}
