package geoget.lib;

import java.util.ArrayList;

public class GeoObjectCollection {
    ArrayList<GeoObject> objects = new ArrayList<GeoObject>();
    GeoObjectElements elements = new GeoObjectElements();
    ArrayList<GeoObject> partOne = new ArrayList<GeoObject>();

    int[][] points = {
            {-1, -1}, {0, -1}, {1, -1},
            {-1, 0}, {0, 0}, {1, 0},
            {-1, 1}, {0, 1}, {1, 1}
    };

    public ArrayList<GeoObject> centers(double lon, double lat) {
        double edge = 0.01;
        for (int[] center: points) {
            partOne = elements.getElements(lon + edge * center[0], lat + edge * center[1]);
            for(int i=0; i<partOne.size();i++) {
                objects.add(partOne.get(i));
            }
        }
        return objects;
    }
}
