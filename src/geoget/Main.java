package geoget;

import geoget.lib.GeoObject;
import geoget.lib.GeoObjectCollection;
import geoget.lib.StraightRequest;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
        double spn = 0.015;
        String address = "Бульвар матроса железняка 21";
        StraightRequest sr = new StraightRequest(address);
        double lon = Double.parseDouble(sr.position[0]);
        double lat = Double.parseDouble(sr.position[1]);
        GeoObjectCollection collection = new GeoObjectCollection();
        GeoObject center = new GeoObject("Center", lon, lat);
        collection.updateElements(lon, lat, spn);
        for (GeoObject current: collection.objects){
            System.out.println(String.format("%s\t%04.0fм", current.name, current.distance(center)));
        }
    }
}

