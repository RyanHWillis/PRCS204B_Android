package com.prcs204b.mobile.app;

import com.prcs204b.mobile.model.Event;
import com.prcs204b.mobile.model.Booking;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyBookingsFragment extends Fragment {
	
	private class MyBookingsAdapter extends ArrayAdapter<Booking>
	{
		public MyBookingsAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
		}		
	}
	
	
	private MyBookingsAdapter mBookingsAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// If we have no bookings. Set the view to the empty bookings.
		int layout_id = R.layout.fragment_my_bookings;			
		View v = inflater.inflate(layout_id, container, false);		
		return v;
	}
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		
		Event aliceInWonderlandEvent = new Event("Alice in Wonderland");
		Event warOfTheWorldsEvent = new Event("War of the Worlds");
		
		mBookingsAdapter = new MyBookingsAdapter(getActivity(), android.R.layout.simple_list_item_1);
		for(int i = 0; i < 5; i++) {
			mBookingsAdapter.add( new Booking(warOfTheWorldsEvent, 2+i));
			mBookingsAdapter.add( new Booking(aliceInWonderlandEvent, 3+i));
		}
		// Get the list view.
		ListView lstMyBookings = (ListView)getActivity().findViewById(R.id.bookingsListView);
		lstMyBookings.setAdapter(mBookingsAdapter);
	}

}
