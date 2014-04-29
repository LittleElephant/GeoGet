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
            String objAddress, objLon, objLat;
            String[] extractedData = GeoObjectCollection.extractDataFromJson((JSONObject) obj);
            objAddress = extractedData[0];
            objLon = extractedData[1];
            objLat = extractedData[2];
            if (!this.contains(objAddress)){
                GeoObject current = new GeoObject(
                    objAddress,
                    Double.parseDouble(objLon),
                    Double.parseDouble(objLat)
                );
                objects.add(current);
            }

            count++;
        }
        return count;
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

    private boolean contains(String name) {
        for (GeoObject o: objects){
            if (o.name.equals(name))
                return true;
        }
        return false;
    }

    private static String[] extractDataFromJson(JSONObject data) {
        String addressLine;
        String[] position;
        data = (JSONObject) data.get("GeoObject");
        addressLine = (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject)
                data
                .get("metaDataProperty"))
                .get("GeocoderMetaData"))
                .get("AddressDetails"))
                .get("Country"))
                .get("AddressLine");

        position= ((String) ((JSONObject) data.get("Point")).get("pos")).split(" ");
        return new String[] {addressLine, position[0], position[1]};
    }
}
