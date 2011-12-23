package x33eja.back;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import x33eja.model.Course;
import x33eja.sb.SchoolCenterLocal;

/**
 *
 * @author valekf1
 */
@ManagedBean(name = "courses")
@ApplicationScoped
public class CoursesBean {

    @EJB
    private SchoolCenterLocal schoolCenter;
    private List<SelectItem> allCourses;

    public List<SelectItem> getAllCourses() {
        allCourses = new ArrayList<SelectItem>();
        for (Course c : schoolCenter.getAllCourses()) {
            allCourses.add(new SelectItem(c, c.getName()));
        }
        return allCourses;
    }
}
