package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class ActivityTrackerMain {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");

    public void saveActivities(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Activity activity1 = new Activity(LocalDateTime.now(), "example1", ActivityType.BIKING);
        entityManager.persist(activity1);

        Activity activity2 = new Activity(LocalDateTime.now(), "example2", ActivityType.HIKING);
        entityManager.persist(activity2);

        Activity activity3 = new Activity(LocalDateTime.now(), "example3", ActivityType.RUNNING);
        entityManager.persist(activity3);

        Activity activity4 = new Activity(LocalDateTime.of(1997, 2, 24, 3, 43), "example4", ActivityType.BIKING);
        entityManager.persist(activity4);

        Activity activity5 = new Activity(LocalDateTime.now(), "example5", ActivityType.BASKETBALL);
        entityManager.persist(activity5);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void main(String[] args) {
        new ActivityTrackerMain().saveActivities();
    }

}
