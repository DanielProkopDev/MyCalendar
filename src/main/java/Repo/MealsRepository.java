package Repo;

import Data.*;

import java.util.List;

public interface MealsRepository {
    Meals findById(Integer id);
    List<Meals> findAll();
    void save(Meals meals);
    void update(Meals meals);
    void delete(Meals meals);
    List<Meals> findByAllergy(Allergy allergy);
    List<Meals> findByDiet(Diet diet);
    List<Meals> findByDay(Day day);
    List<Meals> findByMealsToDate(Meal meal);
}
