package locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class LocationsServiceTest {

    @Test
    void getLocationsTest() {
        LocationsService locationsService = new LocationsService(new ModelMapper());
        assertThat(locationsService.listLocations())
                .hasSize(4)
                .extracting(LocationDTO::getName, LocationDTO::getLat)
                .contains(tuple("b", 1.0));
    }


}