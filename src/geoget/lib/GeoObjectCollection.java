package geoget.lib;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class GeoObjectCollection {
    GeoObjectElements geoObjectElements = new GeoObjectElements();

    int[][] points = {
            {-1, -1}, {1, -1},
            {1, -1}, {1, 1}
    };

    public ArrayList<GeoObject> centers(double lon, double lat, double spn) {
        for (int[] center : points) {
            int added = geoObjectElements.getElements(lon + spn * center[0], lat + spn * center[1], spn);
            if (added >= 100) {
                centers(lon + spn * center[0] / 2, lat + spn * center[1] / 2, spn / 2);
            }
        }
        return geoObjectElements.elements;
    }
}
