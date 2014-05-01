package geoget;

import geoget.lib.GeoObject;
import geoget.lib.GeoObjectCollection;

public class Main {

	public static void main(String[] args) {
        double lon = 37.619559, lat = 55.739789, spn = 0.0059;
        GeoObjectCollection collection = new GeoObjectCollection();
        GeoObject center = new GeoObject("Center", lon, lat);

        collection.updateElements(lon, lat, spn);

        for (GeoObject current: collection.objects){
            System.out.println(String.format("%s\t%04.0fм", current.name, current.distance(center)));
        }
    }
}

