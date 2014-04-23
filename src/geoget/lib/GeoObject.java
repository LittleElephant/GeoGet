package geoget.lib;

public class GeoObject {
    public String name;
    public double lat;
    public double lon;

    public GeoObject (String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String toString() {
        return String.format("GeoObject(%s %f %f)", name, lat, lon);
    }
}
