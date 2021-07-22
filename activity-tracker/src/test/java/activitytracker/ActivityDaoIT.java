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

        assertEquals(5, activities.size());
        assertEquals("example2", activities.get(1).getDescription());

    }

    @Test
    void findActivityById() {
        Activity activity = activityDao.findActivityById(3);

        assertEquals("example3", activity.getDescription());

    }

    @Test
    void deleteActivity() {
        activityDao.deleteActivity(2);

        List<Activity> activities = activityDao.listActivities();

        assertEquals(4, activities.size());
        assertEquals("example3", activities.get(1).getDescription());
    }

    @Test
    void updateActivity() {
        activityDao.updateActivity(2, "Example21");
        Activity activity = activityDao.findActivityById(2);

        assertEquals("Example21", activity.getDescription());
        assertNotEquals(null, activity.getUpdatedAt());
    }

    @Test
    void findActivityByIdWithLabels() {



        Activity activity = activityDao.findActivityByIdWithLabels(5);

        assertEquals(2, activity.getLabels().size());
        assertEquals("LabelEx1", activity.getLabels().get(0));
    }


    @Test
    void findActivityByIdWithTrackPoints() {
        Activity activity = activityDao.findActivityByIdWithTrackPoints(3);

        assertEquals(4 ,activity.getTrackPoints().size());
        assertEquals(4 ,activity.getTrackPoints().get(3).getLat());

    }

    @Test
    void findTrackPointCoordinatesByDate() {
        List<Coordinate> coordinates1 = activityDao.findTrackPointCoordinatesByDate(LocalDateTime.of(2022, 2, 22, 12, 42), 0, 3);
        List<Coordinate> coordinates2 = activityDao.findTrackPointCoordinatesByDate(LocalDateTime.MIN, 1, 2);

        assertEquals(2, coordinates1.size());
        assertEquals(4 ,coordinates1.get(0).getLat());

        assertEquals(2, coordinates2.size());
        assertEquals(3 ,coordinates2.get(0).getLat());
        assertEquals(1 ,coordinates2.get(1).getLat());
    }

    @Test
    void findTrackPointCountByActivity() {
        List<Object[]> count = activityDao.findTrackPointCountByActivity();

        assertEquals(1, count.size());
        assertEquals(4L, count.get(0)[1]);
    }
}