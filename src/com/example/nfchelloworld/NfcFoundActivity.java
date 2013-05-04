package com.example.nfchelloworld;

import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class NfcFoundActivity extends Activity {

	private final String TAG = getClass().getName();
	
	private TextView nfcDiscoveredTV;
	
	/**
	 * called when the Activity is created
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfc_found);
		
		Log.i(TAG, "onCreate");
		
		initializeEditText();
	}

	/**
	 * called when the OptionsMenu is created
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nfc_found, menu);
		
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
        
        Intent intent = getIntent();
        if (intent == null) {
        	Log.d(TAG, "NFC intent null, onResume");
        	return;
        }
        
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
        	processIntent(getIntent());
        }
    }
    
    /**
     * onResume gets called after this to handle the intent
     */
    @Override
    public void onNewIntent(Intent intent) {
        setIntent(intent);
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
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
        nfcDiscoveredTV.setText(new String(msg.getRecords()[0].getPayload()));
    }
    
    /**
     * initializes the textview
     */
    private void initializeEditText() {
    	nfcDiscoveredTV = (TextView) findViewById(R.id.nfc_discovered_tv);
    }
}
