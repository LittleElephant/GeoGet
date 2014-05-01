package geoget.lib;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequest {
    public String request;

    public JSONObject getJsonFromRequest () throws IOException {
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
}
