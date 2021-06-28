package locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private List<Location> locations = new ArrayList<>();

    public LocationsService() {
        locations.add(new Location(0L, "a", 1, 1));
        locations.add(new Location(1L, "b", 1, 1.3));
        locations.add(new Location(2L, "c", 2, 1.3));
        locations.add(new Location(3L, "d", 2, 23.4));
    }

    public List<Location> getLocations() {
        return new ArrayList<>(locations);
    }
}
