package training.cars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@ExtendWith(MockitoExtension.class)
class CarControllerTestUnit {

    @Mock
    CarService carService;
    @InjectMocks
    CarController carController;

    @Test
    void listCars() {
        when(carService.getCars()).thenReturn(new ArrayList<>(List.of(new Car("A","B", 1, Status.BAD))));

        assertThat(carController.listCars())
                .hasSize(1)
                .extracting(Car::getBrand, Car::getAge)
                .contains(tuple("A", 1));
    }

    @Test
    void listTypes() {
        when(carService.getTypes()).thenReturn(new ArrayList<>(List.of("A", "B")));

        assertThat(carController.listTypes())
                .hasSize(2)
                .contains("A");
    }
}