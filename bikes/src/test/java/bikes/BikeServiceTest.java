package bikes;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BikeServiceTest {

    @Test
    void readTest() {
        BikeService bikeService = new BikeService();
        bikeService.read();

        assertThat(bikeService.getBikes())
                .hasSize(5)
                .extracting(Bike::getId)
                .contains("FH636");
    }
}