package bikes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BikesApplicationTests {

    @Autowired
    BikeController bikeController;

    @Test
    void getHistoryTest() {
        assertThat(bikeController.getHistory())
                .endsWith("2.9;")
                .startsWith("FH675");
    }

    @Test
    void getUsersTest() {
        assertThat(bikeController.getUsers())
                .endsWith("US346")
                .startsWith("US3434");
    }



}
