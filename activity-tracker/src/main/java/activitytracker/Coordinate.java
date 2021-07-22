package activitytracker;

public class Coordinate {


    private long lat;
    private long lon;

    public Coordinate(long lat, long lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Coordinate() {
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
