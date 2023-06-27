package Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "allergies")
public class Allergy extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "allergies")
    private List<User> users;

    @ManyToMany(mappedBy = "allergies")
    private List<Diet> diets;

    @ManyToMany(mappedBy = "mealsAllergy")
    private List<Meals> meals;


    // ... getters and setters ...

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Diet> getDiets() {
        return diets;
    }

    public void setDiets(List<Diet> diets) {
        this.diets = diets;
    }

    public List<Meals> getMeals() {
        return meals;
    }

    public void setMeals(List<Meals> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Allergy{" +
                "name='" + name + '\'' +
                ", id=" + getId() +
                ", createdAt=" + getCreatedAt() +
                ", modifiedAt=" + getModifiedAt() +
                '}';
    }
}
