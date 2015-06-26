package geoget.lib;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Locale;

public class StraightRequest extends ApiRequest {
    public String[] position;

    public StraightRequest(String req) throws IOException {
        String baseFormat = "https://geocode-maps.yandex.ru/1.x/?format=json&geocode=%s";
        request = String.format(Locale.US, baseFormat, req);
        position = getPosition(getJsonFromRequest());
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
