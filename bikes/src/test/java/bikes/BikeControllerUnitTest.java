package bikes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BikeControllerUnitTest {

    @Mock
    BikeService bikeService;

    @InjectMocks
    BikeController bikeController;

    @Test
    void getHistory() {
        when(bikeService.getBikes()).thenReturn(new ArrayList<>(List.of(new Bike("a", "B", LocalDateTime.MIN, 1))));

        assertThat(bikeController.getHistory())
                .endsWith("1.0;")
                .startsWith("a");
    }

    @Test
    void getUsers() {
        when(bikeService.getBikes()).thenReturn(new ArrayList<>(List.of(new Bike("a", "B", LocalDateTime.MIN, 1))));

        assertEquals("B",bikeController.getUsers());
    }
}