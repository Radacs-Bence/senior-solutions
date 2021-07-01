package org.training360.musicstore;


import lombok.Data;

import java.time.LocalDate;


@Data
public class Instrument {

    private long id;
    private String brand;
    private InstrumentType type;
    private int price;
    private LocalDate postDate;

    public Instrument(long id, String brand, InstrumentType type, int price) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.postDate = LocalDate.now();
    }
}
