package locations_solution;


public class LocationParser {


    public static final String SEPARATOR = ",";

    public Location parse(String text){
        String[] parts = text.split(SEPARATOR);
        validator(parts);
        try {
            return new Location(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
        }catch (NumberFormatException numberFormatException){
            throw new IllegalArgumentException(String.format("Coordinates %s and/or %s are not numbers", parts[1], parts[2]));
        }
    }

    private void validator(String[] parts){
        if (parts.length != 3){
            throw new  IllegalArgumentException("Text is not properly formatted");
        }
    }

}
