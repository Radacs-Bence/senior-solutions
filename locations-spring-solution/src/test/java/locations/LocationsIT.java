package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocationsIT {

	@Autowired
	LocationsController locationsController;

	@Test
	void contextLoads() {
		locationsController.createLocation(new CreateLocationCommand("a", 1, 1));
		locationsController.createLocation(new CreateLocationCommand("b", 1, 1.3));
		locationsController.createLocation(new CreateLocationCommand("c", 2, 1.3));
		locationsController.createLocation(new CreateLocationCommand("d", 2, 23.4));
		String result = locationsController.listLocations(Optional.empty());

		assertThat(result).startsWith("1, a, 1.0, 1.").endsWith("4, d, 2.0, 23.4");

	}

}
