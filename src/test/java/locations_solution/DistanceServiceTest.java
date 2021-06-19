package locations_solution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DistanceServiceTest {

    @Mock
    LocationRepository locationRepository;
    @InjectMocks
    DistanceService distanceService;

    @Test
    void calculateDistanceEmpty() {
        Optional<Double> result = distanceService.calculateDistance("a","b");

        assertEquals(Optional.empty(), result);
    }

    @Test
    void calculateDistanceFound() {
        when(locationRepository.findByName(anyString()))
                .thenReturn(Optional.of(new Location("a", 1,1)));
        Optional<Double> result = distanceService.calculateDistance("a","b");

        assertEquals(Optional.of(0.0), result);
    }



}