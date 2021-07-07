package training.cars;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> cars = new ArrayList<>();

    public CarService() {
        cars.add(new Car("FIAT", "Punto", 13, Status.EXCELLENT));
        cars.add(new Car("Renault", "Twingo", 4, Status.NORMAL));
        cars.add(new Car("Ferrari", "Enzo", 1, Status.BAD));
        cars.add(new Car("Wolkswagen", "Bug", 20, Status.EXCELLENT));
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    public List<String> getTypes(){
        return cars.stream()
                .map(car -> String.format("%s: %s", car.getBrand(), car.getType()))
                .collect(Collectors.toList());
    }
}
