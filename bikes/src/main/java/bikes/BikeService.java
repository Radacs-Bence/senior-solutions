package bikes;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BikeService {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private List<Bike> bikes;

    public BikeService() {
        this.bikes = new ArrayList<>();
    }

    public void read(){
        try {
            BufferedReader reader = Files.newBufferedReader(Path.of("C:\\training\\senior-solutions\\bikes\\src\\main\\resources\\bikes\\bikes.csv"));
            String line;
            while ((line = reader.readLine())  != null){
                formatAndAdd(line);
            }
        } catch (IOException e) {
            throw new IllegalStateException("File not found");
        }

    }

    private void formatAndAdd(String line){
        String[] parts = line.split(";");
        LocalDateTime dateTime = LocalDateTime.parse(parts[2], FORMATTER);
        bikes.add(new Bike(parts[0], parts[1], dateTime, Double.parseDouble(parts[3])));
    }

    public List<Bike> getBikes() {
        return new ArrayList<>(bikes);
    }

}
