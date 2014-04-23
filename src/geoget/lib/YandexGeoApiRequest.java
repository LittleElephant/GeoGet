package geoget.lib;

import java.util.Locale;

public class YandexGeoApiRequest {
	private String baseformat = "http://geocode-maps.yandex.ru/1.x/?format=json&kind=house&spn=0.005,0.005&results=100&geocode=%.6f,%.6f";
	private String request;
	
	public YandexGeoApiRequest(double lon, double lat) {
		request = String.format(Locale.US, baseformat, lon, lat);
	}
	
	public String getRequest() {
		return request;
	}
}