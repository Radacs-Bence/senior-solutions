package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    private ModelMapper modelMapper;
    private List<Location> locations = new ArrayList<>();

    public LocationsService(ModelMapper modelMapper) {
        locations.add(new Location(0L, "a", 1, 1));
        locations.add(new Location(1L, "b", 1, 1.3));
        locations.add(new Location(2L, "c", 2, 1.3));
        locations.add(new Location(3L, "d", 2, 23.4));
        this.modelMapper = modelMapper;
    }

    public List<LocationDTO> listLocations() {
        Type targetListType = new TypeToken<List<LocationDTO>>(){}.getType();
        return modelMapper.map(locations, targetListType);
    }
}
