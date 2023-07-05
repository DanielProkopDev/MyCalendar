import Data.*;
import Service.MealsService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MealsRepositoryTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    Transaction transaction;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<Meals> criteriaQuery;

    @Mock
    Query<Meals> query;

    @Mock
    Predicate predicate;

    @Mock
    private Root<Meals> root;

    @Mock
    private Join<Meals, Allergy> allergyJoin;

    @Mock
    private Join<Meals, Diet> dietJoin;

    @Mock
    private Join<Meals, Day> dayJoin;

    @Mock
    private Join<Meals, Meal> mealJoin;

    @Test
    public void testFindById() {
        // Mocking
        Integer mealId = 1;
        Meals expectedMeal = new Meals();
        expectedMeal.setId(mealId);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Meals.class, mealId)).thenReturn(expectedMeal);

        // Test
        MealsService mealsService = MealsService.getInstance(sessionFactory);
        Meals actualMeal = mealsService.findById(mealId);

        // Verify
        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Meals.class, mealId);
        verify(session, times(1)).close();

        // Assertions
        assertEquals(expectedMeal, actualMeal);
    }

    @Test
    public void testFindAll() {
        // Mocking
        List<Meals> expectedMeals = new ArrayList<>();
        expectedMeals.add(new Meals());
        expectedMeals.add(new Meals());

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Meals.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Meals.class)).thenReturn(root);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedMeals);

        // Test
        MealsService mealsService = MealsService.getInstance(sessionFactory);
        List<Meals> actualMeals = mealsService.findAll();

        // Verify
        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).getCriteriaBuilder();
        verify(criteriaBuilder, times(1)).createQuery(Meals.class);
        verify(criteriaQuery, times(1)).from(Meals.class);
        verify(criteriaQuery, times(1)).select(root);
        verify(session, times(1)).createQuery(criteriaQuery);
        verify(query, times(1)).getResultList();
        verify(session, times(1)).close();

        // Assertions
        assertEquals(expectedMeals, actualMeals);
    }

    @Test
    public void testSave() {
        // Mocking
        Meals meals = new Meals();


        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction); // Return the mock Transaction object

        // Test
        MealsService mealsService = MealsService.getInstance(sessionFactory);
        mealsService.save(meals);

        // Verify
        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).save(meals);
        verify(transaction, times(1)).commit(); // Verify commit() method on the Transaction mock
        verify(session, times(1)).close();
    }

    @Test
    public void testUpdate() {
        // Mocking
        Meals meals = new Meals();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Test
        MealsService mealsService = MealsService.getInstance(sessionFactory);
        mealsService.update(meals);

        // Verify
        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).update(meals);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testDelete() {
        // Mocking
        Meals meals = new Meals();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        // Test
        MealsService mealsService = MealsService.getInstance(sessionFactory);
        mealsService.delete(meals);

        // Verify
        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).delete(meals);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    public void testFindByAllergy() {
        // Mocking
        Allergy allergy = new Allergy();
        allergy.setId(1);

        List<Meals> expectedMeals = new ArrayList<>();
        expectedMeals.add(new Meals());
        expectedMeals.add(new Meals());

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Meals.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Meals.class)).thenReturn(root);
        doReturn(allergyJoin).when(root).join("mealsAllergy");
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaBuilder.equal(allergyJoin.get("id"), allergy.getId())).thenReturn(predicate);
        when(criteriaQuery.where(predicate)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedMeals);

        // Test
        MealsService mealsService = MealsService.getInstance(sessionFactory);
        List<Meals> actualMeals = mealsService.findByAllergy(allergy);

        // Verify
        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).getCriteriaBuilder();
        verify(criteriaBuilder, times(1)).createQuery(Meals.class);
        verify(criteriaQuery, times(1)).from(Meals.class);
        verify(root, times(1)).join("mealsAllergy");
        verify(criteriaBuilder, times(1)).equal(allergyJoin.get("id"), allergy.getId());
        verify(criteriaQuery, times(1)).select(root);
        verify(criteriaQuery, times(1)).where(predicate);
        verify(session, times(1)).createQuery(criteriaQuery);
        verify(query, times(1)).getResultList();
        verify(session, times(1)).close();

        // Assertions
        assertEquals(expectedMeals, actualMeals);
    }

    @Test
    public void testFindByDiet() {
        // Mocking
        Diet diet = new Diet();
        diet.setId(1);

        List<Meals> expectedMeals = new ArrayList<>();
        expectedMeals.add(new Meals());
        expectedMeals.add(new Meals());

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Meals.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Meals.class)).thenReturn(root);
        doReturn(dietJoin).when(root).join("diets");
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaBuilder.equal(dietJoin.get("id"), diet.getId())).thenReturn(predicate);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedMeals);

        // Test
        MealsService mealsService = MealsService.getInstance(sessionFactory);
        List<Meals> actualMeals = mealsService.findByDiet(diet);

        // Verify
        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).getCriteriaBuilder();
        verify(criteriaBuilder, times(1)).createQuery(Meals.class);
        verify(criteriaQuery, times(1)).from(Meals.class);
        verify(root, times(1)).join("diets");
        verify(criteriaQuery, times(1)).select(root);
        verify(criteriaBuilder, times(1)).equal(dietJoin.get("id"), diet.getId());
        verify(session, times(1)).createQuery(criteriaQuery);
        verify(query, times(1)).getResultList();

        // Assertions
        assertEquals(expectedMeals, actualMeals);
    }

    @Test
    public void testFindByDay() {
        // Mocking
        Day day = new Day();
        List<Meals> expectedMeals = new ArrayList<>();
        expectedMeals.add(new Meals());
        expectedMeals.add(new Meals());

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Meals.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Meals.class)).thenReturn(root);
        doReturn(dayJoin).when(root).join("days");
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaBuilder.equal(dayJoin.get("id"), day.getId())).thenReturn(predicate);
        when(criteriaQuery.where(predicate)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedMeals);

        // Test
        MealsService mealsService = MealsService.getInstance(sessionFactory);
        List<Meals> actualMeals = mealsService.findByDay(day);

        // Verify
        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).getCriteriaBuilder();
        verify(criteriaBuilder, times(1)).createQuery(Meals.class);
        verify(criteriaQuery, times(1)).from(Meals.class);
        verify(root, times(1)).join("days");
        verify(criteriaQuery, times(1)).select(root);
        verify(criteriaBuilder, times(1)).equal(dayJoin.get("id"), day.getId());
        verify(criteriaQuery, times(1)).where(predicate);
        verify(session, times(1)).createQuery(criteriaQuery);
        verify(query, times(1)).getResultList();

        // Assertions
        assertEquals(expectedMeals, actualMeals);
    }

    @Test
    public void testFindByMealsToDate() {
        // Mocking
        Meal meal = new Meal();
        meal.setId(1);

        List<Meals> expectedMeals = new ArrayList<>();
        expectedMeals.add(new Meals());
        expectedMeals.add(new Meals());

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Meals.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Meals.class)).thenReturn(root);
        doReturn(mealJoin).when(root).join("mealsToDate");
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaBuilder.equal(mealJoin.get("id"), meal.getId())).thenReturn(predicate);
        when(criteriaQuery.where(predicate)).thenReturn(criteriaQuery);
        when(session.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedMeals);

        // Test
        MealsService mealsService = MealsService.getInstance(sessionFactory);
        List<Meals> actualMeals = mealsService.findByMealsToDate(meal);

        // Verify
        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).getCriteriaBuilder();
        verify(criteriaBuilder, times(1)).createQuery(Meals.class);
        verify(criteriaQuery, times(1)).from(Meals.class);
        verify(root, times(1)).join("mealsToDate");
        verify(criteriaBuilder, times(1)).equal(mealJoin.get("id"), meal.getId());
        verify(criteriaQuery, times(1)).select(root);
        verify(criteriaQuery, times(1)).where(predicate);
        verify(session, times(1)).createQuery(criteriaQuery);
        verify(query, times(1)).getResultList();
        verify(session, times(1)).close();

        // Assertions
        assertEquals(expectedMeals, actualMeals);
    }
}
