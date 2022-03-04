package GetRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class GetData {
	
	public static void main(String[] args) throws ParseException, IOException {
			
		
	}
		@Test
		public static void getWeather(){
			
		
		  try {

			  URL url = new URL("https://api.weatherbit.io/v2.0/current?lat=38&lon=-78.25&include=minutely&marine=t&units=I&key=b15d5bf73793463d92af0f8adbfba900");

	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.connect();

	            //Getting the response code
	            int responsecode = conn.getResponseCode();

	            if (responsecode != 200) {
	                throw new RuntimeException("HttpResponseCode: " + responsecode);
	            } else {

	                String inline = "";
	                Scanner scanner = new Scanner(url.openStream());

	                //Write all the JSON data into a string using a scanner
	                while (scanner.hasNext()) {
	                    inline += scanner.nextLine();
	                }

	                //Close the scanner
	                scanner.close();
	                //Using the JSON simple library parse the string into a json object
	                JSONParser parse = new JSONParser();
	                JSONObject data_obj = (JSONObject) parse.parse(inline);

	                //Get the required object from the above created object
	                //JSONObject obj = (JSONObject) data_obj.get("data");

	                //Get the required data using its key
	                System.out.println(data_obj.toString());

	                JSONArray arr = (JSONArray) data_obj.get("data");

	                for (int i = 0; i < arr.size(); i++) {

	                    JSONObject new_obj = (JSONObject) arr.get(i);        
	          	                    		
	                    	JSONObject res = (JSONObject) new_obj.get("weather");
	                    	
	                    	if(new_obj.get("country_code").equals("US")) {
	                    		                    	 
	                        System.out.println("weather: " + res.get("description"));
	                        break;
	                    	
	                    	} 	
	                    }
	            }
		  }
	                   
	                	catch (Exception e) {
	    	            e.printStackTrace();
	                }
	            }

}
		  	 

			
	
	    
	    
		
