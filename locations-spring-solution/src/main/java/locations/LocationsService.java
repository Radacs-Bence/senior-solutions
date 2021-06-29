package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private ModelMapper modelMapper;
    private AtomicLong idGenerator = new AtomicLong();
    private List<Location> locations = new ArrayList<>();

    public LocationsService(ModelMapper modelMapper) {
        locations.add(new Location(idGenerator.incrementAndGet(), "a", 1, 1));
        locations.add(new Location(idGenerator.incrementAndGet(), "b", 1, 1.3));
        locations.add(new Location(idGenerator.incrementAndGet(), "c", 2, 1.3));
        locations.add(new Location(idGenerator.incrementAndGet(), "d", 2, 23.4));
        this.modelMapper = modelMapper;
    }

    public List<LocationDTO> listLocations(Optional<String> name) {
        List<Location> filtered =  locations.stream()
                .filter(location -> name.isEmpty() || location.getName().toLowerCase().contains(name.get().toLowerCase()))
                .collect(Collectors.toList());
        Type targetListType = new TypeToken<List<LocationDTO>>(){}.getType();
        return modelMapper.map(filtered, targetListType);
    }

    public LocationDTO locationById(long id) {
        return modelMapper.map(searchLocationsById(id), LocationDTO.class);
    }

    private Location searchLocationsById(long id) {
        return locations.stream()
                .filter(location -> location.getId() == id)
                .findAny()
                .orElseThrow(() ->new IllegalArgumentException("Location not found: " + id));
    }

    public LocationDTO createLocation(CreateLocationCommand command) {
        Location location = new Location(idGenerator.incrementAndGet(), command.getName(), command.getLat(), command.getLon());
        locations.add(location);
        return modelMapper.map(location, LocationDTO.class);
    }

    public LocationDTO updateLocation(long id, UpdateLocationCommand command) {
        Location found = searchLocationsById(id);
        found.setName(command.getName());
        found.setLat(command.getLat());
        found.setLon(command.getLon());
        return modelMapper.map(found, LocationDTO.class);
    }

    public void deleteLocation(long id) {
        Location found = searchLocationsById(id);
        locations.remove(found);
    }
}
