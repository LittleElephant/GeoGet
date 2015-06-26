package geoget.lib.yandexapi;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import geoget.lib.ApiRequest;


public class YandexGeoApiRequest extends ApiRequest {

    public YandexGeoApiRequest(double lon, double lat, double spn) {
        String baseFormat = "https://geocode-maps.yandex.ru/1.x/?format=json&kind=house&spn=%.4f,%.4f&results=1000&geocode=%.6f,%.6f";
        request = String.format(Locale.US, baseFormat,spn, spn, lon, lat);
    }

    public static ArrayList<YandexGeoApiRequest> getSplittedRequests(double lon, double lat, double spn) {
        ArrayList<YandexGeoApiRequest> requests = new ArrayList<YandexGeoApiRequest>();
        int [][] offsets = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        YandexGeoApiRequest request = new YandexGeoApiRequest(lon, lat, spn);
        int found;
        try {
            found = YandexGeoApiRequest.getFoundCount(request.getJsonFromRequest());
        }
        catch (IOException err) {
            found = 100;
        }
        if (found >= 100) {
            for (int[] offset: offsets) {
                requests.addAll(getSplittedRequests(
                    lon + offset[0] * spn / 4, lat + offset[1] * spn / 4, spn / 2
                ));
            }
        }
        else {
            requests.add(new YandexGeoApiRequest(lon, lat, spn));
        }
        return requests;
    }

    private static int getFoundCount(JSONObject parsedResponse) {
        return Integer.parseInt(
            (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject)
            parsedResponse
            .get("response"))
            .get("GeoObjectCollection"))
            .get("metaDataProperty"))
            .get("GeocoderResponseMetaData"))
            .get("found"));
    }
}
