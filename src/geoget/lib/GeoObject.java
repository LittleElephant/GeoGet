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
        double
            thisLatRad = Math.toRadians(this.lat),
            thisLonRad = Math.toRadians(this.lon),
            otherLatRad = Math.toRadians(other.lat),
            otherLonRad = Math.toRadians(other.lon);
        final int earthRadius = 6372795;
        return earthRadius * (
            2 * Math.asin(Math.sqrt(
                Math.pow((Math.sin(thisLatRad - otherLatRad) / 2), 2) +
                Math.cos(thisLatRad) * Math.cos(otherLatRad) * Math.pow((Math.sin((thisLonRad - otherLonRad) / 2)), 2)
            ))
        );
    }

}