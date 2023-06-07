package Data;

import java.time.LocalDate;

public class Day {

    private LocalDate yearDate;

   private  LocalDate monthDate;

   private LocalDate dayDate;

    private String dietText;

    private String mealOfTheDay;

    private String generatedMeal;

    public LocalDate getYearDate() {
        return yearDate;
    }

    public void setYearDate(LocalDate yearDate) {
        this.yearDate = yearDate;
    }

    public LocalDate getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(LocalDate monthDate) {
        this.monthDate = monthDate;
    }

    public LocalDate getDayDate() {
        return dayDate;
    }

    public void setDayDate(LocalDate dayDate) {
        this.dayDate = dayDate;
    }

    public String getDietText() {
        return dietText;
    }

    public void setDietText(String dietText) {
        this.dietText = dietText;
    }

    public String getMealOfTheDay() {
        return mealOfTheDay;
    }

    public void setMealOfTheDay(String mealOfTheDay) {
        this.mealOfTheDay = mealOfTheDay;
    }

    public String getGeneratedMeal() {
        return generatedMeal;
    }

    public void setGeneratedMeal(String generatedMeal) {
        this.generatedMeal = generatedMeal;
    }
}
