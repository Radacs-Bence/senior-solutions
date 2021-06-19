package locations_solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocationService {


    private List<Location> locations = new ArrayList<>();

    public void readLines() throws IOException {
        BufferedReader reader = Files.newBufferedReader(Path.of("src/main/resources/locations_solution/FavouriteLocations.csv"));
        String line;
        while ((line = reader.readLine())  != null) {
            locations.add(new LocationParser().parse(line));
        }
    }

    public List<Location> getLocations() {
        return new ArrayList<>(locations);
    }
}
