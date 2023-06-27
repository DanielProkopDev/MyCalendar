package Service;

import Data.FavoriteMeals;
import Data.User;
import Repo.FavoriteMealsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class FavoriteMealsService implements FavoriteMealsRepository {
    private SessionFactory sessionFactory;

    public FavoriteMealsService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public FavoriteMeals findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(FavoriteMeals.class, id);
        }
    }

    @Override
    public List<FavoriteMeals> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<FavoriteMeals> query = builder.createQuery(FavoriteMeals.class);
            Root<FavoriteMeals> root = query.from(FavoriteMeals.class);
            query.select(root);

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public void save(FavoriteMeals favoriteMeals) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(favoriteMeals);
            transaction.commit();
        }
    }

    @Override
    public void update(FavoriteMeals favoriteMeals) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(favoriteMeals);
            transaction.commit();
        }
    }

    @Override
    public void delete(FavoriteMeals favoriteMeals) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(favoriteMeals);
            transaction.commit();
        }
    }

    @Override
    public List<FavoriteMeals> findByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<FavoriteMeals> query = builder.createQuery(FavoriteMeals.class);
            Root<FavoriteMeals> root = query.from(FavoriteMeals.class);
            Join<FavoriteMeals, User> join = root.join("users");
            query.select(root).where(builder.equal(join.get("id"), user.getId()));

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<FavoriteMeals> findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<FavoriteMeals> query = builder.createQuery(FavoriteMeals.class);
            Root<FavoriteMeals> root = query.from(FavoriteMeals.class);
            query.select(root).where(builder.equal(root.get("name"), name));

            return session.createQuery(query).getResultList();
        }
    }
}