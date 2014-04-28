package geoget;

import geoget.lib.GeoObjectCollection;
import geoget.lib.GeoObjectElements;

public class Main {

	public static void main(String[] args) {
        double lon = 37.619559, lat = 55.739789;
        GeoObjectElements center = new GeoObjectElements();
        GeoObjectCollection collection = new GeoObjectCollection();


        System.out.println(collection.centers(lon, lat));
     //   System.out.println(center.getElements(lon, lat));
    }
}

