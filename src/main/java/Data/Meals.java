package Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="meals")
public class Meals extends AbstractEntity{
    @Column(name="price")
    private Integer price;

    @Column(name="calories")
    private Integer calories;
    @Column(name="name")
    private String name;
    @Column(name="cookMethod")
    private String cookMethod;

    @ManyToMany
    @JoinTable(
            name = "meals_allergy",
            joinColumns = @JoinColumn(name = "meals_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    private List<Allergy> mealsAllergy;

    @ManyToMany
    @JoinTable(
            name = "meals_diet",
            joinColumns = @JoinColumn(name = "meals_id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id")
    )
    private List<Diet> diets;

    @ManyToMany
    @JoinTable(
            name = "mealstodate_meals",
            joinColumns = @JoinColumn(name = "meals_id"),
            inverseJoinColumns = @JoinColumn(name = "mealstodate_id")
    )
    private List<Meal> mealsToDate;


    @ManyToMany(mappedBy = "meals")
    private List<Day> days;




//getters and setters


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return cookMethod;
    }

    public void setMethod(String method) {
        this.cookMethod = method;
    }

    public List<Allergy> getAllergies() {
        return mealsAllergy;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.mealsAllergy = allergies;
    }

    public List<Diet> getDiets() {
        return diets;
    }

    public void setDiets(List<Diet> diets) {
        this.diets = diets;
    }

    public List<Allergy> getMealsAllergy() {
        return mealsAllergy;
    }

    public void setMealsAllergy(List<Allergy> mealsAllergy) {
        this.mealsAllergy = mealsAllergy;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public String getCookMethod() {
        return cookMethod;
    }

    public void setCookMethod(String cookMethod) {
        this.cookMethod = cookMethod;
    }

    public List<Meal> getMealsToDate() {
        return mealsToDate;
    }

    public void setMealsToDate(List<Meal> mealsToDate) {
        this.mealsToDate = mealsToDate;
    }

    @Override
    public String toString() {
        return "Meals{" +
                "price=" + price +
                ", calories=" + calories +
                ", name='" + name + '\'' +
                ", method='" + cookMethod + '\'' +
                ", allergies=" + mealsAllergy +
                ", diets=" + diets +
                '}';
    }
}
