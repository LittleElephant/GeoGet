package geoget;

import com.sun.javafx.binding.StringFormatter;
import geoget.lib.GeoObject;
import geoget.lib.GeoObjectCollection;
import geoget.lib.GeoObjectElements;

import java.util.Locale;

public class Main {

	public static void main(String[] args) {
        double lon = 37.619559, lat = 55.739789;
        GeoObjectCollection collection = new GeoObjectCollection();
        GeoObject center = new GeoObject("Center", lon, lat);

        for (GeoObject current: collection.centers(lon, lat, 0.007)){
            System.out.println(String.format("%s\t%04.0fм", current.name, current.distance(center)));
        }
    }
}

