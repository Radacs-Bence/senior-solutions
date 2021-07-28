package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsServiceTest {

    @Mock
    LocationsRepository locationsRepository;

    @InjectMocks
    LocationsService locationsService;

    @Test
    void getLocationsTest() {
        when(locationsRepository.findAll()).thenReturn(new ArrayList<>(List.of(new Location("a", 1, 1), new Location("b", 1, 1.3))));

        assertThat(locationsService.listLocations(Optional.empty()))
                .hasSize(4)
                .extracting(LocationDTO::getName, LocationDTO::getLon)
                .contains(tuple("b", 1.3));
    }


}