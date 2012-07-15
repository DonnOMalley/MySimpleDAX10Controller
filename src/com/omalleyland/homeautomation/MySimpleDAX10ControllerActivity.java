package com.omalleyland.homeautomation;

import java.io.BufferedReader; 
import java.io.InputStream; 
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MySimpleDAX10ControllerActivity extends Activity
{

	Button entranceOnButton;
	Button entranceOffButton;
	Button entranceDimButton;
	Button entranceBrightButton;
	Button livingRoomOnButton;
	Button livingRoomOffButton;
	Button livingRoomDimButton;
	Button livingRoomBrightButton;
	Button diningRoomOnButtton;
	Button diningRoomOffButton;
	Button diningRoomDimButton;
	Button diningRoomBrightButton;
	Button kitchenOnButton;
	Button kitchenOffButton;
	Button kitchenDimButton;
	Button kitchenBrightButton;
	Button donnsOfficeOnButton;
	Button donnsOfficeOffButton;
	Button donnsOfficeDimButton;
	Button donnsOfficeBrightButton;
	Button officeCameraOnButton;
	Button officeCameraOffButton;
	Button officeCameraDimButton;
	Button officeCameraBrightButton;
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		entranceOnButton = (Button)findViewById(R.id.entranceOnButton);
		entranceOnButton.setOnClickListener(new entranceOnClick());
				
		entranceOffButton = (Button)findViewById(R.id.entranceOffButton);
		entranceOffButton.setOnClickListener(new entranceOffClick());

		entranceDimButton = (Button)findViewById(R.id.entranceDimButton);
		entranceDimButton.setOnClickListener(new entranceDimClick());

		entranceBrightButton = (Button)findViewById(R.id.entranceBrightButton);
		entranceBrightButton.setOnClickListener(new entranceBrightClick());
		
		livingRoomOnButton = (Button)findViewById(R.id.livingRoomOnButton);
		livingRoomOnButton.setOnClickListener(new livingRoomOnClick());
		
		livingRoomOffButton = (Button)findViewById(R.id.livingRoomOffButton);
		livingRoomOffButton.setOnClickListener(new livingRoomOffClick());

		livingRoomDimButton = (Button)findViewById(R.id.livingRoomDimButton);
		livingRoomDimButton.setOnClickListener(new livingRoomDimClick());

		livingRoomBrightButton = (Button)findViewById(R.id.livingRoomBrightButton);
		livingRoomBrightButton.setOnClickListener(new livingRoomBrightClick());
		
		diningRoomOnButtton = (Button)findViewById(R.id.diningRoomOnButton);
		diningRoomOnButtton.setOnClickListener(new diningRoomOnClick());
		
		diningRoomOffButton = (Button)findViewById(R.id.diningRoomOffButton);
		diningRoomOffButton.setOnClickListener(new diningRoomOffClick());

		diningRoomDimButton = (Button)findViewById(R.id.diningRoomDimButton);
		diningRoomDimButton.setOnClickListener(new diningRoomDimClick());

		diningRoomBrightButton = (Button)findViewById(R.id.diningRoomBrightButton);
		diningRoomBrightButton.setOnClickListener(new diningRoomBrightClick());
		
		kitchenOnButton = (Button)findViewById(R.id.kitchenOnButton);
		kitchenOnButton.setOnClickListener(new kitchenOnClick());
		
		kitchenOffButton = (Button)findViewById(R.id.kitchenOffButton);
		kitchenOffButton.setOnClickListener(new kitchenOffClick());

		kitchenDimButton = (Button)findViewById(R.id.kitchenDimButton);
		kitchenDimButton.setOnClickListener(new kitchenDimClick());

		kitchenBrightButton = (Button)findViewById(R.id.kitchenBrightButton);
		kitchenBrightButton.setOnClickListener(new kitchenBrightClick());
		
		donnsOfficeOnButton = (Button)findViewById(R.id.donnsOfficeOnButton);
		donnsOfficeOnButton.setOnClickListener(new donnsOfficeOnClick());
		
		donnsOfficeOffButton = (Button)findViewById(R.id.donnsOfficeOffButton);
		donnsOfficeOffButton.setOnClickListener(new donnsOfficeOffClick());

		donnsOfficeDimButton = (Button)findViewById(R.id.donnsOfficeDimButton);
		donnsOfficeDimButton.setOnClickListener(new donnsOfficeDimClick());

		donnsOfficeBrightButton = (Button)findViewById(R.id.donnsOfficeBrightButton);
		donnsOfficeBrightButton.setOnClickListener(new donnsOfficeBrightClick());
		
		officeCameraOnButton = (Button)findViewById(R.id.officeCameraOnButton);
		officeCameraOnButton.setOnClickListener(new officeCameraOnClick());
		
		officeCameraOffButton = (Button)findViewById(R.id.officeCameraOffButton);
		officeCameraOffButton.setOnClickListener(new officeCameraOffClick()); 

		officeCameraDimButton = (Button)findViewById(R.id.officeCameraDimButton);
		officeCameraDimButton.setOnClickListener(new officeDimClick());

		officeCameraBrightButton = (Button)findViewById(R.id.officeCameraBrightButton);
		officeCameraBrightButton.setOnClickListener(new officeBrightClick());
		
    }
		
  //Send url string to the web server over HTTP
  	private String ExecuteHTTPRequest(String url) throws Exception {
  		InputStream content = null;
  		String str;
  		String line;
  		BufferedReader rd;
  		StringBuilder sb;		
  		
  		try {
  			HttpGet httpGet = new HttpGet(url);
  			HttpClient httpclient = new DefaultHttpClient();			
  			
  			// Execute HTTP Get Request
  			HttpResponse response = httpclient.execute(httpGet);				
  			content = response.getEntity().getContent();
      } 
  		catch (Exception e) {
  			Toast.makeText(MySimpleDAX10ControllerActivity.this, "ExecuteHTTPRequest EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show();
  		}

  		rd = new BufferedReader(new InputStreamReader(content), 4096);
  		sb =  new StringBuilder();
  		while ((line = rd.readLine()) != null) {
  				sb.append(line);
  		}
  		rd.close();
  		str = sb.toString();
  		
  		return str;
  	}

	public class entranceOnClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "A"; 
				String UnitCode = "7"; 
				String DigitalValue = "1"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class entranceOffClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";    
				String RoomName = "A"; 
				String UnitCode = "7"; 
				String DigitalValue = "0"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class entranceDimClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "A"; 
				String UnitCode = "7"; 
				String DigitalValue = "3"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class entranceBrightClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "A"; 
				String UnitCode = "7"; 
				String DigitalValue = "2"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class livingRoomOnClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "A"; 
				String UnitCode = "0"; 
				String DigitalValue = "1"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class livingRoomOffClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";    
				String RoomName = "A"; 
				String UnitCode = "0"; 
				String DigitalValue = "0"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class livingRoomDimClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "A"; 
				String UnitCode = "0"; 
				String DigitalValue = "3"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class livingRoomBrightClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "A"; 
				String UnitCode = "0"; 
				String DigitalValue = "2"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class diningRoomOnClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "A"; 
				String UnitCode = "1"; 
				String DigitalValue = "1"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class diningRoomOffClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";    
				String RoomName = "A"; 
				String UnitCode = "1"; 
				String DigitalValue = "0"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class diningRoomDimClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "A"; 
				String UnitCode = "1"; 
				String DigitalValue = "3"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class diningRoomBrightClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "A"; 
				String UnitCode = "1"; 
				String DigitalValue = "2"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class kitchenOnClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "A"; 
				String UnitCode = "2"; 
				String DigitalValue = "1"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class kitchenOffClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";    
				String RoomName = "A"; 
				String UnitCode = "2"; 
				String DigitalValue = "0"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class kitchenDimClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "A"; 
				String UnitCode = "2"; 
				String DigitalValue = "3"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class kitchenBrightClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "A"; 
				String UnitCode = "2"; 
				String DigitalValue = "2"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class donnsOfficeOnClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "B"; 
				String UnitCode = "0"; 
				String DigitalValue = "1"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class donnsOfficeOffClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";    
				String RoomName = "B"; 
				String UnitCode = "0"; 
				String DigitalValue = "0"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class donnsOfficeDimClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "B"; 
				String UnitCode = "0"; 
				String DigitalValue = "3"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class donnsOfficeBrightClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "B"; 
				String UnitCode = "0"; 
				String DigitalValue = "2"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class officeCameraOnClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "B"; 
				String UnitCode = "8"; 
				String DigitalValue = "1"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class officeCameraOffClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {				
				String URLToParse = "http://www.omalleyland.com:8080/?";    
				String RoomName = "B"; 
				String UnitCode = "8"; 
				String DigitalValue = "0"; 
				URLToParse += RoomName + UnitCode + DigitalValue;
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class officeDimClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "B"; 
				String UnitCode = "8"; 
				String DigitalValue = "3"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}

	public class officeBrightClick implements Button.OnClickListener {
		public void onClick(View v) {
			try {
				String URLToParse = "http://www.omalleyland.com:8080/?";  
				String RoomName = "B"; 
				String UnitCode = "8"; 
				String DigitalValue = "2"; 
				URLToParse += RoomName + UnitCode + DigitalValue; 
				new SendDAX10Command().execute(URLToParse);
			} 
			catch (Exception e) { 
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
		} 
	}
	
	private class SendDAX10Command extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			String Response;
			Response = "ERROR";
			try {
				Response = ExecuteHTTPRequest(urls[0]);
			}
			catch (Exception e) {
				Toast.makeText(MySimpleDAX10ControllerActivity.this, "EXCEPTION: \n" + e.getMessage(), Toast.LENGTH_LONG).show();
			}
			return Response;
		}
	}
	
}
