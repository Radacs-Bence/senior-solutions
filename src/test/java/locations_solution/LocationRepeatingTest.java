package locations_solution;

import locations_solution.Location;
import locations_solution.LocationParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import static org.junit.jupiter.api.Assertions.*;

public class LocationRepeatingTest {

    LocationParser locationParser = new LocationParser();
    Location[] locations = new Location[6];
    String name = "Hely: ";

    @BeforeEach
    void init(){
        for (int i = 0; i < 6; i++) {
            locations [i] = locationParser.parse(String.format("%s,%d,1", name + (i + 1), i%2));
        }
    }

    @RepeatedTest(6)
    void isOnEquatorRepeatedTest(RepetitionInfo repetitionInfo){
        assertEquals((repetitionInfo.getCurrentRepetition() - 1) % 2 == 0,
                locations[repetitionInfo.getCurrentRepetition() - 1].isOnEquator());
    }
}
