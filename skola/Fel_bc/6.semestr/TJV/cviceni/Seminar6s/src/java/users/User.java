package users;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User2")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return 0;
    }

    @Override
    public boolean equals(Object p) {
        if (!(p instanceof User)) {
            return false;
        }
        User u = (User) p;
        if ((this.id == null && u.id != null) || (this.id != null && !this.id.equals(u.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, name=%s, email=%s]", id, name, email);
    }
}
