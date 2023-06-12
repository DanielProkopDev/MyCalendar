package Data;

import Utils.Allergies;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(name = "current_location")
    private Locale currentLocation;

    @Column(name = "current_diet")
    private boolean currentDiet;


    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private char[] password;

    @Column(name = "dob")
    private LocalDate doB;

    @ManyToMany
    @JoinTable(
            name = "user_favoritemeals",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "favoritemeals_id")
    )
    private List<Meal> favoriteMeals;

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
        this.favoriteMeals = favoriteMeals;
    }

    public List<Cuisine> getFavoriteCuisine() {
        return favoriteCuisine;
    }

    public void setFavoriteCuisine(List<Cuisine> favoriteCuisine) {
        this.favoriteCuisine = favoriteCuisine;
    }

    public boolean isCurrentDiet() {
        return currentDiet;
    }

    public void setCurrentDiet(boolean currentDiet) {
        this.currentDiet = currentDiet;
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

    @Override
    public String toString() {
        return "User{" +
                "currentLocation=" + currentLocation +
                ", favoriteMeals=" + favoriteMeals +
                ", favoriteCuisine=" + favoriteCuisine +
                ", currentDiet=" + currentDiet +
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
