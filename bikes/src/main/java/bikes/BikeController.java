package bikes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BikeController {

    private BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/history")
    public String getHistory(){
        initList();
        return bikeService.getBikes().stream()
                .map(Bike::toString)
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/users")
    public String getUsers(){
        initList();
        return bikeService.getBikes().stream()
                .map(Bike::getLastUser)
                .distinct()
                .collect(Collectors.joining(", "));
    }


    private void initList(){
        if (bikeService.getBikes().size() <= 0){
            bikeService.read();
        }
    }
}
