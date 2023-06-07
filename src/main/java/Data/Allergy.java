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

    // ... getters and setters ...

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
