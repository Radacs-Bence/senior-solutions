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

        activity.setCreatedAt(LocalDateTime.now());
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

    public void updateActivity(long id, String description){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Activity activity = entityManager.find(Activity.class, id);
        activity.setDesc(description);
        activity.setUpdatedAt(LocalDateTime.now());
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public Activity findActivityByIdWithLabels(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Activity activity = entityManager.createQuery("select a from Activity a join fetch a.labels where a.id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();

        return activity;
    }
}
