import Data.*;
import Service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<User> criteriaQuery;

    @Mock
    Query<User> query;

    @Mock
    TypedQuery<User> mockTypedQuery;

    @Mock
    private Root<User> root;

    @Mock
    Predicate predicate;



    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = UserService.getInstance(sessionFactory);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

    }

    @Test
    public void testCreateUser_Successful() {
        String userName = "john";
        String email = "john@example.com";
        String confirmEmail = "john@example.com";
        char[] password = "password".toCharArray();
        char[] confirmPassword = "password".toCharArray();
        String dob = "2000-01-01";

        assertTrue(userService.createUser(userName, email, confirmEmail, password, confirmPassword, dob));

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).save(any(User.class));
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testCreateUser_PasswordMismatch() {
        String userName = "john";
        String email = "john@example.com";
        String confirmEmail = "john@example.com";
        char[] password = "password1".toCharArray();
        char[] confirmPassword = "password2".toCharArray();
        String dob = "2000-01-01";

        assertFalse(userService.createUser(userName, email, confirmEmail, password, confirmPassword, dob));

        verify(sessionFactory, never()).openSession();
    }

    @Test
    public void testFindUserByUsername_UserExists() {
        String username = "john";
        User user = new User();
        user.setUserName(username);

        when(session.createQuery(anyString(), eq(User.class))).thenReturn(query);
        when(query.setParameter(anyString(), anyString())).thenReturn(query);
        when(query.uniqueResultOptional()).thenReturn(Optional.of(user));

        Optional<User> result = userService.findUserByUsername(username);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUserName());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery(anyString(), eq(User.class));
        verify(query, times(1)).setParameter(anyString(), anyString());
        verify(query, times(1)).uniqueResultOptional();
        verify(session, times(1)).close();
    }

    @Test
    public void testFindUserByUsername_UserDoesNotExist() {
        String username = "john";

        when(session.createQuery(anyString(), eq(User.class))).thenReturn(query);
        when(query.setParameter(anyString(), anyString())).thenReturn(query);
        when(query.uniqueResultOptional()).thenReturn(Optional.empty());

        Optional<User> result = userService.findUserByUsername(username);

        assertFalse(result.isPresent());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery(anyString(), eq(User.class));
        verify(query, times(1)).setParameter(anyString(), anyString());
        verify(query, times(1)).uniqueResultOptional();
        verify(session, times(1)).close();
    }

    @Test
    public void testFindUserByEmail_UserExists() {
        String email = "john@example.com";
        User user = new User();
        user.setEmail(email);


        when(session.createQuery(anyString(), eq(User.class))).thenReturn(query);
        when(query.setParameter(anyString(), anyString())).thenReturn(query);
        when(query.uniqueResultOptional()).thenReturn(Optional.of(user));

        Optional<User> result = userService.findUserByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery(anyString(), eq(User.class));
        verify(query, times(1)).setParameter(anyString(), anyString());
        verify(query, times(1)).uniqueResultOptional();
        verify(session, times(1)).close();
    }

    @Test
    public void testFindUserByEmail_UserDoesNotExist() {
        String email = "john@example.com";

        when(session.createQuery(anyString(), eq(User.class))).thenReturn(query);
        when(query.setParameter(anyString(), anyString())).thenReturn(query);
        when(query.uniqueResultOptional()).thenReturn(Optional.empty());

        Optional<User> result = userService.findUserByEmail(email);

        assertFalse(result.isPresent());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery(anyString(), eq(User.class));
        verify(query, times(1)).setParameter(anyString(), anyString());
        verify(query, times(1)).uniqueResultOptional();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateUsername_UserExists() {
        int userId = 1;
        String newUsername = "newJohn";

        User user = new User();
        user.setUserName("john");

        when(session.get(User.class, userId)).thenReturn(user);

        assertTrue(userService.updateUsername(userId, newUsername));

        assertEquals(newUsername, user.getUserName());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, userId);
        verify(session, times(1)).update(user);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateUsername_UserDoesNotExist() {
        int userId = 1;
        String newUsername = "newJohn";

        when(session.get(User.class, userId)).thenReturn(null);

        assertFalse(userService.updateUsername(userId, newUsername));

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, userId);
        verify(session, never()).update(any(User.class));
        verify(transaction, never()).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateEmail_UserExists() {
        int userId = 1;
        String newEmail = "newjohn@example.com";

        User user = new User();
        user.setEmail("john@example.com");

        when(session.get(User.class, userId)).thenReturn(user);

        assertTrue(userService.updateEmail(userId, newEmail));

        assertEquals(newEmail, user.getEmail());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, userId);
        verify(session, times(1)).update(user);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateEmail_UserDoesNotExist() {
        int userId = 1;
        String newEmail = "newjohn@example.com";

        when(session.get(User.class, userId)).thenReturn(null);

        assertFalse(userService.updateEmail(userId, newEmail));

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, userId);
        verify(session, never()).update(any(User.class));
        verify(transaction, never()).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdatePassword_UserExists() {
        int userId = 1;
        char[] newPassword = "newpassword".toCharArray();

        User user = new User();
        user.setPassword("oldpassword".toCharArray());

        when(session.get(User.class, userId)).thenReturn(user);

        assertTrue(userService.updatePassword(userId, newPassword));

        assertArrayEquals(newPassword, user.getPassword());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, userId);
        verify(session, times(1)).update(user);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdatePassword_UserDoesNotExist() {
        int userId = 1;
        char[] newPassword = "newpassword".toCharArray();

        when(session.get(User.class, userId)).thenReturn(null);

        assertFalse(userService.updatePassword(userId, newPassword));

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, userId);
        verify(session, never()).update(any(User.class));
        verify(transaction, never()).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateDoB_UserExists() {
        int userId = 1;
        LocalDate newDoB = LocalDate.of(1990, 1, 1);

        User user = new User();
        user.setDoB(LocalDate.of(2000, 1, 1));

        when(session.get(User.class, userId)).thenReturn(user);

        assertTrue(userService.updateDoB(userId, newDoB));

        assertEquals(newDoB, user.getDoB());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, userId);
        verify(session, times(1)).update(user);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateDoB_UserDoesNotExist() {
        int userId = 1;
        LocalDate newDoB = LocalDate.of(1990, 1, 1);

        when(session.get(User.class, userId)).thenReturn(null);

        assertFalse(userService.updateDoB(userId, newDoB));

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, userId);
        verify(session, never()).update(any(User.class));
        verify(transaction, never()).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateLocation_UserExists() {
        int userId = 1;
        Locale newLocation = Locale.US;

        User user = new User();
        user.setCurrentLocation(Locale.UK);

        when(session.get(User.class, userId)).thenReturn(user);

        assertTrue(userService.updateLocation(userId, newLocation));

        assertEquals(newLocation, user.getCurrentLocation());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, userId);
        verify(session, times(1)).update(user);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateLocation_UserDoesNotExist() {
        int userId = 1;
        Locale newLocation = Locale.US;

        when(session.get(User.class, userId)).thenReturn(null);

        assertFalse(userService.updateLocation(userId, newLocation));

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, userId);
        verify(session, never()).update(any(User.class));
        verify(transaction, never()).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testDeleteUser_UserExists() {
        int userId = 1;

        User user = new User();

        when(session.get(User.class, userId)).thenReturn(user);

        assertTrue(userService.delete(userId));

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, userId);
        verify(session, times(1)).delete(user);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testDeleteUser_UserDoesNotExist() {
        int userId = 1;

        when(session.get(User.class, userId)).thenReturn(null);

        assertFalse(userService.delete(userId));

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, userId);
        verify(session, never()).delete(any(User.class));
        verify(transaction, never()).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testGetAllUsers_NoUsers() {
        doReturn(query).when(session).createQuery(any(CriteriaQuery.class));
        when(query.getResultList()).thenReturn(Collections.emptyList());

        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(0, users.size());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery(any(CriteriaQuery.class));
        verify(session, times(1)).close();
    }

    @Test
    public void testGetAllUsers_WithUsers() {
        User user1 = new User();
        User user2 = new User();

        when(session.createQuery(any(CriteriaQuery.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(List.of(user1, user2));

        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(User.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery(any(CriteriaQuery.class));
        verify(session, times(1)).close();
    }


    @Test
    public void testUpdateFavoriteMeals_UserExists() {
        int userId = 1;
        List<Meal> newFavoriteMeals = new ArrayList<>();

        User user = new User();
        user.setId(userId);

        Transaction transaction = mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(User.class, userId)).thenReturn(user);

        boolean result = userService.updateFavoriteMeals(userId, newFavoriteMeals);

        assertTrue(result);
        assertEquals(newFavoriteMeals, user.getFavoriteMeals());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).get(User.class, userId);
        verify(session, times(1)).update(user);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateFavoriteMeals_UserDoesNotExist() {
        int userId = 1;
        List<Meal> newFavoriteMeals = new ArrayList<>();

        Transaction transaction = mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(User.class, userId)).thenReturn(null);

        boolean result = userService.updateFavoriteMeals(userId, newFavoriteMeals);

        assertFalse(result);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).get(User.class, userId);
        verify(transaction, never()).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateFavoriteCuisine_UserExists() {
        int userId = 1;
        List<Cuisine> newFavoriteCuisine = new ArrayList<>();

        User user = new User();
        user.setId(userId);

        Transaction transaction = mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(User.class, userId)).thenReturn(user);

        boolean result = userService.updateFavoriteCuisine(userId, newFavoriteCuisine);

        assertTrue(result);
        assertEquals(newFavoriteCuisine, user.getFavoriteCuisine());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).get(User.class, userId);
        verify(session, times(1)).update(user);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateFavoriteCuisine_UserDoesNotExist() {
        int userId = 1;
        List<Cuisine> newFavoriteCuisine = new ArrayList<>();

        Transaction transaction = mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(User.class, userId)).thenReturn(null);

        boolean result = userService.updateFavoriteCuisine(userId, newFavoriteCuisine);

        assertFalse(result);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).get(User.class, userId);
        verify(transaction, never()).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateMealsToDate_UserExists() {
        int userId = 1;
        List<Meal> newMealsToDate = new ArrayList<>();

        User user = new User();
        user.setId(userId);

        Transaction transaction = mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(User.class, userId)).thenReturn(user);

        boolean result = userService.updateMealsToDate(userId, newMealsToDate);

        assertTrue(result);
        assertEquals(newMealsToDate, user.getMealsToDate());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).get(User.class, userId);
        verify(session, times(1)).update(user);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateMealsToDate_UserDoesNotExist() {
        int userId = 1;
        List<Meal> newMealsToDate = new ArrayList<>();

        Transaction transaction = mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(User.class, userId)).thenReturn(null);

        boolean result = userService.updateMealsToDate(userId, newMealsToDate);

        assertFalse(result);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).get(User.class, userId);
        verify(transaction, never()).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateAllergies_UserExists() {
        int userId = 1;
        List<Allergy> newAllergies = new ArrayList<>();

        User user = new User();
        user.setId(userId);

        Transaction transaction = mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(User.class, userId)).thenReturn(user);

        boolean result = userService.updateAllergies(userId, newAllergies);

        assertTrue(result);
        assertEquals(newAllergies, user.getAllergies());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).get(User.class, userId);
        verify(session, times(1)).update(user);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdateAllergies_UserDoesNotExist() {
        int userId = 1;
        List<Allergy> newAllergies = new ArrayList<>();

        Transaction transaction = mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(User.class, userId)).thenReturn(null);

        boolean result = userService.updateAllergies(userId, newAllergies);

        assertFalse(result);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).get(User.class, userId);
        verify(transaction, never()).commit();
        verify(session, times(1)).close();
    }

    private <T> TypedQuery<T> mockTypedQuery(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        TypedQuery<T> typedQuery = mock(TypedQuery.class);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        return typedQuery;
    }
}
