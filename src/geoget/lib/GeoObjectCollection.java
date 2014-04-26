package geoget.lib;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;

public class GeoObjectCollection {

    public int getElements() {
        YandexGeoApiRequest req;
        double lon = 37.619559, lat = 55.739789;
        req = new YandexGeoApiRequest(lon, lat);
        JSONObject jsonParsed = req.getJson();
        //GeoObject center = new GeoObject("Center", lon, lat);
        ArrayList<GeoObject> elements = new ArrayList<GeoObject>();

        JSONArray data;
        jsonParsed = (JSONObject) jsonParsed.get("response");
        jsonParsed = (JSONObject) jsonParsed.get("GeoObjectCollection");
        data = (JSONArray) jsonParsed.get("featureMember");
        for (Object obj : data) {
            jsonParsed = (JSONObject) obj;
            jsonParsed = (JSONObject) jsonParsed.get("GeoObject");
            JSONObject point = (JSONObject) jsonParsed.get("Point");
            jsonParsed = (JSONObject) jsonParsed.get("metaDataProperty");
            jsonParsed = (JSONObject) jsonParsed.get("GeocoderMetaData");
            jsonParsed = (JSONObject) jsonParsed.get("AddressDetails");
            jsonParsed = (JSONObject) jsonParsed.get("Country");
            String[] position = ((String) point.get("pos")).split(" ");

            GeoObject current = new GeoObject(
                    (String) jsonParsed.get("AddressLine"),
                    Double.parseDouble(position[0]),
                    Double.parseDouble(position[1])
            );
            elements.add(current);
        }
    return elements.size();
    }
 }

