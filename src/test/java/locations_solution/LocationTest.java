package locations_solution;

import locations_solution.Location;
import locations_solution.LocationParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    LocationParser locationParser;

    @BeforeEach
    void init(){
        locationParser = new LocationParser();
    }

    @Test
    void validityTestLat(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()-> locationParser.parse("A, -91, 1.3"));
        assertEquals("Invalid latitude: -91.0", ex.getMessage());
    }

    @Test
    void validityTestLon(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()-> locationParser.parse("A, 1, 181"));
        assertEquals("Invalid longitude: 181.0", ex.getMessage());
    }

    @Test
    void validityTestBoth(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()-> locationParser.parse("A, -91, 181"));
        assertEquals("Invalid latitude: -91.0 Invalid longitude: 181.0", ex.getMessage());
    }

    @Test
    void testParse(){
        Location location = locationParser.parse("A,1,1.3");
        assertEquals(1.3, location.getLon());
    }

    @Test
    void testParseWithAssertAll(){
        Location location = locationParser.parse("A,1,1.3");
        assertAll(
                ()-> assertEquals("A", location.getName()),
                ()-> assertEquals(1, location.getLat()),
                ()-> assertEquals(1.3, location.getLon()));
    }

    @Test
    void testParseWrongText(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()-> locationParser.parse("A"));
        assertEquals("Text is not properly formatted", ex.getMessage());
    }

    @Test
    void testParseNotNumbers(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()-> locationParser.parse("A,b,1.3"));
        assertEquals("Coordinates b and/or 1.3 are not numbers", ex.getMessage());
    }

    @Test
    void isOnEquatorTrue() {
        Location location = locationParser.parse("A,0,1.3");
        assertTrue(location.isOnEquator());

    }
    @Test
    void isOnEquatorFalse() {
        Location location = locationParser.parse("A,1,1.3");
        assertFalse(location.isOnEquator());
    }

    @Test
    void isOnPrimeMeridianTrue() {
        Location location = locationParser.parse("A,1,0");
        assertTrue(location.isOnPrimeMeridian());
    }

    @Test
    void isOnPrimeMeridianFalse() {
        Location location = locationParser.parse("A,1,1.3");
        assertFalse(location.isOnPrimeMeridian());
    }

    @Test
    void twoParsesAreDifferent(){
        assertNotSame(locationParser.parse("A,1,1.3"), locationParser.parse("A,1,1.3"));
    }

    @Test
    void distanceFrom() {
        Location locationStart = locationParser.parse("Budapest,47.30,19.05");
        Location locationEnd = locationParser.parse("Vienna,48.14,16.20"); //adatok: https://www.infoplease.com/world/geography/major-cities-latitude-longitude-and-corresponding-time-zones
        double distance = locationStart.distanceFrom(locationEnd);

        assertEquals(233, distance, 1); // eredmény: https://www.nhc.noaa.gov/gccalc.shtml
    }
}