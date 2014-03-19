package com.apps4s.pryexternalstorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	
	boolean mExternalStorageAvailable = false;
	boolean mExternalStorageWritable = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void checkStorage(){
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			//Todo esta correcto
			mExternalStorageAvailable = mExternalStorageWritable = true;
		}else if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
			mExternalStorageAvailable = true;
			mExternalStorageWritable = false;
		} else {
			mExternalStorageAvailable = mExternalStorageWritable = false;
		}
		
		if(mExternalStorageWritable == true){
			File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"MisImagenes");
			path.mkdirs();
			File file = new File(path,"prueba.png");
			
			if (file.exists()) {
				Log.e("ERROR", "Archivo No Existe");
			}
			
			InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
			OutputStream os;
			try {
				os = new FileOutputStream(file);
				byte [] data = new byte [is.available()];
				is.read(data);
				os.write(data);
				is.close();
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
	}

}
