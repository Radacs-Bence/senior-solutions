package locations_solution;

import locations_solution.Location;
import locations_solution.LocationParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationNestedTest {


    LocationParser locationParser;
    Location location;

    @BeforeEach
    void init(){
        locationParser = new LocationParser();
    }

    @Nested
    class ResultIsFalse{

        @BeforeEach
        void init(){
            location = locationParser.parse("A, 1, 1.3");
        }

        @Test
        void isOnEquatorFalse() {
            assertFalse(location.isOnEquator());
        }

        @Test
        void isOnPrimeMeridianFalse() {
            assertFalse(location.isOnPrimeMeridian());
        }
    }

    @Nested
    class ResultIsTrue{

        @BeforeEach
        void init(){
            location = locationParser.parse("A, 0, 0");
        }

        @Test
        void isOnEquatorTrue() {
            assertTrue(location.isOnEquator());
        }

        @Test
        void isOnPrimeMeridianTrue() {
            assertTrue(location.isOnPrimeMeridian());
        }

    }

}
