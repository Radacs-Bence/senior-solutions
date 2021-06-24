package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LocationsController {

    private List<Location> locations = new ArrayList<>();

    public LocationsController() {
        locations.add(new Location(0L, "a", 1, 1));
        locations.add(new Location(1L, "b", 1, 1.3));
        locations.add(new Location(2L, "c", 2, 1.3));
        locations.add(new Location(3L, "d", 2, 23.4));
    }

    @GetMapping("/locations")
    public List<String> getLocations(){
        return locations.stream()
                .map(location -> location.getName())
                .collect(Collectors.toList());
    }
}
