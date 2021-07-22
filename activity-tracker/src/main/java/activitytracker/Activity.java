package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Activities")
public class Activity {

    @Id
    @GeneratedValue(generator="Activity_Gen")
    @TableGenerator(name="Activity_Gen", table = "act_id_gen", pkColumnName = "id_gen ", pkColumnValue = "id_val")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "description", length = 200, nullable = false)
    private String desc;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ActivityType type;

    public Activity(LocalDateTime startTime, String desc, ActivityType type) {
        this.startTime = startTime;
        this.desc = desc;
        this.type = type;
    }

    public Activity() {

    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }
}
