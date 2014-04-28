package geoget.lib;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class YandexGeoApiRequest {
    private String request;
	
	public YandexGeoApiRequest(double lon, double lat, double spn) {
        String baseformat = "http://geocode-maps.yandex.ru/1.x/?format=json&kind=house&spn=%.4f,%.4f&results=1000&geocode=%.6f,%.6f";
        request = String.format(Locale.US, baseformat,spn, spn, lon, lat);
    }
    public JSONObject getJson() {
        JSONParser parser = new JSONParser();

        JSONObject jsonParsed = new JSONObject();
        try {
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder json = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            jsonParsed = (JSONObject) parser.parse(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParsed;
    }
}