package Service;

import Data.Meal;
import Data.User;
import Repo.MealRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class MealService implements MealRepository {
    private SessionFactory sessionFactory;

    public MealService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Meal findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Meal.class, id);
        }
    }

    @Override
    public List<Meal> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Meal> query = builder.createQuery(Meal.class);
            Root<Meal> root = query.from(Meal.class);
            query.select(root);

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public void save(Meal meal) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(meal);
            transaction.commit();
        }
    }

    @Override
    public void update(Meal meal) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(meal);
            transaction.commit();
        }
    }

    @Override
    public void delete(Meal meal) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(meal);
            transaction.commit();
        }
    }

    @Override
    public List<Meal> findByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Meal> query = builder.createQuery(Meal.class);
            Root<Meal> root = query.from(Meal.class);
            Join<Meal, User> join = root.join("users");
            query.select(root).where(builder.equal(join.get("id"), user.getId()));

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Meal> findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Meal> query = builder.createQuery(Meal.class);
            Root<Meal> root = query.from(Meal.class);
            query.select(root).where(builder.equal(root.get("name"), name));

            return session.createQuery(query).getResultList();
        }
    }
}
