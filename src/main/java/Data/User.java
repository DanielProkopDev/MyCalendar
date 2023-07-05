package Data;

import Utils.Allergies;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(name = "current_location")
    private Locale currentLocation;


    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private char[]  password;

    @Column(name = "salt")
    private String salt;

    @Column(name = "dob")
    private LocalDate doB;

    @Column(name="budget")
    private BigDecimal budget;

    @ManyToMany
    @JoinTable(
            name = "user_favoritemeals",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "favoritemeals_id")
    )
    private List<Meal> favoriteMeals;

    @ManyToMany
    @JoinTable(
            name = "user_diet",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id")
    )
    private List<Diet> diets;

    @ManyToMany
    @JoinTable(
            name = "user_favoritecuisine",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "favoritecuisine_id")
    )
    private List<Cuisine> favoriteCuisine;



    @ManyToMany
    @JoinTable(
            name = "user_allergy",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    private List<Allergy> allergies;

    @ManyToMany
    @JoinTable(
            name = "user_mealstodate",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "mealstodate_id")
    )
    private List<Meal> mealsToDate;

    @ManyToMany(mappedBy = "users")
    private List<Day> daysList;


    public User() {
        this.currentLocation = Locale.getDefault();
    }

    // ... getters and setters ...


    public Locale getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Locale currentLocation) {
        this.currentLocation = currentLocation;
    }

    public List<Meal> getFavoriteMeals() {
        return favoriteMeals;
    }

    public void setFavoriteMeals(List<Meal> favoriteMeals) {
        this.favoriteMeals =  favoriteMeals;
    }

    public List<Cuisine> getFavoriteCuisine() {
        return favoriteCuisine;
    }

    public void setFavoriteCuisine(List<Cuisine> favoriteCuisine) {
        this.favoriteCuisine = favoriteCuisine;
    }


    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }

    public List<Meal> getMealsToDate() {
        return mealsToDate;
    }

    public void setMealsToDate(List<Meal> mealsToDate) {
        this.mealsToDate = mealsToDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public LocalDate getDoB() {
        return doB;
    }

    public void setDoB(LocalDate doB) {
        this.doB = doB;
    }

    public List<Diet> getDiets() {
        return diets;
    }

    public void setDiets(List<Diet> diets) {
        this.diets = diets;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<Day> getDaysList() {
        return daysList;
    }

    public void setDaysList(List<Day> daysList) {
        this.daysList = daysList;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "User{" +
                "currentLocation=" + currentLocation +
                ", favoriteMeals=" + favoriteMeals +
                ", favoriteCuisine=" + favoriteCuisine +
                ", allergies='" + allergies + '\'' +
                ", mealsToDate=" + mealsToDate +
                ", username=" + getUserName() +
                ", email=" + getEmail() +
                ", password=" + getPassword() +
                ", created at=" + getCreatedAt() +
                ", date of birth=" + getDoB() +
                '}';
    }
}
