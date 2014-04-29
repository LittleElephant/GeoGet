package geoget;

import geoget.lib.GeoObject;
import geoget.lib.GeoObjectCollection;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
        double lon = 37.619559, lat = 55.739789, spn = 0.01;
        GeoObjectCollection collection = new GeoObjectCollection();
        GeoObject center = new GeoObject("Center", lon, lat);

        collection.updateElements(lon, lat, spn);

        for (GeoObject current: collection.objects){
            System.out.println(String.format("%04.0fм",  current.distance(center)));
        }
    }
}

