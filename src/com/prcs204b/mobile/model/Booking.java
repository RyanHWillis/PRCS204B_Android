package com.prcs204b.mobile.model;

public class Booking {
	
	private Event mEvent;
	private int   mQuantity;
	
	public Booking(Event event) {
		this.mEvent = event;
		this.mQuantity = 0;
	}
	
	public Booking(Event event, int quantity) {
		this.mEvent = event;
		this.mQuantity = quantity;
	}
	
	int getQuantity() { 
		return mQuantity;
	}
	
	Event getEvent() {
		return mEvent;
	}
	
	public String toString() {
		return new String(mEvent.getName() + '\n' + "Qty: " + mQuantity);
	}
	
	
	

}
