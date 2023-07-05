import Data.Allergy;
import Service.AllergyService;
import Service.FavoriteMealsService;
import Data.FavoriteMeals;
import Data.User;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FavoriteMealsServiceTest {
    private FavoriteMealsService favoriteMealsService;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<FavoriteMeals> criteriaQuery;

    @Mock
    Query<FavoriteMeals> query;

    @Mock
    TypedQuery<FavoriteMeals> mockTypedQuery;

    @Mock
    private Root<FavoriteMeals> root;

    @Mock
    Predicate predicate;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        favoriteMealsService = FavoriteMealsService.getInstance(sessionFactory);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(FavoriteMeals.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(FavoriteMeals.class)).thenReturn(root);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
    }

    @Test
    void testFindById_Success() {
        // Create test data
        Integer id = 1;
        FavoriteMeals favoriteMeals = new FavoriteMeals();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(FavoriteMeals.class, id)).thenReturn(favoriteMeals);

        // Call the method being tested
        FavoriteMeals result = favoriteMealsService.findById(id);

        // Assert the result
        assertNotNull(result);
        assertEquals(favoriteMeals, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).get(FavoriteMeals.class, id);
        verify(session).close();
    }

    @Test
    void testFindAll_Success() {
        // Create test data
        List<FavoriteMeals> expectedList = new ArrayList<>();
        expectedList.add(new FavoriteMeals());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(FavoriteMeals.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(FavoriteMeals.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Call the method being tested
        List<FavoriteMeals> result = favoriteMealsService.findAll();

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery(FavoriteMeals.class);
        verify(criteriaQuery).from(FavoriteMeals.class);
        verify(criteriaQuery).select(root);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
        verify(session).close();
    }

    @Test
    void testSave_Success() {
        // Create test data
        FavoriteMeals favoriteMeals = new FavoriteMeals();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Call the method being tested
        favoriteMealsService.save(favoriteMeals);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(session).save(favoriteMeals);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testUpdate_Success() {
        // Create test data
        FavoriteMeals favoriteMeals = new FavoriteMeals();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Call the method being tested
        favoriteMealsService.update(favoriteMeals);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(session).update(favoriteMeals);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testDelete_Success() {
        // Create test data
        FavoriteMeals favoriteMeals = new FavoriteMeals();

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Call the method being tested
        favoriteMealsService.delete(favoriteMeals);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(session).delete(favoriteMeals);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testFindByUser_Success() {
        // Create test data
        User user = new User();
        user.setId(1);

        List<FavoriteMeals> expectedList = new ArrayList<>();
        expectedList.add(new FavoriteMeals());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(FavoriteMeals.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(FavoriteMeals.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Mock the behavior of the root object
        Join<FavoriteMeals, User> join = Mockito.mock(Join.class);
        doReturn(join).when(root).join("users");
        // Call the method being tested
        List<FavoriteMeals> result = favoriteMealsService.findByUser(user);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(session).createQuery(any(CriteriaQuery.class));
        verify(session).close();
    }

    @Test
    void testFindByName_Success() {
        // Create test data
        String name = "meal";

        List<FavoriteMeals> expectedList = new ArrayList<>();
        expectedList.add(new FavoriteMeals());

        // Mock the behavior of the session and session factory
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(FavoriteMeals.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(FavoriteMeals.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedList);

        // Call the method being tested
        List<FavoriteMeals> result = favoriteMealsService.findByName(name);

        // Assert the result
        assertNotNull(result);
        assertEquals(expectedList, result);

        // Verify the interactions with the mocked objects
        verify(sessionFactory).openSession();
        verify(session).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery(FavoriteMeals.class);
        verify(criteriaQuery).from(FavoriteMeals.class);
        verify(criteriaQuery).select(root);
        verify(session).createQuery(criteriaQuery);
        verify(query).getResultList();
        verify(session).close();
    }
}
