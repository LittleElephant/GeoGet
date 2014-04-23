package geoget.lib;

public class GeoObject {
    public String name;
    public double lat;
    public double lon;

    public GeoObject (String name, double lon, double lat) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
    }

    public String toString() {
        return String.format("GeoObject(%s %f %f)", name, lat, lon);
    }

    public double distance(GeoObject other) {
        return Math.sqrt(Math.pow(this.lat - other.lat, 2) + Math.pow(this.lon - other.lon, 2));
    }

    public static double convertToMeters(double grads) {
        return grads * 111 * 1000;
    }
}