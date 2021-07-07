package training.cars;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private String brand;
    private String type;
    private int age;
    private Status status;
    private List<KmState> kmStates = new ArrayList<>();

    public Car(String brand, String type, int age, Status status) {
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.status = status;
    }

    public void addKmState(KmState kmState){
        kmStates.add(kmState);
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public Status getStatus() {
        return status;
    }

    public List<KmState> getKmStates() {
        return new ArrayList<>(kmStates);
    }
}
