package Service;

import Data.Allergy;
import Data.Diet;
import Data.User;
import Repo.DietRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class DietService implements DietRepository {
    private SessionFactory sessionFactory;

    public DietService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Diet findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Diet.class, id);
        }
    }

    @Override
    public List<Diet> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Diet> query = builder.createQuery(Diet.class);
            Root<Diet> root = query.from(Diet.class);
            query.select(root);

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public void save(Diet diet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(diet);
            transaction.commit();
        }
    }

    @Override
    public void update(Diet diet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(diet);
            transaction.commit();
        }
    }

    @Override
    public void delete(Diet diet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(diet);
            transaction.commit();
        }
    }

    @Override
    public List<Diet> findByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Diet> query = builder.createQuery(Diet.class);
            Root<Diet> root = query.from(Diet.class);
            Join<Diet, User> join = root.join("users");
            query.select(root).where(builder.equal(join.get("id"), user.getId()));

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Diet> findByAllergy(Allergy allergy) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Diet> query = builder.createQuery(Diet.class);
            Root<Diet> root = query.from(Diet.class);
            Join<Diet, Allergy> join = root.join("allergies");
            query.select(root).where(builder.equal(join.get("id"), allergy.getId()));

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Diet> findByBudget(Double budget) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Diet> query = builder.createQuery(Diet.class);
            Root<Diet> root = query.from(Diet.class);
            query.select(root).where(builder.equal(root.get("budget"), budget));

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Diet> findByCalories(Integer calories) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Diet> query = builder.createQuery(Diet.class);
            Root<Diet> root = query.from(Diet.class);
            query.select(root).where(builder.equal(root.get("calories"), calories));

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Diet> findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Diet> query = builder.createQuery(Diet.class);
            Root<Diet> root = query.from(Diet.class);
            query.select(root).where(builder.equal(root.get("name"), name));

            return session.createQuery(query).getResultList();
        }
    }
}