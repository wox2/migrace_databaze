package users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ManagerSessionBean {
    
    @PersistenceContext
    private EntityManager em;

    public User findUser(Long id) {
        return em.find(User.class, id);
    }

    public void updateUser(Long id, String name, String email) {
        User p = em.find(User.class, id);
        p.setName(name);
        p.setEmail(email);
    }
}
