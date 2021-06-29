package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    private Long id;
    private String name;
    private double lat;
    private double lon;

    @Override
    public String toString() {
        return  id +
                ", " + name +
                ", " + lat +
                ", " + lon;
    }
}
