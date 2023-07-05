package Service;

import Data.*;
import Repo.MealsRepository;
import Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class MealsService implements MealsRepository {

    private static MealsService instance;
    private SessionFactory sessionFactory;

    private MealsService(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    private MealsService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static MealsService getInstance() {
        if (instance == null) {
            synchronized (MealsService.class) {
                if (instance == null) {
                    instance = new MealsService();
                }
            }
        }
        return instance;
    }
    public static MealsService getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            synchronized (MealsService.class) {
                if (instance == null) {
                    instance = new MealsService(sessionFactory);
                }
            }
        }
        return instance;
    }

    @Override
    public Meals findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Meals.class, id);
        }
    }

    @Override
    public List<Meals> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Meals> query = builder.createQuery(Meals.class);
            Root<Meals> root = query.from(Meals.class);
            query.select(root);

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public void save(Meals meals) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(meals);
            transaction.commit();
        }
    }

    @Override
    public void update(Meals meals) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(meals);
            transaction.commit();
        }
    }

    @Override
    public void delete(Meals meals) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(meals);
            transaction.commit();
        }
    }

    @Override
    public List<Meals> findByAllergy(Allergy allergy) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Meals> query = builder.createQuery(Meals.class);
            Root<Meals> root = query.from(Meals.class);
            Join<Meals, Allergy> join = root.join("mealsAllergy");
            query.select(root).where(builder.equal(join.get("id"), allergy.getId()));

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Meals> findByDiet(Diet diet) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Meals> query = builder.createQuery(Meals.class);
            Root<Meals> root = query.from(Meals.class);
            Join<Meals, Diet> join = root.join("diets");
            query.select(root).where(builder.equal(join.get("id"), diet.getId()));

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Meals> findByDay(Day day) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Meals> query = builder.createQuery(Meals.class);
            Root<Meals> root = query.from(Meals.class);
            Join<Meals, Day> join = root.join("days");
            query.select(root).where(builder.equal(join.get("id"), day.getId()));

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Meals> findByMealsToDate(Meal meal) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Meals> query = builder.createQuery(Meals.class);
            Root<Meals> root = query.from(Meals.class);
            Join<Meals, Meal> join = root.join("mealsToDate");
            query.select(root).where(builder.equal(join.get("id"), meal.getId()));

            return session.createQuery(query).getResultList();
        }
    }
}