package Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "mealstodate")
public class Meal extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "mealsToDate")
    private List<User> users;



    // ... getters and setters ...

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", id=" + getId() +
                ", createdAt=" + getCreatedAt() +
                ", modifiedAt=" + getModifiedAt() +
                '}';
    }
}
