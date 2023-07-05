import Data.Allergy;
import Data.Diet;
import Data.User;
import Repo.DietRepository;
import Service.DietService;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class DietServiceTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<Diet> criteriaQuery;

    @Mock
    Query<Diet> query;

    @Mock
    TypedQuery<Diet> mockTypedQuery;

    @Mock
    private Root<Diet> root;

    @Mock
    Predicate predicate;

    private DietService dietService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        dietService = DietService.getInstance(sessionFactory);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    void testFindById_Success() {
        // Create test data
        Integer dietId = 1;
        Diet expectedDiet = new Diet();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Diet.class, dietId)).thenReturn(expectedDiet);

        // Call the method being tested
        Diet result = dietService.findById(dietId);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedDiet, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).get(Diet.class, dietId);
        verify(session).close();
    }

    @Test
    void testFindAll_Success() {
        // Create test data
        Diet diet = new Diet();
        List<Diet> expectedList = new ArrayList<>();
        expectedList.add(diet);
       // dietService.save(diet);

       // when(session.createQuery(any(CriteriaQuery.class))).thenReturn(query);


        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Diet.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Diet.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);



        // Call the method being tested
        List<Diet> result = dietService.findAll();

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery(Diet.class);
        verify(criteriaQuery).from(Diet.class);
        verify(criteriaQuery).select(root);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
        verify(session).close();
    }

    @Test
    void testSave_Success() {
        // Create test data
        Diet diet = new Diet();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Call the method being tested
        dietService.save(diet);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(session).save(diet);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testUpdate_Success() {
        // Create test data
        Diet diet = new Diet();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Call the method being tested
        dietService.update(diet);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(session).update(diet);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testDelete_Success() {
        // Create test data
        Diet diet = new Diet();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Call the method being tested
        dietService.delete(diet);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(session).delete(diet);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testFindByUser_Success() {
        // Create test data
        User user = new User();
        user.setId(1);

        List<Diet> expectedList = new ArrayList<>();
        expectedList.add(new Diet());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Diet.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Diet.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Mock the behavior of the root object
        Join<Diet, User> join = Mockito.mock(Join.class);
        doReturn(join).when(root).join("users");

        // Call the method being tested
        List<Diet> result = dietService.findByUser(user);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery(Diet.class);
        verify(criteriaQuery).from(Diet.class);
        verify(criteriaQuery).select(root);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
        verify(session).close();
    }

    @Test
    void testFindByAllergy_Success() {
        // Create test data
        Allergy allergy = new Allergy();
        allergy.setId(1);

        List<Diet> expectedList = new ArrayList<>();
        expectedList.add(new Diet());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Diet.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Diet.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Create mock Join and Root objects
        Join<Diet, Allergy> join = Mockito.mock(Join.class);
        doReturn(join).when(root).join("allergies");


        // Call the method being tested
        List<Diet> result = dietService.findByAllergy(allergy);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery(Diet.class);
        verify(criteriaQuery).from(Diet.class);
        verify(criteriaQuery).select(root);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
        verify(session).close();
    }

    @Test
    void testFindByBudget_Success() {
        // Create test data
        Double budget = 100.0;

        List<Diet> expectedList = new ArrayList<>();
        expectedList.add(new Diet());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Diet.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Diet.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Call the method being tested
        List<Diet> result = dietService.findByBudget(budget);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(session).createQuery(Mockito.<CriteriaQuery<Diet>>any());
        verify(session).close();
    }

    @Test
    void testFindByCalories_Success() {
        // Create test data
        Integer calories = 200;

        List<Diet> expectedList = new ArrayList<>();
        expectedList.add(new Diet());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Diet.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Diet.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Call the method being tested
        List<Diet> result = dietService.findByCalories(calories);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(session).createQuery(Mockito.<CriteriaQuery<Diet>>any());
        verify(session).close();
    }

    @Test
    void testFindByName_Success() {
        // Create test data
        String name = "Diet Plan";

        List<Diet> expectedList = new ArrayList<>();
        expectedList.add(new Diet());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Diet.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Diet.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Call the method being tested
        List<Diet> result = dietService.findByName(name);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(session).createQuery(Mockito.<CriteriaQuery<Diet>>any());
        verify(session).close();
    }
}
