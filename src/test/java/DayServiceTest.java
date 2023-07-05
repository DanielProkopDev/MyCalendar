import Data.Day;
import Data.Meals;
import Data.User;
import Service.DayService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.criteria.*;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DayServiceTest {

    private DayService dayService;

    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Transaction transaction;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    @Mock
    private CriteriaQuery<Day> criteriaQuery;
    @Mock
    private Root<Day> root;
    @Mock
    private Predicate predicate;
    @Mock
    private Predicate yearPredicate;
    @Mock
    private Predicate monthPredicate;
    @Mock
    private Predicate dayPredicate;
    @Mock
    private Query<Day> query;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        dayService = DayService.getInstance(sessionFactory);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Day.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Day.class)).thenReturn(root);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
    }

    @Test
    void testFindById_Success() {
        // Create a mocked Day object
        Day expectedDay = new Day();
        Integer dayId = 1;

        // Mock the behavior of the session get method
        when(session.get(Day.class, dayId)).thenReturn(expectedDay);

        // Call the method being tested
        Day result = dayService.findById(dayId);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedDay, result);

        // Verify the interaction with the mocked session
        verify(session).get(Day.class, dayId);
    }

    @Test
    void testFindAll_Success() {
        // Create a mocked list of Day objects
        List<Day> expectedList = Collections.singletonList(new Day());

        // Mock the behavior of the query and result list
        when(query.getResultList()).thenReturn(expectedList);

        // Call the method being tested
        List<Day> result = dayService.findAll();

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
    }

    @Test
    void testSave_Success() {
        // Create a mocked Day object
        Day day = new Day();

        // Call the method being tested
        dayService.save(day);

        // Verify the interactions with the mocked objects
        verify(session).beginTransaction();
        verify(session).save(day);
        verify(transaction).commit();
    }

    @Test
    void testUpdate_Success() {
        // Create a mocked Day object
        Day day = new Day();

        // Call the method being tested
        dayService.update(day);

        // Verify the interactions with the mocked objects
        verify(session).beginTransaction();
        verify(session).update(day);
        verify(transaction).commit();
    }

    @Test
    void testDelete_Success() {
        // Create a mocked Day object
        Day day = new Day();

        // Call the method being tested
        dayService.delete(day);

        // Verify the interactions with the mocked objects
        verify(session).beginTransaction();
        verify(session).delete(day);
        verify(transaction).commit();
    }


    @Test
    void testFindByDate_Success() {
        // Create test data
        Integer yearDate = 2023;
        Integer monthDate = 7;
        Integer dayDate = 5;
        Day day = new Day(yearDate,monthDate,dayDate);
        dayService.save(day);
        List<Day> expectedList = Collections.singletonList(day);

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Day.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Day.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Call the method being tested
        List<Day> result = dayService.findByDate(yearDate, monthDate, dayDate);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(criteriaBuilder).equal(root.get("yearDate"), yearDate);
        verify(criteriaBuilder).equal(root.get("monthDate"), monthDate);
        verify(criteriaBuilder).equal(root.get("dayDate"), dayDate);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
    }

    @Test
    void testFindByYear_Success() {
        // Create test data
        Integer yearDate = 2023;
        Day expectedDay = new Day();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Day.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Day.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.uniqueResult()).thenReturn(expectedDay);

        // Call the method being tested
        Day result = dayService.findByYear(yearDate);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedDay, result);

        // Verify the interactions with the mocked objects
        verify(criteriaBuilder).equal(root.get("yearDate"), yearDate);
        verify(session).createQuery(criteriaQuery);
        verify(query).uniqueResult();
    }

    @Test
    void testFindByMonth_Success() {
        // Create test data
        Integer monthDate = 7;
        Day expectedDay = new Day();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Day.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Day.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.uniqueResult()).thenReturn(expectedDay);

        // Call the method being tested
        Day result = dayService.findByMonth(monthDate);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedDay, result);

        // Verify the interactions with the mocked objects
        verify(criteriaBuilder).equal(root.get("monthDate"), monthDate);
        verify(session).createQuery(criteriaQuery);
        verify(query).uniqueResult();
    }

    @Test
    void testFindByDay_Success() {
        // Create test data
        Integer dayDate = 5;
        Day expectedDay = new Day();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Day.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Day.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.uniqueResult()).thenReturn(expectedDay);

        // Call the method being tested
        Day result = dayService.findByDay(dayDate);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedDay, result);

        // Verify the interactions with the mocked objects
        verify(criteriaBuilder).equal(root.get("dayDate"), dayDate);
        verify(session).createQuery(criteriaQuery);
        verify(query).uniqueResult();
    }

    @Test
    void testUpdateMeals_Success() {
        // Create test data
        int dayId = 1;
        List<Meals> mealsList = Collections.singletonList(new Meals());
        Day day = new Day();
        day.setId(dayId);

        // Mock the behavior of the session get method
        when(session.get(Day.class, dayId)).thenReturn(day);

        // Call the method being tested
        boolean result = dayService.updateMeals(dayId, mealsList);

        // Assert the result
        assertTrue(result);

        // Verify the interactions with the mocked objects
        verify(session).get(Day.class, dayId);
        verify(session).update(day);
        verify(transaction).commit();
    }

    @Test
    void testUpdateMeals_DayNotFound() {
        // Create test data
        int dayId = 1;
        List<Meals> mealsList = Collections.singletonList(new Meals());

        // Mock the behavior of the session get method
        when(session.get(Day.class, dayId)).thenReturn(null);

        // Call the method being tested
        boolean result = dayService.updateMeals(dayId, mealsList);

        // Assert the result
        assertFalse(result);

        // Verify the interaction with the mocked session
        verify(session).get(Day.class, dayId);
        verify(session, never()).update(any());
        verify(transaction, never()).commit();
    }

    @Test
    void testUpdateUsers_Success() {
        // Create test data
        int dayId = 1;
        List<User> userList = Collections.singletonList(new User());
        Day day = new Day();
        day.setId(dayId);

        // Mock the behavior of the session get method
        when(session.get(Day.class, dayId)).thenReturn(day);

        // Call the method being tested
        boolean result = dayService.updateUsers(dayId, userList);

        // Assert the result
        assertTrue(result);

        // Verify the interactions with the mocked objects
        verify(session).get(Day.class, dayId);
        verify(session).update(day);
        verify(transaction).commit();
    }

    @Test
    void testUpdateUsers_DayNotFound() {
        // Create test data
        int dayId = 1;
        List<User> userList = Collections.singletonList(new User());

        // Mock the behavior of the session get method
        when(session.get(Day.class, dayId)).thenReturn(null);

        // Call the method being tested
        boolean result = dayService.updateUsers(dayId, userList);

        // Assert the result
        assertFalse(result);

        // Verify the interaction with the mocked session
        verify(session).get(Day.class, dayId);
        verify(session, never()).update(any());
        verify(transaction, never()).commit();
    }
}

