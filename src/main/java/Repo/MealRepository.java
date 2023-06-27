package Repo;

import Data.Meal;
import Data.User;

import java.util.List;

public interface MealRepository {
    Meal findById(Integer id);
    List<Meal> findAll();
    void save(Meal meal);
    void update(Meal meal);
    void delete(Meal meal);
    List<Meal> findByUser(User user);
    List<Meal> findByName(String name);
}