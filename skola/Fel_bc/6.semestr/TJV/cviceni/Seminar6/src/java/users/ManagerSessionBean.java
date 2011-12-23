package users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

@Stateless
public class ManagerSessionBean {
    
    @PersistenceContext
    private EntityManager em;

    public User findUser(Long id) {
        return em.find(User.class, id);
    }

    public void updateUser(Long id, String name, String email, int version)
        throws UpdateException {
//        User p = em.find(User.class, id);
//        p.setName(name);
//        p.setEmail(email);
//        p.setVersion(version);
        try {
            User p = new User(id, name, email, version);
            em.merge(p);
        } catch (OptimisticLockException e) {
            throw new UpdateException("data has changed since last retrieval");
        }
    }
}
