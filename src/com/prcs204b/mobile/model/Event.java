package com.prcs204b.mobile.model;

public class Event {
	private String mName;
	
	public Event(String name) {
		mName = name;
	}
	
	public String getName() {
		return new String(mName);
	}
	
	public String toString() {
		return new String(mName);
	}
}
