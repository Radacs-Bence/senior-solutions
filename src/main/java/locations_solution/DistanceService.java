package locations_solution;

import java.util.Optional;

public class DistanceService {

    private LocationRepository locationRepository;

    public DistanceService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Double> calculateDistance(String startName, String endName){
        Optional<Location> start = locationRepository.findByName(startName);
        Optional<Location> end = locationRepository.findByName(endName);

        if (start.isEmpty() || end.isEmpty()){
            return Optional.empty();
        }else {
            return Optional.of(start.get().distanceFrom(end.get()));
        }

    }

}
