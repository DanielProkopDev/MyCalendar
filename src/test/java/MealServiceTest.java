import Data.Diet;
import Data.Meal;
import Data.User;
import Service.MealService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MealServiceTest {
    private MealService mealService;
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<Meal> criteriaQuery;

    @Mock
    Query<Meal> query;

    @Mock
    TypedQuery<Meal> mockTypedQuery;

    @Mock
    private Root<Meal> root;

    @Mock
    Predicate predicate;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mealService = MealService.getInstance(sessionFactory);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    void testFindById_Success() {
        // Create test data
        Integer mealId = 1;
        Meal expectedMeal = new Meal();
        expectedMeal.setId(mealId);

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Meal.class, mealId)).thenReturn(expectedMeal);

        // Call the method being tested
        Meal result = mealService.findById(mealId);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedMeal, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).get(Meal.class, mealId);
        verify(session).close();
    }

    @Test
    void testFindAll_Success() {
        // Create test data
        List<Meal> expectedList = new ArrayList<>();
        expectedList.add(new Meal());
        expectedList.add(new Meal());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Meal.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Meal.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Call the method being tested
        List<Meal> result = mealService.findAll();

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery(Meal.class);
        verify(criteriaQuery).from(Meal.class);
        verify(criteriaQuery).select(root);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
        verify(session).close();
    }

    @Test
    void testSave_Success() {
        // Create test data
        Meal meal = new Meal();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Call the method being tested
        mealService.save(meal);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(session).save(meal);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testUpdate_Success() {
        // Create test data
        Meal meal = new Meal();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Call the method being tested
        mealService.update(meal);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(session).update(meal);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testDelete_Success() {
        // Create test data
        Meal meal = new Meal();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Call the method being tested
        mealService.delete(meal);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(session).delete(meal);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testFindByUser_Success() {
        // Create test data
        User user = new User();
        user.setId(1);

        List<Meal> expectedList = new ArrayList<>();
        expectedList.add(new Meal());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Meal.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Meal.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Mock the behavior of the root object
        Join<Meal, User> join = Mockito.mock(Join.class);
        doReturn(join).when(root).join("users");
        // Call the method being tested
        List<Meal> result = mealService.findByUser(user);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery(Meal.class);
        verify(criteriaQuery).from(Meal.class);
        verify(criteriaQuery).select(root);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
        verify(session).close();
    }

    @Test
    void testFindByName_Success() {
        // Create test data
        String name = "Chicken Curry";

        List<Meal> expectedList = new ArrayList<>();
        expectedList.add(new Meal());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Meal.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Meal.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Call the method being tested
        List<Meal> result = mealService.findByName(name);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery(Meal.class);
        verify(criteriaQuery).from(Meal.class);
        verify(criteriaQuery).select(root);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
        verify(session).close();
    }
}
