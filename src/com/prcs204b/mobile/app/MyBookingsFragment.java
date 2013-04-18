package com.prcs204b.mobile.app;

import com.prcs204b.mobile.model.Event;
import com.prcs204b.mobile.model.Booking;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MyBookingsFragment extends Fragment {
	
	static class BookingHolder { 
		ImageView bookingIcon;
		TextView  bookingTitle;
		TextView  bookingQuantity;
	}
	
	private class MyBookingsAdapter extends ArrayAdapter<Booking>
	{
		private Context context;
		private int		layoutResourceId;
		
		public MyBookingsAdapter(Context context, int layoutResourceId) {
			super(context, layoutResourceId);
			
			this.context = context;
			this.layoutResourceId = layoutResourceId;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View row = convertView;
			BookingHolder holder;
			
			if(row == null) {
				
				// Get the layout inflater.
				LayoutInflater inflater = 
						((Activity)context).getLayoutInflater();
				
				row = inflater.inflate(layoutResourceId, parent, false);
			
				holder = new BookingHolder();
				holder.bookingIcon = (ImageView)row.findViewById(R.id.booked_event_display);
				holder.bookingTitle = (TextView)row.findViewById(R.id.booked_event_title);
				holder.bookingQuantity = (TextView)row.findViewById(R.id.booked_event_qty);
				
				row.setTag(holder);
			
			} else { 				
				holder = (BookingHolder)row.getTag();
			}
			
			Booking booking = getItem(position);
			
			holder.bookingTitle.setText( booking.getEvent().getName() );
			holder.bookingQuantity.setText( "Quantity: " + booking.getQuantity());
			holder.bookingIcon.setImageResource(android.R.drawable.ic_menu_day);
			
			return row;
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
		
		mBookingsAdapter = new MyBookingsAdapter(getActivity(), R.layout.adapter_bookings_listitem);
		for(int i = 0; i < 5; i++) {
			mBookingsAdapter.add( new Booking(warOfTheWorldsEvent, 2+i));
			mBookingsAdapter.add( new Booking(aliceInWonderlandEvent, 3+i));
		}
		// Get the list view.
		ListView lstMyBookings = (ListView)getActivity().findViewById(R.id.bookingsListView);
		lstMyBookings.setAdapter(mBookingsAdapter);
	}

}
