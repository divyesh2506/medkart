package com.example.meditail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class FirstPage extends Activity {
	ImageView btnForm,imgAddDetail,btnSubmit;
	ImageView imgCall;
	EditText editName,editcontact;

	String str1="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_firstpage);
		btnForm=(ImageView) findViewById(R.id.imageClick);
		editName=(EditText) findViewById(R.id.editName1);
		editcontact=(EditText) findViewById(R.id.editContact1);
		btnSubmit=(ImageView) findViewById(R.id.imageView7);
		imgCall=(ImageView) findViewById(R.id.imageViewCall);
		btnForm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in=new Intent(FirstPage.this, MainActivity.class);
				startActivity(in);
			}
		});

		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				str1=editName.getText().toString()+"\n"+editcontact.getText().toString();
				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL,new String[]{ "divyesh2506@gmail.com"});
				//email.putExtra(Intent.EXTRA_CC, new String[]{ to});
				//email.putExtra(Intent.EXTRA_BCC, new String[]{to});
				email.putExtra(Intent.EXTRA_SUBJECT," Hey pl. call me");
				email.putExtra(Intent.EXTRA_TEXT,  str1);
				//need this to prompts email client only
				email.setType("message/rfc822");

				startActivity(Intent.createChooser(email, "Choose an Email client :"));
			}
		});
		imgAddDetail=(ImageView) findViewById(R.id.imageAddDetail);
		imgAddDetail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Pl. Click a image of the prescription before this",Toast.LENGTH_LONG ).show();
			}


		});
		
		imgCall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:78888"));
				startActivity(callIntent);
			}
		});
	}


}

