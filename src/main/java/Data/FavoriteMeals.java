package Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "favoritemeals")
public class FavoriteMeals extends AbstractEntity {

    // ... other properties ...

    @ManyToMany(mappedBy = "favoriteMeals")
    private List<User> users;

    // ... getters and setters ...

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}