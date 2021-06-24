package locations_solution;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class LocationServiceTest {

    Condition<Location> containsOneZero = new Condition<>(location -> location.getLat() == 0 || location.getLon() == 0, "at least one coordinate is 0");

    @Test
    void readLines() {
        LocationService locationService = new LocationService();

        try {
            locationService.readLines();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(locationService.getLocations())
                .hasSize(3)
                .extracting(Location::getName, Location::getLat)
                .contains(tuple("Budapest", 47.497912));
    }

    @Test
    void conditionTest(){
        Location location = new Location("a", 0, 1);

        assertThat(location).has(containsOneZero);
    }
}