/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.back;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import timetable.model.InactivePassanger;
import timetable.model.Passanger;
import timetable.model.Person;
import timetable.sb.TimetableCenterLocal;

/**
 *
 * @author janf
 */
@ManagedBean(name="passangers")
@SessionScoped
public class PassangerBean {

    @EJB
    private TimetableCenterLocal tc;

    private Person passanger = null;

    /** Creates a new instance of PassangerBean */
    public PassangerBean() {
    }

    public String newPassanger() {
        passanger = new InactivePassanger();
        return "register";
    }

    public List<Passanger> getAllPassangers() {
        return tc.getAllPassangers();
    }

    public List<InactivePassanger> getAllInactives() {
        return tc.getAllInactives();
    }

    public String savePassanger() {
        if (passanger instanceof Passanger)
            tc.updatePassanger((Passanger) passanger);
        if (passanger instanceof InactivePassanger) {
            try {
                tc.updateInactive((InactivePassanger) passanger);
            } catch (Exception ex) {
                return "failed";
            }
        }
        return "passangers";
    }

    public String activatePassanger(InactivePassanger passanger) {
        tc.activatePassanger(passanger);
        return "passangers";
    }

    public String editPassanger(Passanger passanger) {
        this.passanger = passanger;
        return "passanger";
    }

    public void removePassanger(Passanger passanger) {
        tc.removePassanger(passanger);
    }

    public void removePassanger(InactivePassanger passanger) {
        tc.removeInactive(passanger);
    }

    public Person getPassanger() {
        return passanger;
    }

    
}
