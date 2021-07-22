package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "description")
    private String desc;
    private ActivityType type;

    public Activity(LocalDateTime startTime, String desc, ActivityType type) {
        this.startTime = startTime;
        this.desc = desc;
        this.type = type;
    }

    public Activity() {

    }
}
