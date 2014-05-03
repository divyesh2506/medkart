package com.example.meditail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Step1 extends Activity {

	EditText editName,editContact,editAddress,editDrName,editEmail,editPin; 
	Button btnConfirm;
	String jdata;
	String strsend;
	InputStream inputStream;
	MultipartEntity mpEntity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.step1);
		editName=(EditText) findViewById(R.id.editName);
		editContact=(EditText) findViewById(R.id.editContact);
		editAddress=(EditText) findViewById(R.id.editAddress);
		editEmail=(EditText) findViewById(R.id.editEmail);
		editDrName=(EditText) findViewById(R.id.editDrName);
		editPin=(EditText) findViewById(R.id.editPincode);
		btnConfirm=(Button) findViewById(R.id.btnConfirm);

		btnConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {	

				if(editName.getText().toString()==null || (editContact.getText().toString().length()!=10 && editContact.getText().toString().length()!=11))
				{
					Toast.makeText(getApplicationContext(), "Either name field is empty or Contact no. is wrong", Toast.LENGTH_LONG).show();
				}
				else
				{
					
					 File file1 = new File(MainActivity.str);

					    mpEntity = new MultipartEntity();
					    ContentBody cbFile = new FileBody(file1, "image/jpeg");
					    mpEntity.addPart("userfile", cbFile);

					JSONArray jarr=new JSONArray();

					JSONObject obj=new JSONObject();
					
					strsend="Name:  "+editName.getText().toString()+"\n"+ "Adress:  "+editAddress.getText().toString()+"\n"+ "Contact:  "+editContact.getText().toString()+"\n"+	"Email:  "+editEmail.getText().toString()+"\n"+"Dr. Name:  "+editDrName.getText().toString();				
					
					
					File file=new File(Environment.getExternalStorageDirectory(),"jsonmed.txt");
					try {
						FileWriter fw=new FileWriter(file);
						for(int i=0;i<jarr.length();i++)
						{
							String a=jarr.getString(i);
							System.out.println(a);
							Log.e("array", a);
							fw.write(a);
						}
	
					}
					catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					catch (Exception e) {
						// TODO: handle exception
						Log.e("error", e.toString());
					}
				}
				Intent email = new Intent(Intent.ACTION_SEND);
				  email.putExtra(Intent.EXTRA_EMAIL,new String[]{ "divyesh2506@gmail.com"});
				  //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
				  //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
				  email.putExtra(Intent.EXTRA_SUBJECT," I want to order these medicines");
				  email.putExtra(Intent.EXTRA_TEXT,  strsend+"\n"+MainActivity.jdeldet);
				  email.putExtra(Intent.EXTRA_STREAM, Uri.parse(MainActivity.str));
				  //need this to prompts email client only
				  email.setType("message/rfc822");
	 
				  startActivity(Intent.createChooser(email, "Choose an Email client :"));
			}
			
		});



	}


}