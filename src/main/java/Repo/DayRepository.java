package Repo;

import Data.Day;

import java.util.List;

public interface DayRepository {
    Day findById(Integer id);
    List<Day> findAll();
    void save(Day day);
    void update(Day day);
    void delete(Day day);
    Day findByDate(Integer yearDate, Integer monthDate, Integer dayDate);
    Day findByYear(Integer yearDate);
    Day findByMonth(Integer monthDate);
    Day findByDay(Integer dayDate);
}