package ug.gda.pl.parts;

/**
 * Created by Maciek on 31.12.2015.
 */
public class City {

    private String name;
    private double longitude;
    private double  latitude;

    public City() {
    }

    public City(String name, double longitude, double latitude ) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
