package com.prcs204b.mobile.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpConnection;
import org.json.JSONException;
import org.json.JSONObject;

import com.prcs204b.mobile.model.Customer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends Activity implements Runnable,
		OnClickListener {

	Button clickedButton = null;
	private static final String activityName = "PRCSB_LogIn";
	Handler msg;
	TextView invalidLogin;
	TextView successTxt;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	
		Button signInBtn = (Button)findViewById(R.id.signInBtn);
		signInBtn.setOnClickListener(this);
		
		msg = new Handler(); 
	}

	String webServiceURL = "http://chidell.com:8080/PRCS204B_Middleware/webresources/customer_login";
	
	@Override
	public void onClick(View v) {
		

		if (v.getId() == R.id.signInBtn)
		{
			Toast.makeText(this, "Connecting", 
					       Toast.LENGTH_SHORT).show();
			
			
			//**************************************************************//
			// Start the Thread.  We could say runner.run(), which would 	//
			// run its run() method synchronously.  However, runner.start() //
			// invoked the run() method asynchronously.						//
			//**************************************************************//
		
			Thread runner = new Thread(this);
			runner.start();
			

			Log.i("Simple Web Service translate", "Button pressed");
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Log.d(activityName, "starting login thread");
		// Set up the connection
			HttpURLConnection con = null;

		try {
			
			// This string will contain the json object containing customer
			// details
			String responseString = "";
			
			EditText emailEdit = (EditText)findViewById(R.id.emailaddressEditText);
			EditText passwordEdit = (EditText)findViewById(R.id.passwordEditTxt);

			String customerEmail = emailEdit.getText().toString();
			String customerPassword = passwordEdit.getText().toString();

			String urlStr = webServiceURL + "?user_email=" + customerEmail
					+ "&password=" + customerPassword;

			// Check if this sends to webservice!
			Log.d(activityName, "sendthing this to webservice:" + urlStr);

			URL url = new URL(urlStr);

			con = (HttpURLConnection) url.openConnection();
			con.setReadTimeout(10000);
			con.setConnectTimeout(15000);
			con.setRequestMethod("GET");
			con.setChunkedStreamingMode(0);
			con.setDoInput(true);
			con.connect();

			//Now read the response!
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));

			responseString = in.readLine();
			in.close();
			con.disconnect();
			
			JSONObject customerObj = new JSONObject(responseString);
			
			if (customerObj.length() == 0)
			{
				Log.d(activityName, "Invalid!");
				
				
				msg.post(new Runnable() {
					
					@Override
					public void run() {
						
						Toast toast = Toast.makeText(LogInActivity.this, "Invalid Login, Please try again!", Toast.LENGTH_SHORT);
						toast.show();
						
					}
				});
			}
			else
			{
				Customer c = new Customer (customerObj.getInt("customerId"));
				
				c.setTitle( customerObj.getString("title"));
				c.setFirstName( customerObj.getString("firstName"));
				c.setSurname( customerObj.getString("surname"));
				c.setAddressLine1( customerObj.getString("addressLine1"));
				c.setAddressLine2( customerObj.getString("addressLine2"));
				c.setCity( customerObj.getString("city"));
				c.setPostcode( customerObj.getString("postCode"));
				c.setAccountActive(customerObj.getInt("accountActive"));
				c.setEmail( customerObj.getString("email"));
				c.setTelephoneNumber( customerObj.getString("telNo"));
				
				Log.d(activityName, "Success!");
				
				msg.post(new Runnable() {
					
					@Override
					public void run() {

						Toast toast = Toast.makeText(LogInActivity.this, "Success!", Toast.LENGTH_SHORT);
						toast.show();
						
						Intent launchMain = new Intent(LogInActivity.this, MainActivity.class);
						LogInActivity.this.startActivity(launchMain);
						
					}
				});
				
				
			}

				
			

		} catch (IOException e) {
				
		}
		
		catch (JSONException e) {
			
		}
		
		finally 
	       {
	          if (con != null) {
	             con.disconnect();
	          }
	       }

		Log.i(activityName, "Thread finished");

	}
	
	
}
