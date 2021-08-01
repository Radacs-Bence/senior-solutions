package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@DataJpaTest
public class LocationsRepositoryIT {

    @Autowired
    LocationsRepository locationsRepository;

    @Test
    void saveAndFindAll(){
        locationsRepository.save(new Location("d", 2, 23.4));
        locationsRepository.save(new Location("c", 2, 1.3));

        List<Location> locations = locationsRepository.findAll();

        assertThat(locations)
                .hasSize(2)
                .extracting(Location::getName, Location::getLon)
                .contains(tuple("d", 23.4));
    }

    @Test
    void findById(){
        locationsRepository.save(new Location("d", 2, 23.4));
        locationsRepository.save(new Location("c", 2, 1.3));

        Location location = locationsRepository.findById(2L).get();

        assertThat(location.getName()).isEqualTo("c");
    }

    @Test
    void delete(){
        locationsRepository.save(new Location("d", 2, 23.4));
        locationsRepository.save(new Location("c", 2, 1.3));

        Location location = locationsRepository.findById(2L).get();
        locationsRepository.delete(location);

        List<Location> locations = locationsRepository.findAll();

        assertThat(locations)
                .hasSize(1);

    }
}
