package Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "favoritemeals")
public class FavoriteMeals extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "favoriteMeals")
    private List<User> users;

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

    @Override
    public String toString() {
        return "FavoriteMeals{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}