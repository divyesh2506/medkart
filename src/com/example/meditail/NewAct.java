package com.example.meditail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NewAct extends ListActivity {

	ListView lv;
	ArrayList<String> slist;
	private String path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		path = "/";
	    if (getIntent().hasExtra("path")) {
	      path = getIntent().getStringExtra("path");
	    }
	    setTitle(path);
	    Log.e("mfg", path);
		File dir=new File(path);
		lv=getListView();
		slist=new ArrayList<String>();
		String[] list=dir.list();
		
		if (list != null) {
		      for (String file : list) {
		       slist.add(file);
		      }
		    }
		if(list!=null)
		{
			ArrayAdapter<String> adap=new ArrayAdapter<String>(NewAct.this, android.R.layout.simple_list_item_1,slist);
			lv.setAdapter(adap);
		}
		Toast.makeText(getApplicationContext(), dir+"", Toast.LENGTH_LONG).show();
		//path="Environment.getExternalStorageDirectory().getPath()";
	}    
	
	 @Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Log.e("line1","line1");
	    String filename =(String) lv.getAdapter().getItem(position);
	    Log.e("gotAdapter","gotAdapter");
	    if (path.endsWith("/")) {
	      filename = path + filename;
	    } else {
	      filename = path + File.separator + filename;
	    }
	    Log.e("filename", filename);
	    
	   // File ndir=new File(filename);
	    
	    if (new File(filename).isDirectory()) {
	      Intent intent = new Intent(NewAct.this,NewAct.class);
	      intent.putExtra("path", filename);
	      startActivityForResult(intent, 101);
	    } else {
	     // Toast.makeText(this, filename + " is not a directory", Toast.LENGTH_LONG).show();
	      Toast.makeText(this, filename + " is selected pl. return back to the main page", Toast.LENGTH_LONG).show();
	      File myfile=new File(filename);
	      try {
			FileInputStream fis=new FileInputStream(myfile);
			Bitmap bmp=BitmapFactory.decodeStream(fis);
			if(bmp==null)
				 Toast.makeText(this, filename + " is neither a directory nor a image file", Toast.LENGTH_LONG).show();
			else
			{
				MainActivity.pathway=filename;
				finish();
			}
	      } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("file not found","missing file");
		}
	      
	      
	    }
	  }
	 @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==101)
		{
			if(resultCode==RESULT_OK)
			{
				finish();
			}
		}
	}
}
