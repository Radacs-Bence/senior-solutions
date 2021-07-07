package training.cars;

import java.time.LocalDate;

public class KmState {

    private LocalDate date;
    private long kiloMeters;

    public KmState(LocalDate date, long kiloMeters) {
        this.date = date;
        this.kiloMeters = kiloMeters;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getKiloMeters() {
        return kiloMeters;
    }
}
