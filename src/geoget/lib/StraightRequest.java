package geoget.lib;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class StraightRequest {
    public String request;
    public String[] lonlat;

    public StraightRequest(String req) throws IOException {
        String baseFormat = "http://geocode-maps.yandex.ru/1.x/?format=json&geocode=%s";
        request = String.format(Locale.US, baseFormat, req);
        lonlat = getPosition(getJson());
    }

    public JSONObject getJson() throws IOException {
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
        }
        catch (IOException e) {
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParsed;
    }

    private static String[] getPosition(JSONObject parsedResponse) {
        String pos;
        pos = (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) ((JSONObject)
            parsedResponse
            .get("response"))
            .get("GeoObjectCollection"))
            .get("featureMember"))
            .get(0)).get("GeoObject"))
            .get("Point"))
            .get("pos");
        return pos.split(" ");
    }
}
