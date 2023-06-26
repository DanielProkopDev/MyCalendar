package Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="diet")
public class Diet extends AbstractEntity{

    @Column(name="name")
    private String name;

    @Column(name="calories")
    private Integer calories;

    @Column(name="budget")
    private Double budget;

    @ManyToMany
    @JoinTable(
            name = "diet_allergy",
            joinColumns = @JoinColumn(name = "diet_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    private List<Allergy> allergies;

    @ManyToMany(mappedBy ="diets")
    private List<User> users;

    //getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    @Override
    public String toString() {
        return "Diet{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", budget=" + budget +
                ", allergies=" + allergies +
                ", users=" + users +
                '}';
    }
}
