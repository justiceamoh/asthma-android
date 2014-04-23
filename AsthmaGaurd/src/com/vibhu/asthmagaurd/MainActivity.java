package com.vibhu.asthmagaurd;

import java.util.Random;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAB_KEY_INDEX = "tab_key";
	TextView tv1;
	Button bt1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ActionBar
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// create new tabs and and set up the titles of the tabs
		ActionBar.Tab mXTab = actionbar.newTab().setText(
				getString(R.string.main_tabname_x));
		ActionBar.Tab mYTab = actionbar.newTab().setText(
				getString(R.string.main_tabname_y));
		ActionBar.Tab mZTab = actionbar.newTab().setText(
				getString(R.string.main_tabname_z));

		// create the fragments
		Fragment mXFragment = new XFragment();
		Fragment mYFragment = new YFragment();
		Fragment mZFragment = new ZFragment();

		// bind the fragments to the tabs - set up tabListeners for each tab
		mXTab.setTabListener(new MyTabsListener(mXFragment,
				getApplicationContext()));
		mYTab.setTabListener(new MyTabsListener(mYFragment,
				getApplicationContext()));
		mZTab.setTabListener(new MyTabsListener(mZFragment,
				getApplicationContext()));

		// add the tabs to the action bar
		actionbar.addTab(mXTab);
		actionbar.addTab(mYTab);
		actionbar.addTab(mZTab);

		bt1 = (Button) findViewById(R.id.button2);
		bt1.setOnClickListener(this);
		tv1 = (TextView) findViewById(R.id.textView2);

		// restore to navigation
		if (savedInstanceState != null) {
			// Toast.makeText(getApplicationContext(),
			// "tab is " + savedInstanceState.getInt(TAB_KEY_INDEX, 0),
			// Toast.LENGTH_SHORT).show();

			actionbar.setSelectedNavigationItem(savedInstanceState.getInt(
					TAB_KEY_INDEX, 0));
		}

	}

	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button2:
			Random r = new Random();
			int i1 = r.nextInt(80 - 65) + 65;
			tv1 = (TextView) findViewById(R.id.textView2);
			tv1.setText(String.valueOf(i1));
			break;
		}
		;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// Toast.makeText(
		// this,
		// "onSaveInstanceState: tab is"
		// + getActionBar().getSelectedNavigationIndex(),
		// Toast.LENGTH_SHORT).show();
		outState.putInt(TAB_KEY_INDEX, getActionBar()
				.getSelectedNavigationIndex());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

class MyTabsListener implements ActionBar.TabListener {
	public Fragment fragment;
	public Context context;

	public MyTabsListener(Fragment fragment, Context context) {
		this.fragment = fragment;
		this.context = context;

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// Toast.makeText(context, "Reselected!", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// Toast.makeText(context, "Selected!", Toast.LENGTH_SHORT).show();
		ft.replace(R.id.fragment_main_asthma, fragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// Toast.makeText(context, "Unselected!", Toast.LENGTH_SHORT).show();
		ft.remove(fragment);
	}
}
