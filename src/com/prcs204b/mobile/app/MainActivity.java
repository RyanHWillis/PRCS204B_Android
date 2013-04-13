package com.prcs204b.mobile.app;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity 
{	
	private MainPagerAdapter mPagerAdapter;
	
	private static final String TAB_POSITION_NAME = "MainTabPosition"; 

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set the content view	
		setContentView(R.layout.layout_main_activity);
		
		// Create the ViewPager adapter
		mPagerAdapter = new MainPagerAdapter(
				getSupportFragmentManager());
		
		// Get the view pager
		ViewPager mViewPager = (ViewPager)findViewById(R.id.pager);
		mViewPager.setOnPageChangeListener(
				new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {						
						getActionBar().setSelectedNavigationItem(position);
						
					}
				});
		
		// Use our pager adapter
		mViewPager.setAdapter(mPagerAdapter);
		
		// Set the Action Bar
		final ActionBar actionBar = getActionBar();		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		
		// Add the tabs
		Tab tab =  actionBar.newTab()
				.setText("My Bookings")				
				.setTabListener( new MainTabListener(mViewPager));
		actionBar.addTab(tab);
		
		tab = actionBar.newTab()
				.setText("Events")
				.setTabListener( new MainTabListener(mViewPager));
		actionBar.addTab(tab);
		
		tab = actionBar.newTab()
				.setText("Special Offers")
				.setTabListener( new MainTabListener(mViewPager));
		actionBar.addTab(tab);
		
		tab = actionBar.newTab()
				.setText("About")
				.setTabListener( new MainTabListener(mViewPager));
		actionBar.addTab(tab);
		
		
		// Restore any previous state if this application was paused.
		if(savedInstanceState != null) {
			// Restore previous tab selection.
			int tabPos = savedInstanceState.getInt(TAB_POSITION_NAME);
			actionBar.setSelectedNavigationItem(tabPos);
		}
	}
	
	public void onSaveInstanceState(Bundle outState) {
		
		// Save the tab position
		final int tabPos = getActionBar().getSelectedNavigationIndex();
		outState.putInt( TAB_POSITION_NAME, tabPos);
	}
	
	public void onDestroy() {
		super.onDestroy();
	}
	
	
	private class MainTabListener implements ActionBar.TabListener {
		private ViewPager mViewPager;
		
		public MainTabListener(ViewPager viewPager) {
			mViewPager = viewPager;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			mViewPager.setCurrentItem(tab.getPosition());			
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
	}
	
	private class MainPagerAdapter extends
		FragmentStatePagerAdapter {		
		
		private static final int NUM_FRAGMENTS = 4;

		public MainPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			
			Fragment f = null;
			Bundle args = new Bundle();
			
			// Create the appropriate fragment.
			switch(i) {
			case 0:
				f = new MyBookingsFragment();
				break;
			case 1:
				f = new EventsFragment();
				break;	
			case 2:
				f = new SpecialOffersFragment();
				break;
			case 3:
				f = new AboutFragment();
				break;
			}
			
			f.setArguments(args);
			return f;
		}

		@Override
		public int getCount() {			
			return NUM_FRAGMENTS;
		}
		
	}
}
