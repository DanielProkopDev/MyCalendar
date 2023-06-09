package Repo;

import Data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface UserRepository {

    public boolean createUser(String userName, String email, String confirmEmail, char[] password, char[] confirmPassword, String dob);

    public Optional<User> findUserByUsername(String username);
    public Optional<User> findUserByEmail(String email);

    public boolean updateUsername(int userId, String newUsername);

    public boolean updateEmail(int userId, String newEmail);

    public boolean updatePassword(int userId, char[] newPassword);

    public boolean updateDoB(int userId, LocalDate newDoB);

    public boolean updateLocation(int userId, Locale newLocation);

    public boolean updateFavoriteMeals(int userId, List<Meal> newFavoriteMeals);

    public boolean updateFavoriteCuisine(int userId, List<Cuisine> newFavoriteCuisine);

    public boolean updateMealsToDate(int userId, List<Meal> newMealsToDate);

    public boolean updateAllergies(int userId, List<Allergy> newAllergies);

    public boolean updateBudget(int userId, BigDecimal budget);

    public boolean updateDays(int userId,List<Day> dayList);

    public boolean delete(int userId);

    List<User> getAllUsers();


}
