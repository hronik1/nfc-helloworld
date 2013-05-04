package com.example.nfchelloworld;


import java.nio.charset.Charset;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class HelloActivity extends Activity implements CreateNdefMessageCallback {

	private final String TAG = getClass().getName();
	
	private EditText messageET;
	
	private NfcAdapter mNfcAdapter;
	
	/**
	 * called when activity is created
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello);
		
		Log.i(TAG, "onCreate");
		
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        mNfcAdapter.setNdefPushMessageCallback(this, this);
        
		initializeEditTexts();
	}

	/**
	 * called when optionmenu is created
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hello, menu);
		
		Log.i(TAG, "onCreateOptionsMenu");
		return true;
	}

    /**
     * called when activity is restarted
     */
    @Override
    public void onRestart() {
        super.onRestart();

        Log.i(TAG, "onRestart");
    }
    
    /**
     * called when activity is starting
     */
    @Override
    public void onStart() {
        super.onStart();

        Log.i(TAG, "onStart");
    }
    
    /**
     * called when activity is stopped
     */
	@Override
	protected void onStop()
	{
		super.onStop();		

		Log.i(TAG, "onStop");
	}
    
    /**
     * called when activity resumes
     */
    @Override
    public void onResume() {
        super.onResume();
        
        Log.i(TAG, "onResume");
    }
    
    /**
     * called when activity is paused
     */
    @Override
    public void onPause() {
        super.onPause();
       
        Log.i(TAG, "onPause" + (isFinishing() ? " Finishing" : " Not Finishing"));
    }
    
    /**
     * called when activity is destroyed
     */
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	
    	Log.i(TAG, "onDestroy");
    	
    }
    
    /**
     * connects edittexts to appropriate views
     */
    private void initializeEditTexts() {
    	messageET = (EditText)findViewById(R.id.hello_et);
    }
    
    /** CreateNDEFCallback methods **/
    
    /**
     * callback for creating an NDEFmessagge when in range
     * 
     */
    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
    	Log.i(TAG, "createNdefMessage called");
    	
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] { new NdefRecord(
            		    NdefRecord.TNF_MIME_MEDIA ,
            		    getString(R.string.ndef_domain).getBytes(Charset.forName("US-ASCII")),
            		    new byte[0], messageET.getText().toString().getBytes(Charset.forName("US-ASCII")))
         /**
          * The Android Application Record (AAR) is commented out. When a device
          * receives a push with an AAR in it, the application specified in the AAR
          * is guaranteed to run. The AAR overrides the tag dispatch system.
          * You can add it back in to guarantee that this
          * activity starts when receiving a beamed message. For now, this code
          * uses the tag dispatch system.
          */
          //,NdefRecord.createApplicationRecord("com.example.android.beam")
    	});
        return msg;
    }

}
