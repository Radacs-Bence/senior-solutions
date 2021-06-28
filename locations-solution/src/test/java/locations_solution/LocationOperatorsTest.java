package locations_solution;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LocationOperatorsTest {

    @Test
    void filterOnNorth() {
        LocationOperators locationOperators = new LocationOperators();
        LocationParser locationParser = new LocationParser();
        List<Location> locations = new ArrayList<>();
        String name = "Hely: ";
        for (int i = 0; i < 6; i++){
            locations.add(locationParser.parse(String.format("%s,%d,1", name + (i + 1), 3-i)));
        }
        List<Location> northLocations = locationOperators.filterOnNorth(locations);
        assertEquals(List.of("Hely: 1", "Hely: 2", "Hely: 3"), northLocations.stream().map(Location::getName).collect(Collectors.toList()));
    }
}