package com.example.meditail;



import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	public static String str,jdeldet="";
	
	public static String pathway;
	public static byte[] photoarr;
	TabHost tabHost;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		tabHost =getTabHost();

		// Tab for Step1
		TabSpec spec1 = tabHost.newTabSpec("Step1");
		// setting Title and Icon for the Tab
		//spec1.setIndicator("", getResources().getDrawable(R.drawable.step2));
		Intent Step1Intent = new Intent(this, Step2.class);
		spec1.setContent(Step1Intent);

		// Tab for Step2
		TabSpec spec2 = tabHost.newTabSpec("Step2");        
		//spec2.setIndicator("", getResources().getDrawable(R.drawable.step1));
		Intent Step2Intent = new Intent(this, Step1.class);
		spec2.setContent(Step2Intent);


		//        // Tab for Step3
		//        TabSpec spec3 = tabHost.newTabSpec("Step3");
		//        spec3.setIndicator("Step3", getResources().getDrawable(R.drawable.step3));
		//        Intent Step3Intent = new Intent(this, Step3.class);
		//        spec3.setContent(Step3Intent);

		//File file = new File(getApplication().getApplicationInfo().dataDir);
		//file.delete();





		// Adding all TabSpec to TabHost
		tabHost.addTab(spec1); // Adding Step1 tab
		tabHost.addTab(spec2); // Adding Step2 tab
		//        tabHost.addTab(spec3); // Adding Step3 tab-8


	}

	public void switchTab(int tab){
		tabHost.setCurrentTab(tab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		CreateMenu(menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		finish();
		
		return true;
	}


	@SuppressLint("NewApi")
	private void CreateMenu(Menu menu)
	{
		MenuItem mnu1 = menu.add(0, 0, 0, "Item 1");
		{
			mnu1.setIcon(R.drawable.ic_menu_home);
			mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		}
	}

}
