package com.prcs204b.mobile.app;

import com.prcs204b.mobile.app.MyBookingsFragment.BookingHolder;
import com.prcs204b.mobile.model.Booking;
import com.prcs204b.mobile.model.Event;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class EventsFragment extends Fragment {

	static class EventHolder { 
		ImageView eventIcon;
		TextView  eventTitle;
		
	}
	
	private class EventsAdapter extends ArrayAdapter<Event>
	{
		private Context context;
		private int		layoutResourceId;
		
		public EventsAdapter(Context context, int layoutResourceId) {
			super(context, layoutResourceId);
			
			this.context = context;
			this.layoutResourceId = layoutResourceId;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View row = convertView;
			EventHolder holder;
			
			if(row == null) {
				
				// Get the layout inflater.
				LayoutInflater inflater = 
						((Activity)context).getLayoutInflater();
				
				row = inflater.inflate(layoutResourceId, parent, false);
			
				holder = new EventHolder();
				holder.eventIcon = (ImageView)row.findViewById(R.id.event_event_display);
				holder.eventTitle = (TextView)row.findViewById(R.id.event_event_title);
			
				
				row.setTag(holder);
			
			} else { 				
				holder = (EventHolder)row.getTag();
			}
			
			Event event = getItem(position);
			
			holder.eventTitle.setText( event.getName());
			
			holder.eventIcon.setImageResource(android.R.drawable.btn_star);
			
			return row;
		}
		
	}
	
	private EventsAdapter mEventsAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v =  inflater.inflate(R.layout.fragment_events, container, false);
		return v;
	}
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		
		mEventsAdapter = new EventsAdapter(getActivity(), R.layout.adapter_events_listitem);
		for(int i = 0; i < 5; i++) {
			
			mEventsAdapter.add( new Event("moms the word"));
		}
		ListView lstEvents = (ListView)getActivity().findViewById(R.id.eventsListView);
		lstEvents.setAdapter(mEventsAdapter);
	}
}
