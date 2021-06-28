package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocationsIT {

	@Autowired
	LocationsController locationsController;

	@Test
	void contextLoads() {
		String result = locationsController.getLocations();

		assertThat(result).startsWith("a").endsWith("d");

	}

}
