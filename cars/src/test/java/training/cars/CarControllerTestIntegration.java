package training.cars;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.Mockito.when;

public class CarControllerTestIntegration {

    CarController carController = new CarController(new CarService());

    @Test
    void listCars() {

        assertThat(carController.listCars())
                .hasSize(4)
                .extracting(Car::getBrand, Car::getAge)
                .contains(tuple("FIAT", 13));
    }

    @Test
    void listTypes() {

        assertThat(carController.listTypes())
                .hasSize(4)
                .contains("Ferrari: Enzo");
    }
}
