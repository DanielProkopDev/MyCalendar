package Repo;

import Data.FavoriteMeals;
import Data.User;

import java.util.List;

public interface FavoriteMealsRepository {
    FavoriteMeals findById(Integer id);
    List<FavoriteMeals> findAll();
    void save(FavoriteMeals favoriteMeals);
    void update(FavoriteMeals favoriteMeals);
    void delete(FavoriteMeals favoriteMeals);
    List<FavoriteMeals> findByUser(User user);
    List<FavoriteMeals> findByName(String name);
}