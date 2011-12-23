package x33eja.back;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import x33eja.model.Teacher;
import x33eja.sb.SchoolCenterLocal;

/**
 *
 * @author valekfra
 */
@ManagedBean(name="teachers")
@ApplicationScoped
public class TeachersBean {
    @EJB
    private SchoolCenterLocal schoolCenter;
    private List<SelectItem> allTeachers;

    public List<SelectItem> getAllTeachers() {
        allTeachers = new ArrayList<SelectItem>();
        allTeachers.add(new SelectItem(new Teacher(), "---"));
        for (Teacher t : schoolCenter.getAllTeachers()) {
            allTeachers.add(new SelectItem(t, t.getSurname() + " " + t.getFirstName()));
        }
        return allTeachers;
    }
}
