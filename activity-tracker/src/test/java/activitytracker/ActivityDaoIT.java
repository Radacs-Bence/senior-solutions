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
        activityDao.saveActivity(new Activity(LocalDateTime.now(), "example1", ActivityType.BIKING));
        activityDao.saveActivity(new Activity(LocalDateTime.now(), "example2", ActivityType.HIKING));
        activityDao.saveActivity(new Activity(LocalDateTime.now(), "example3", ActivityType.RUNNING));
        activityDao.saveActivity(new Activity(LocalDateTime.of(1997, 2, 24, 3, 43), "example4", ActivityType.BIKING));
        activityDao.saveActivity(new Activity(LocalDateTime.now(), "example5", ActivityType.BASKETBALL));

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
}