package com.n_puzzle;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

public class LauncherActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private Board board;
	private Records record;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private Gallery gallery;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		FragmentManager fragmentManager = getFragmentManager();
		board = (Board) fragmentManager.findFragmentById(R.id.fragment_board);
		gallery = (Gallery) fragmentManager
				.findFragmentById(R.id.fragment_gallery);
		record = (Records) fragmentManager
				.findFragmentById(R.id.fragment_profile);
		FragmentTransaction trans = fragmentManager.beginTransaction();
		trans.hide(gallery);
		trans.hide(record);
		trans.show(board);
		trans.commit();
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();

		FragmentTransaction trans = fragmentManager.beginTransaction();
		if (gallery != null && record != null && board != null) {
			switch (position) {
			case 0:
				mTitle = getString(R.string.title_start_game);
				trans.hide(gallery);
				trans.hide(record);
				trans.show(board);
				trans.commit();
				break;
			case 1:
				mTitle = getString(R.string.title_user_information);
				trans.hide(board);
				trans.hide(gallery);
				trans.show(record);
				trans.commit();
				break;
			case 2:
				mTitle = getString(R.string.title_select_image);
				trans.hide(board);
				trans.hide(record);
				trans.show(gallery);
				trans.commit();
				break;
			}
		}

	}

	/*
	 * public void onSectionAttached(int number) { FragmentManager
	 * fragmentManager = getFragmentManager(); switch (number) { case 1: if
	 * (board == null) board = (Board) Board.newInstance(3);
	 * fragmentManager.beginTransaction().replace(R.id.container, board)
	 * .commit(); mTitle = getString(R.string.title_start_game); break; case 2:
	 * if (record == null) record = (Records) Records.newInstance();
	 * fragmentManager.beginTransaction().replace(R.id.container, record)
	 * .commit(); mTitle = getString(R.string.title_user_information); break;
	 * case 3: SharedPreferences sharedPref =
	 * getPreferences(Context.MODE_PRIVATE); String path =
	 * sharedPref.getString(getString(R.string.path), ""); gallery =
	 * Gallery.newInstance(path);
	 * fragmentManager.beginTransaction().replace(R.id.container, gallery)
	 * .commit(); mTitle = getString(R.string.title_select_image); break;
	 * 
	 * } }
	 */

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.launcher, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*
	 * public static class PlaceholderFragment extends Fragment {
	 *//**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	/*
	 * private static final String ARG_SECTION_NUMBER = "section_number";
	 *//**
	 * Returns a new instance of this fragment for the given section number.
	 */
	/*
	 * public static PlaceholderFragment newInstance(int sectionNumber) {
	 * PlaceholderFragment fragment = new PlaceholderFragment(); Bundle args =
	 * new Bundle(); args.putInt(ARG_SECTION_NUMBER, sectionNumber);
	 * fragment.setArguments(args); return fragment; }
	 * 
	 * public PlaceholderFragment() { }
	 * 
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) { View rootView =
	 * inflater.inflate(R.layout.fragment_launcher, container, false); return
	 * rootView; }
	 * 
	 * @Override public void onAttach(Activity activity) {
	 * super.onAttach(activity); ((LauncherActivity)
	 * activity).onSectionAttached(getArguments() .getInt(ARG_SECTION_NUMBER));
	 * } }
	 */
}
