import Data.*;
import Service.AllergyService;
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

class AllergyServiceTest {
    private AllergyService allergyService;
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<Allergy> criteriaQuery;

    @Mock
    Query<Allergy> query;

    @Mock
    TypedQuery<Allergy> mockTypedQuery;

    @Mock
    private Root<Allergy> root;

    @Mock
    Predicate predicate;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        allergyService = AllergyService.getInstance(sessionFactory);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Allergy.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Allergy.class)).thenReturn(root);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
    }

    @Test
    void testFindById_Success() {
        // Create test data
        Integer allergyId = 1;
        Allergy expectedAllergy = new Allergy();
        expectedAllergy.setId(allergyId);

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Allergy.class, allergyId)).thenReturn(expectedAllergy);

        // Call the method being tested
        Allergy result = allergyService.findById(allergyId);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedAllergy, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).get(Allergy.class, allergyId);
        verify(session).close();
    }

    @Test
    void testFindAll_Success() {
        // Create test data
        List<Allergy> expectedList = new ArrayList<>();
        expectedList.add(new Allergy());
        expectedList.add(new Allergy());


        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Allergy.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Allergy.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Call the method being tested
        List<Allergy> result = allergyService.findAll();

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery(Allergy.class);
        verify(criteriaQuery).from(Allergy.class);
        verify(criteriaQuery).select(root);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
        verify(session).close();
    }

    @Test
    void testSave_Success() {
        // Create test data
        Allergy allergy = new Allergy();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Call the method being tested
        allergyService.save(allergy);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(session).save(allergy);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testUpdate_Success() {
        // Create test data
        Allergy allergy = new Allergy();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Call the method being tested
        allergyService.update(allergy);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(session).update(allergy);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testDelete_Success() {
        // Create test data
        Allergy allergy = new Allergy();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Call the method being tested
        allergyService.delete(allergy);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(session).delete(allergy);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testFindByUser_Success() {
        // Create test data
        User user = new User();
        user.setId(1);

        List<Allergy> expectedList = new ArrayList<>();
        expectedList.add(new Allergy());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Allergy.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Allergy.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Mock the behavior of the root object
        Join<Allergy, User> join = Mockito.mock(Join.class);
        doReturn(join).when(root).join("users");
        // Call the method being tested
        List<Allergy> result = allergyService.findByUser(user);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery(Allergy.class);
        verify(criteriaQuery).from(Allergy.class);
        verify(criteriaQuery).select(root);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
        verify(session).close();
    }

    @Test
    void testFindByDiet_Success() {
        // Create test data
        Diet diet = new Diet();
        diet.setId(1);

        List<Allergy> expectedList = new ArrayList<>();
        expectedList.add(new Allergy());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Allergy.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Allergy.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Mock the behavior of the root object
        Join<Allergy, Diet> join = Mockito.mock(Join.class);
        doReturn(join).when(root).join("diets");
        // Call the method being tested
        List<Allergy> result = allergyService.findByDiet(diet);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery(Allergy.class);
        verify(criteriaQuery).from(Allergy.class);
        verify(criteriaQuery).select(root);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
        verify(session).close();
    }

    @Test
    void testFindByMeal_Success() {
        // Create test data
        Meals meals = new Meals();
        meals.setId(1);

        List<Allergy> expectedList = new ArrayList<>();
        expectedList.add(new Allergy());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Allergy.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Allergy.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Mock the behavior of the root object
        Join<Allergy, Meals> join = Mockito.mock(Join.class);
        doReturn(join).when(root).join("meals");
        // Call the method being tested
        List<Allergy> result = allergyService.findByMeal(meals);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery(Allergy.class);
        verify(criteriaQuery).from(Allergy.class);
        verify(criteriaQuery).select(root);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
        verify(session).close();
    }
}
