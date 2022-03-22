package GetRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class TechTest {

	public static void main(String[] args) throws ParseException, IOException {

		getWeather_Lat_Lon_AC1();
		getWeather_Cities_AC2();
		getAirquality_AC3();

	}

	@Test
	public static void getWeather_Lat_Lon_AC1() throws IOException, ParseException {

		try {

			URL url = new URL(
					"https://api.weatherbit.io/v2.0/current?lat=38&lon=-78.25&include=minutely&country=US&key=b15d5bf73793463d92af0f8adbfba900");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			// Getting the response code
			int responsecode = conn.getResponseCode();

			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {
				System.out.println("Correct status code returned:" + responsecode);
				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				// Write all the JSON data into a string using a scanner function
				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}

				// Close the scanner
				scanner.close();

				// Using the JSON simple library parse the string into a json object
				JSONParser parse = new JSONParser();
				JSONObject data_obj = (JSONObject) parse.parse(inline);

				JSONArray arr = (JSONArray) data_obj.get("data");

				for (int i = 0; i < arr.size();) {

					JSONObject new_obj = (JSONObject) arr.get(i);

					if (new_obj.get("country_code").equals("US")) {

						JSONObject res = (JSONObject) new_obj.get("weather");

						System.out.println("weather:\n " + res.toString());
						break;

					}
				}
			}
		}

		catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public static void getWeather_Cities_AC2() throws IOException {

		try {
			URL url = new URL(
					"https://api.weatherbit.io/v2.0/current?cities=4487042%2C%204494942&units=S&marine=t&key=b15d5bf73793463d92af0f8adbfba900");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responsecode = conn.getResponseCode();
			InputStream res = conn.getErrorStream();
			
			if (responsecode != 200)
				System.out.println("Error: " + res.toString());
			Assert.assertEquals(200, responsecode);

		} catch (java.lang.AssertionError e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public static void getAirquality_AC3() throws ParseException, IOException {

		try {

			URL url = new URL(
					"https://api.weatherbit.io/v2.0/current/airquality?postal_code=27601&country=US&key=b15d5bf73793463d92af0f8adbfba900");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responsecode = conn.getResponseCode();

			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {
				System.out.println("Correct status code returned:" + responsecode);
				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}

				scanner.close();

				JSONParser parse = new JSONParser();
				JSONObject data_obj = (JSONObject) parse.parse(inline);

				if (data_obj.get("country_code").equals("US")) {

					JSONArray arr = (JSONArray) data_obj.get("data");

					for (int i = 0; i < arr.size();) {

						JSONObject new_obj = (JSONObject) arr.get(i);

						System.out.println("Airquality: " + new_obj.toString());
						break;

					}
				}
			}
		}

		catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
