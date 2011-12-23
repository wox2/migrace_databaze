package x33eja.sb;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import x33eja.model.Course;
import x33eja.model.Person;
import x33eja.model.Student;

@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class StudentSessionBean implements StudentSessionLocal {

    private Student current;

    @PersistenceContext
    private EntityManager em;
    
    @Resource
    private UserTransaction utx;

    public void init(String birthNumber) {
        System.out.println("INIT: " + this.hashCode());
        try {
            if (utx.getStatus() == Status.STATUS_NO_TRANSACTION) {
                utx.begin();
            }

            System.out.println("birthNumber=" + birthNumber);

            if (birthNumber == null) {
                current = new Student();
            } else {
                try {
                    int i = Integer.parseInt(birthNumber);
                    current = em.find(Student.class, i);
                } catch (NumberFormatException e) {
                    current = new Student();
                }
            }
            System.out.println("Current set to " + current);
        } catch (NotSupportedException ex) {
            Logger.getLogger(StudentSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(StudentSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Person getCurrent() {
        return current;
    }

    public List<Course> getNotEnrolledCoursesForCurrent() {
        if (!em.contains(current)) {
            return (List<Course>) em.createQuery("SELECT a FROM Course a").getResultList();
        } else {
            return (List<Course>) em.createQuery("SELECT a FROM Course a WHERE a.courseId NOT IN (SELECT c.courseId FROM Student p JOIN p.mEnrolledIn c WHERE p=:student)").setParameter("student", current).getResultList();
        }
    }

    public Map<Student, Long> getStudentsWithCourseCount() {
        final Map<Student, Long> map = new LinkedHashMap<Student, Long>();

        final List<Object[]> result = (List<Object[]>) em.createQuery("SELECT p, count(e) FROM Student p LEFT JOIN p.mEnrolledIn e GROUP BY p ORDER BY p.surname, p.firstName").getResultList();

        for (final Object[] o : result) {
            map.put((Student) o[0], (Long) o[1]);
        }

        return map;
    }

    public void save(String birthNumber, String firstName, String surname) {
        try {
            System.out.println("=== RQ START ===");
            System.out.println("Entity manager created.");

            System.out.println("Saving " + birthNumber + "/" + firstName + "/" + surname);

            int bN = Integer.parseInt(birthNumber);

            if (!em.contains(current)) {
                if ( current.getBirthNumber() != bN ) {
                    current.setBirthNumber(Integer.parseInt(birthNumber));
                    em.persist(current);
                } else {
                    current = em.merge(current);
                    em.persist(current);
                }
            } else if (bN != current.getBirthNumber()) {
                utx.rollback();
                // nebo napr. throw new IllegalArgumentException("Invalid argument: current birthNumber=" + current.getBirthNumber() + ", but got bN="+bN);
            }

            current.setFirstName(firstName);
            current.setSurname(surname);

            utx.commit();
            // po save zustane aplikace na teze strance
            utx.begin();
        } catch (NotSupportedException ex) {
            Logger.getLogger(StudentSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(StudentSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(StudentSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(StudentSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(StudentSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(StudentSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(StudentSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Remove
    public void discard() {
        try {
            utx.rollback();
        } catch (IllegalStateException ex) {
            Logger.getLogger(StudentSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(StudentSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(StudentSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addCourse(String courseId) {
        List<Course> courses = current.getEnrolledIn();

        if ( courses == null) {
            courses = new ArrayList<Course>();
        }

        courses.add(em.find(Course.class, courseId));

        current.setEnrolledIn(courses);
    }
}

