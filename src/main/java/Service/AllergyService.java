package Service;

import Data.Allergy;
import Data.Diet;
import Data.Meals;
import Data.User;
import Repo.AllergyRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class AllergyService implements AllergyRepository {
    private SessionFactory sessionFactory;

    public AllergyService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Allergy findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Allergy.class, id);
        }
    }

    @Override
    public List<Allergy> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Allergy> query = builder.createQuery(Allergy.class);
            Root<Allergy> root = query.from(Allergy.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public void save(Allergy allergy) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(allergy);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Allergy allergy) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(allergy);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Allergy allergy) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(allergy);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Allergy> findByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Allergy> query = builder.createQuery(Allergy.class);
            Root<Allergy> root = query.from(Allergy.class);

            Predicate userPredicate = builder.equal(root.join("users"), user);

            query.select(root).where(userPredicate);

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Allergy> findByDiet(Diet diet) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Allergy> query = builder.createQuery(Allergy.class);
            Root<Allergy> root = query.from(Allergy.class);

            Predicate dietPredicate = builder.equal(root.join("diets"), diet);

            query.select(root).where(dietPredicate);

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Allergy> findByMeal(Meals meals) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Allergy> query = builder.createQuery(Allergy.class);
            Root<Allergy> root = query.from(Allergy.class);

            Predicate mealPredicate = builder.equal(root.join("meals"), meals);

            query.select(root).where(mealPredicate);

            return session.createQuery(query).getResultList();
        }
    }
}
