package Repo;

import Data.Day;
import Data.Meals;
import Data.User;

import javax.persistence.criteria.Predicate;
import java.util.List;

public interface DayRepository {
    Day findById(Integer id);
    List<Day> findAll();
    void save(Day day);
    void update(Day day);
    void delete(Day day);
    List<Day> findByDate(Integer yearDate, Integer monthDate, Integer dayDate);
    Day findByYear(Integer yearDate);
    Day findByMonth(Integer monthDate);
    Day findByDay(Integer dayDate);

    boolean updateMeals(int dayId, List<Meals> mealsList);

    boolean updateUsers(int dayId, List<User> userList);
}