/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package x33eja.sb;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import x33eja.model.Course;
import x33eja.model.Student;

/**
 *
 * @author blcha
 */
@Stateless
public class SchoolCenter implements SchoolCenterLocal {

    @PersistenceContext
    EntityManager em;

    public List<Course> getNotEnrolledCoursesForCurrent(Student student) {
        return (List<Course>) em.createQuery("SELECT a FROM Course a WHERE a.courseId NOT IN (SELECT c.courseId FROM Student p JOIN p.mEnrolledIn c WHERE p=:student)").setParameter("student", student).getResultList();

    }

    public void updateStudent(Student student) {
        em.merge(student);
    }

    public Student getStudent(int birthNumber) {
        return em.find(Student.class, birthNumber);
    }

    public Course getCourse(String courseId) {
        return em.find(Course.class, courseId);
    }

    public Map<Student, Long> getStudentsWithCourseCount() {
        final Map<Student, Long> map = new LinkedHashMap<Student, Long>();

        final List<Object[]> result = (List<Object[]>) em.createQuery("SELECT p, count(e) FROM Student p LEFT JOIN p.mEnrolledIn e GROUP BY p ORDER BY p.surname, p.firstName").getResultList();

        for (final Object[] o : result) {
            map.put((Student) o[0], (Long) o[1]);
        }

        return map;
    }

    public List<Course> getAllCourses() {
        return (List<Course>) em.createQuery("SELECT a FROM Course a").getResultList();
    }
    
}
