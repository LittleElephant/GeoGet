package geoget;

import geoget.lib.GeoObject;
import geoget.lib.YandexGeoApiRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

	public static void main(String[] args) {
		YandexGeoApiRequest req;
		double lon=37.619559, lat=55.739789;
		req = new YandexGeoApiRequest(lon, lat);
		JSONParser parser = new JSONParser();
	
		
		try {
			URL url = new URL(req.getRequest());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(
				new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder json = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			JSONArray data;
			JSONObject jsonParsed =	(JSONObject) parser.parse(json.toString());
			jsonParsed = (JSONObject) jsonParsed.get("response");
			jsonParsed = (JSONObject) jsonParsed.get("GeoObjectCollection");
			data = (JSONArray) jsonParsed.get("featureMember");
			ArrayList<GeoObject> geoObjects = new ArrayList<GeoObject>();
			for (Object obj: data){
				jsonParsed = (JSONObject) obj;
				jsonParsed = (JSONObject) jsonParsed.get("GeoObject");
				JSONObject point = (JSONObject) jsonParsed.get("Point");
				jsonParsed = (JSONObject) jsonParsed.get("metaDataProperty");
				jsonParsed = (JSONObject) jsonParsed.get("GeocoderMetaData");
				jsonParsed = (JSONObject) jsonParsed.get("AddressDetails");
				jsonParsed = (JSONObject) jsonParsed.get("Country");
                String[] position = ((String) point.get("pos")).split(" ");


                geoObjects.add(new GeoObject(
                    (String)jsonParsed.get("AddressLine"),
                    Double.parseDouble(position[0]),
                    Double.parseDouble(position[1])
                ));

			}
            System.out.println(geoObjects);
        }
		catch (Exception e) {
			e.printStackTrace();
		}

		
	}
}

