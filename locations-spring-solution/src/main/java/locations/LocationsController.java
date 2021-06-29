package locations;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class LocationsController {

    private LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping("/locations")
    public String listLocations(@RequestParam Optional<String> name){
        return locationsService.listLocations(name).stream()
                .map(LocationDTO::getName)
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/{id}")
    public LocationDTO locationById(@PathVariable("id") long id){
        return locationsService.locationById(id);
    }

    @PostMapping
    public LocationDTO createLocation(@RequestBody CreateLocationCommand command){
        return locationsService.createLocation(command);
    }

    @PutMapping("/{id}")
    public LocationDTO updateLocation(@PathVariable("id") long id, @RequestBody UpdateLocationCommand command){
        return locationsService.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable("id") long id, @RequestBody UpdateLocationCommand command){
        locationsService.deleteLocation(id, command);
    }


}
