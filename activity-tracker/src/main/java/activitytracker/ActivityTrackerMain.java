package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityTrackerMain {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");

    public void saveActivities() {
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

    public List<Activity> listActivities() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Activity> activities = entityManager.createQuery("select a from Activity a", Activity.class)
                .getResultList();
        entityManager.close();

        return activities;
    }

    public Activity findById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Activity activity = entityManager.find(Activity.class, id);
        entityManager.close();

        return activity;
    }

    public void modifyDescById(long id, String desc) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Activity activity = entityManager.find(Activity.class, id);
        activity.setDesc(desc);
        entityManager.getTransaction().commit();

        entityManager.close();
    }



    public void removeById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Activity activity = entityManager.find(Activity.class, id);
        entityManager.remove(activity);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static void main(String[] args) {
        ActivityTrackerMain activityTrackerMain = new ActivityTrackerMain();
        activityTrackerMain.saveActivities();

        System.out.println(activityTrackerMain.listActivities().get(1).getDesc());
        System.out.println(activityTrackerMain.findById(2l).getDesc());
        activityTrackerMain.modifyDescById(2l, "exapmle 23");
        System.out.println(activityTrackerMain.findById(2l).getDesc());
        activityTrackerMain.removeById(2l);
        System.out.println(activityTrackerMain.listActivities().get(1).getDesc());

    }

}
