package Repo;

import Data.Cuisine;
import Data.Meal;
import Data.User;
import Data.Allergy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface UserRepository {

    public boolean createUser(String userName, String email, String confirmEmail, String password, String confirmPassword, String dob);

    public Optional<User> findUserByUsername(String username);
    public Optional<User> findUserByEmail(String email);

    public boolean updateUsername(int userId, String newUsername);

    public boolean updateEmail(int userId, String newEmail);

    public boolean updatePassword(int userId, String newPassword);

    public boolean updateDoB(int userId, LocalDate newDoB);

    public boolean updateLocation(int userId, Locale newLocation);

    public boolean updateFavoriteMeals(int userId, List<Meal> newFavoriteMeals);

    public boolean updateFavoriteCuisine(int userId, List<Cuisine> newFavoriteCuisine);

    public boolean updateMealsToDate(int userId, List<Meal> newMealsToDate);

    public boolean updateAllergies(int userId, List<Allergy> newAllergies);


}
