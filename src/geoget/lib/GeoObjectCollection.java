package geoget.lib;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import geoget.lib.yandexapi.YandexGeoApiRequest;


public class GeoObjectCollection {
    public ArrayList<GeoObject> objects = new ArrayList<GeoObject>();

    public void updateElements(double lon, double lat, double spn) throws IOException {
        for (YandexGeoApiRequest req: YandexGeoApiRequest.getSplittedRequests(lon, lat, spn))
            this.updateElementsFromRequest(req);
    }

    public void updateElementsFromRequest(YandexGeoApiRequest req) throws IOException {
        JSONObject jsonParsed = req.getJsonFromRequest();
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
        }
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
