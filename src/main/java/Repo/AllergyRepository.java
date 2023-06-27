package Repo;

import Data.Allergy;
import Data.Diet;
import Data.Meals;
import Data.User;

import java.util.List;

public interface AllergyRepository {
    Allergy findById(Integer id);
    List<Allergy> findAll();
    void save(Allergy allergy);
    void update(Allergy allergy);
    void delete(Allergy allergy);
    List<Allergy> findByUser(User user);
    List<Allergy> findByDiet(Diet diet);
    List<Allergy> findByMeal(Meals meals);
}