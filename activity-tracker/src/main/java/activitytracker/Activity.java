package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Activities")
public class Activity {

    @Id
    @GeneratedValue(generator = "Activity_Gen")
    @TableGenerator(name = "Activity_Gen", table = "act_id_gen", pkColumnName = "id_gen ", pkColumnValue = "id_val")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "description", length = 200, nullable = false)
    private String desc;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ActivityType type;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ElementCollection
    private List<String> labels;

    @OneToMany(cascade=CascadeType.PERSIST)
    @OrderBy("time asc")
    private List<TrackPoint> trackPoints;



    public Activity(LocalDateTime startTime, String desc, ActivityType type) {
        this.startTime = startTime;
        this.desc = desc;
        this.type = type;
    }

    public Activity() {

    }


    public void addLabel(String label) {
        if (labels == null) {
            labels = new ArrayList<>();
        }
        labels.add(label);
    }

    public void addTrackPoint(TrackPoint trackPoint){
        if (trackPoints == null){
            trackPoints = new ArrayList<>();
        }
        trackPoint.setActivity(this);
        trackPoints.add(trackPoint);
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

     public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = trackPoints;
    }


}
