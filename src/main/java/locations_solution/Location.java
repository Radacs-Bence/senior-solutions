package locations_solution;

public class Location {

    public static final int EARTH_RADIUS = 6371;
    private String name;
    private double lat;
    private double lon;


    public Location(String name, double lat, double lon) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        validator();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isOnEquator(){
        return lat == 0;
    }

    public boolean isOnPrimeMeridian(){
        return lon == 0;
    }

    public double distanceFrom(Location otherLocation) { //Haversine algoritmus kimásolva és átírva

        double latDistance = Math.toRadians(otherLocation.getLat() - this.lat);
        double lonDistance = Math.toRadians(otherLocation.getLon() - this.lon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.getLat())) * Math.cos(Math.toRadians(otherLocation.getLat()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // km-ben
    }

    private void validator(){
        boolean valid = true;
        String text = "";
        if (lat < -90 || lat > 90){
            valid = false;
            text = "Invalid latitude: " + lat + " ";
        }
        if (lon < -180 || lon > 180){
            valid = false;
            text = text + "Invalid longitude: " + lon;
        }
        if (!valid){
            throw new IllegalArgumentException(text.trim());
        }
    }
}
