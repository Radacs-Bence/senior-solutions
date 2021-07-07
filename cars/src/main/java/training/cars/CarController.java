package training.cars;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> listCars(){
        return carService.getCars();
    }

    @GetMapping("/types")
    public List<String> listTypes(){
        return carService.getTypes();
    }

}
