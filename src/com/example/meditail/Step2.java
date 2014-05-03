package com.example.meditail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Timer;

import org.json.JSONArray;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TimePicker;
import android.widget.Toast;

public class Step2 extends Activity {
	ImageView imv1;
	File file;
	EditText[] editMedName,editQuant;
	ImageView imgplus;
	TableLayout l1;
	Button btnNew, btnProceed;
	ImageView imgNew,btnExisting;
	TableRow[] tbR;
	int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.step2);
		//btnNew=(Button) findViewById(R.id.btnNew);
		//	imgNew=(ImageView) findViewById(R.id.btnNew);
		//	btnExisting=(ImageView) findViewById(R.id.btnExisting);
		btnProceed=(Button) findViewById(R.id.btnProceed2);
		imv1=(ImageView) findViewById(R.id.imageView1);
		//imgplus=(ImageView) findViewById(R.id.imgADD);
		l1=(TableLayout) findViewById(R.id.Layout101);
		editMedName=new EditText[5];
		editQuant=new EditText[5];
		tbR=new TableRow[10];
		editMedName[0]=(EditText) findViewById(R.id.editMedName0);
		editQuant[0]=(EditText) findViewById(R.id.editMedQuant0);
		editMedName[1]=(EditText) findViewById(R.id.editMedName1);
		editQuant[1]=(EditText) findViewById(R.id.editMedQuant1);
		editMedName[2]=(EditText) findViewById(R.id.editMedName2);
		editQuant[2]=(EditText) findViewById(R.id.editMedQuant2);
		editMedName[3]=(EditText) findViewById(R.id.editMedName3);
		editQuant[3]=(EditText) findViewById(R.id.editMedQuant3);
		editMedName[4]=(EditText) findViewById(R.id.editMedName4);
		editQuant[4]=(EditText) findViewById(R.id.editMedQuant4);

		imv1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getParent());

				// set title
				alertDialogBuilder.setTitle("Your Title");

				// set dialog message
				alertDialogBuilder
				.setMessage("Hey!")
				.setCancelable(false);
				alertDialogBuilder.setPositiveButton("take a new picture", new ClickNewOnClickListener());
				//					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				//						public void onClick(DialogInterface dialog,int id) {
				//							// if this button is clicked, close
				//							// current activity
				//							MainActivity.this.finish();
				//						}
				//					  })
				alertDialogBuilder.setNegativeButton("Upload Existing image",new UploadExistingOnClickListener());

				alertDialogBuilder.setNeutralButton("back",new GobackClickLiostener());
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
			}
		});

		btnProceed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				JSONArray jarr=new JSONArray();

				JSONObject obj=new JSONObject();
				MainActivity.jdeldet="\n\n";
				for(int i=0;i<5;i++)
				{

					//	obj.put("photo",MainActivity.photoarr);
					MainActivity.jdeldet+="MedName ->" +editMedName[i].getText().toString()+"\t";
					MainActivity.jdeldet+="Qunatity ->"+ editQuant[i].getText().toString()+"\n\n";
					jarr.put(obj);

				}
				switchTabInActivity(1);

			}
		});
	}

	private final class UploadExistingOnClickListener implements
	DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			Intent intent = new Intent(Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, 101);
			dialog.cancel();
		}
	}

	private final class ClickNewOnClickListener implements
	DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"meditailtemp"+Calendar.getInstance().get(Calendar.SECOND)+".png");
			
			Uri imuri=Uri.fromFile(file);
			Toast.makeText(getApplicationContext(), file.getPath(), Toast.LENGTH_LONG).show();
			Log.e("path",file.getPath());
			Intent in=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			in.putExtra(MediaStore.EXTRA_OUTPUT,imuri);
			startActivityForResult(in, 102);
			dialog.cancel();
		}
	}

	private final class GobackClickLiostener implements DialogInterface.OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			dialog.cancel();
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Bitmap bm=null;
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==101)
		{
			if (resultCode == RESULT_OK){
				Uri targetUri = data.getData();
				// textTargetUri.setText(targetUri.toString());
				Bitmap bitmap;
				try {
					bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
					imv1.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(requestCode==102)
		{
			if (resultCode == RESULT_OK)
			{
				//				Bundle extras = data.getExtras();
				//				bm = (Bitmap) extras.get("data");
				//				imv2.setImageBitmap(bm);
				BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
				bmpFactoryOptions.inSampleSize = 2;
				Bitmap bmp=BitmapFactory.decodeFile(file.getPath(),bmpFactoryOptions);
				imv1.setImageBitmap(bmp);
				MainActivity.str=file.getPath();
			}

		}

	}
	public void switchTabInActivity(int indexTabToSwitchTo){
		MainActivity parentActivity;
		parentActivity = (MainActivity) this.getParent();
		parentActivity.switchTab(indexTabToSwitchTo);
	}
}
