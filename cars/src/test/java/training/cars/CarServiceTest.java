package training.cars;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class CarServiceTest {

    CarService carService = new CarService();

    @Test
    void getCars() {
        assertThat(carService.getCars())
                .hasSize(4)
                .extracting(Car::getBrand, Car::getAge)
                .contains(tuple("FIAT", 13));
    }

    @Test
    void getTypes() {
        assertThat(carService.getTypes())
                .hasSize(4)
                .contains("Ferrari: Enzo");
    }
}