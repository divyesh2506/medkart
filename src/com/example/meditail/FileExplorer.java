package com.example.meditail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import android.os.Bundle;
import android.os.Environment;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FileExplorer extends ListActivity {

	ListView lv;
	ArrayList<String> flist;
	private String path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		path=Environment.getExternalStorageDirectory().getPath();
	    
	    
		File dir=new File(path);
		lv=getListView();
		flist=new ArrayList<String>();
		String[] list=dir.list();
		if (list != null) {
		      for (String file : list) {
		        if (!file.startsWith(".")) {
		          flist.add(file);
		        }
		      }
		    }
		Collections.sort(flist);
		
		ArrayAdapter<String> adap=new ArrayAdapter<String>(FileExplorer.this, android.R.layout.simple_list_item_1 , flist);
		lv.setAdapter(adap);
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
	      Intent intent = new Intent(FileExplorer.this,NewAct.class);
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
	 
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			if(resultCode==RESULT_OK)
			{
				finish();
			}
		}
}
