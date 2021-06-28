package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;
    @InjectMocks
    LocationsController locationsController;

    @Test
    void getLocationsRunsTest() {
        String result = locationsController.getLocations();
        assertEquals("", result);
    }

    @Test
    void getLocationsTest() {
        when(locationsService.getLocations())
                .thenReturn(List.of(new Location(1L,"a", 1,1)));
        String result = locationsController.getLocations();
        assertEquals("a", result);
    }


}