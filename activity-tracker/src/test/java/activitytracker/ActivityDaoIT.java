package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoIT {

    ActivityDao activityDao;

    @BeforeEach
    void init(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(entityManagerFactory);

        Activity example1 = new Activity(LocalDateTime.now(), "example1", ActivityType.BIKING);
        activityDao.saveActivity(example1);

        Activity example2 = new Activity(LocalDateTime.now(), "example2", ActivityType.HIKING);
        activityDao.saveActivity(example2);

        Activity example3 = new Activity(LocalDateTime.now(), "example3", ActivityType.RUNNING);
       example3.addTrackPoint(new TrackPoint(LocalDateTime.of(2024, 4, 24, 14, 44), 4, 4));
        example3.addTrackPoint(new TrackPoint(LocalDateTime.of(2023, 3, 23, 13, 43), 3, 3));
        example3.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 1, 21, 11, 41), 1, 1));
        example3.addTrackPoint(new TrackPoint(LocalDateTime.of(2022, 2, 22, 12, 42), 2, 2));

        activityDao.saveActivity(example3);

        Activity example4 = new Activity(LocalDateTime.of(1997, 2, 24, 3, 43), "example4", ActivityType.BIKING);
        activityDao.saveActivity(example4);

        Activity example5 = new Activity(LocalDateTime.now(), "example5", ActivityType.BASKETBALL);
        example5.addLabel("LabelEx1");
        example5.addLabel("LabelEx2");
        activityDao.saveActivity(example5);
    }

    @Test
    void listActivities() {
        List<Activity> activities = activityDao.listActivities();

        assertEquals(activities.size(), 5);
        assertEquals(activities.get(1).getDesc(), "example2");

    }

    @Test
    void findActivityById() {
        Activity activity = activityDao.findActivityById(3);

        assertEquals(activity.getDesc(), "example3");

    }

    @Test
    void deleteActivity() {
        activityDao.deleteActivity(2);

        List<Activity> activities = activityDao.listActivities();

        assertEquals(activities.size(), 4);
        assertEquals(activities.get(1).getDesc(), "example3");
    }

    @Test
    void updateActivity() {
        activityDao.updateActivity(2, "Example21");
        Activity activity = activityDao.findActivityById(2);

        assertEquals(activity.getDesc(), "Example21");
        assertNotEquals(activity.getUpdatedAt(), null);
    }

    @Test
    void findActivityByIdWithLabels() {



        Activity activity = activityDao.findActivityByIdWithLabels(5);

        assertEquals(activity.getLabels().size(), 2);
        assertEquals(activity.getLabels().get(0), "LabelEx1");
    }


    @Test
    void findActivityByIdWithTrackPoints() {
        Activity activity = activityDao.findActivityByIdWithTrackPoints(3);

        assertEquals(activity.getTrackPoints().size(), 4);
        assertEquals(activity.getTrackPoints().get(3).getLat(), 4);

    }
}