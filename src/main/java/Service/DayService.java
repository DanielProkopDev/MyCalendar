package Service;

import Data.Day;
import Repo.DayRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class DayService implements DayRepository {
    private SessionFactory sessionFactory;

    public DayService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Day findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Day.class, id);
        }
    }

    @Override
    public List<Day> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Day> query = builder.createQuery(Day.class);
            Root<Day> root = query.from(Day.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public void save(Day day) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(day);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Day day) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(day);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Day day) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(day);
            session.getTransaction().commit();
        }
    }

    @Override
    public Day findByDate(Integer yearDate, Integer monthDate, Integer dayDate) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Day> query = builder.createQuery(Day.class);
            Root<Day> root = query.from(Day.class);

            Predicate yearPredicate = builder.equal(root.get("yearDate"), yearDate);
            Predicate monthPredicate = builder.equal(root.get("monthDate"), monthDate);
            Predicate dayPredicate = builder.equal(root.get("dayDate"), dayDate);

            query.select(root).where(yearPredicate, monthPredicate, dayPredicate);

            return session.createQuery(query).uniqueResult();
        }
    }

    @Override
    public Day findByYear(Integer yearDate) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Day> query = builder.createQuery(Day.class);
            Root<Day> root = query.from(Day.class);

            Predicate yearPredicate = builder.equal(root.get("yearDate"), yearDate);

            query.select(root).where(yearPredicate);

            return session.createQuery(query).uniqueResult();
        }
    }

    @Override
    public Day findByMonth(Integer monthDate) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Day> query = builder.createQuery(Day.class);
            Root<Day> root = query.from(Day.class);

            Predicate monthPredicate = builder.equal(root.get("monthDate"), monthDate);

            query.select(root).where(monthPredicate);

            return session.createQuery(query).uniqueResult();
        }
    }

    @Override
    public Day findByDay(Integer dayDate) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Day> query = builder.createQuery(Day.class);
            Root<Day> root = query.from(Day.class);

            Predicate dayPredicate = builder.equal(root.get("dayDate"), dayDate);

            query.select(root).where(dayPredicate);

            return session.createQuery(query).uniqueResult();
        }
    }
}
