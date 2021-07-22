package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQuery(name = "TrackPointCoordinatesByDate", query = "select new activitytracker.Coordinate(t.lat, t.lon) from TrackPoint t where t.time > :time")
public class TrackPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;

    private long lat;
    private long lon;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public TrackPoint(LocalDateTime time, long lat, long lon) {
        this.time = time;
        this.lat = lat;
        this.lon = lon;
    }

    public TrackPoint() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
