package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityDao {

    EntityManagerFactory entityManagerFactory;

    public ActivityDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveActivity(Activity activity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(activity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Activity> listActivities() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Activity> activities = entityManager.createQuery("select a from Activity a", Activity.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return activities;
    }

    public Activity findActivityById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Activity activity = entityManager.find(Activity.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();

        return activity;
    }

    public void deleteActivity(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Activity activity = entityManager.find(Activity.class, id);
        entityManager.remove(activity);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
