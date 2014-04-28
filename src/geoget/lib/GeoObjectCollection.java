package geoget.lib;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class GeoObjectCollection {
    public ArrayList<GeoObject> objects = new ArrayList<GeoObject>();

    public int getElements(double lon, double lat, double spn) {
        YandexGeoApiRequest req;
        req = new YandexGeoApiRequest(lon, lat, spn);
        JSONObject jsonParsed = req.getJson();
        //GeoObject center = new GeoObject("Center", lon, lat);
        int count = 0;
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
            String name = (String) jsonParsed.get("AddressLine");
            if (!this.contains(name)){
                GeoObject current = new GeoObject(
                    name,
                    Double.parseDouble(position[0]),
                    Double.parseDouble(position[1])
                );
                objects.add(current);
            }

            count++;
        }
        return count;
    }

    private boolean contains(String name) {
        for (GeoObject o: objects){
            if (o.name.equals(name))
                return true;
        }
        return false;
    }

    public ArrayList<GeoObject> centers(double lon, double lat, double spn) {
        int[][] points = {
            {-1, -1}, {1, -1},
            {1, -1}, {1, 1}
        };

        for (int[] center : points) {
            int added = this.getElements(lon + spn * center[0], lat + spn * center[1], spn);
            if (added >= 100) {
                centers(lon + spn * center[0] / 2, lat + spn * center[1] / 2, spn / 2);
            }
        }
        return objects;
    }
}
