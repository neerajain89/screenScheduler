package com.skilrock.screenSchedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class TpIntegrationImpl {
	private static final Logger logger = LoggerFactory
			.getLogger(TpIntegrationImpl.class);

	public static String getSchedulingResponseString() {
		StringBuilder reqJson = null;
		HttpURLConnection connection = null;
		BufferedReader in = null;
		logger.debug("***** Inside getPMSResponseString Function");
		StringBuilder urlString = new StringBuilder();
		
		try {
			PropertyLoader.loadProperties("ScreenSchedule.properties");
			String scheduleDataURL = PropertyLoader.getProperty("scheduleDataURL");
			//urlString.append("http://192.168.124.215:8081/LMSLinuxNew/rest/schedulerData/fetchSchedule");
			urlString.append(scheduleDataURL);
			logger.debug("***** URL for PMS Calling {}", urlString.toString());
			URL url = new URL(urlString.toString());
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "text/plain");
			connection.setRequestProperty("userName", PropertyLoader.getProperty("userName"));
			connection.setRequestProperty("password", PropertyLoader.getProperty("password"));
			long t1 = System.currentTimeMillis();
			/*OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			Gson gson = new Gson();
			String json = gson.toJson(jsonObj);
			out.write(json);
			out.close();*/
			logger.debug("Time Taken for PMS Request - {}", (System.currentTimeMillis() - t1));

			int responseCode = connection.getResponseCode();
			reqJson = new StringBuilder("");
			if (responseCode == 200) {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String responseString;
				while ((responseString = in.readLine()) != null) {
					reqJson.append(responseString);
				}
			} else {
				in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				String responseString;
				while ((responseString = in.readLine()) != null) {
					reqJson.append(responseString);
				}
				return null;
			}
			logger.debug("Response String From PMS {}", reqJson.toString());
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (connection != null) {
					connection.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return reqJson.toString();
	}
public static void main(String[] args) {
	getSchedulingResponseString();
}
	
}
