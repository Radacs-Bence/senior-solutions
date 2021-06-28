package locations;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class LocationsServiceTest {

    @Test
    void getLocationsTest() {
        LocationsService locationsService = new LocationsService();
        assertThat(locationsService.getLocations())
                .hasSize(4)
                .extracting(Location::getName, Location::getLat)
                .contains(tuple("b", 1.0));
    }


}