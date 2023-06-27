package Repo;

import Data.Allergy;
import Data.Diet;
import Data.User;

import java.util.List;

public interface DietRepository {
    Diet findById(Integer id);
    List<Diet> findAll();
    void save(Diet diet);
    void update(Diet diet);
    void delete(Diet diet);
    List<Diet> findByUser(User user);
    List<Diet> findByAllergy(Allergy allergy);
    List<Diet> findByBudget(Double budget);
    List<Diet> findByCalories(Integer calories);
    List<Diet> findByName(String name);
}