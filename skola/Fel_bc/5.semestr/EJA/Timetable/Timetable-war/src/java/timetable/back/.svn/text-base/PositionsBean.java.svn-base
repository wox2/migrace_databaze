/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.back;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import timetable.model.Position;
import timetable.sb.TimetableCenterLocal;

/**
 *
 * @author janf
 */
@ManagedBean(name="positions")
@SessionScoped
public class PositionsBean {

    @EJB
    private TimetableCenterLocal tc;

    private Position position = null;

    /** Creates a new instance of PositionBean */
    public PositionsBean() {
    }

    public String newPosition() {
        position = new Position();
        return "position";
    }

    public List<Position> getAllPositions() {
        return tc.getAllPositions();
    }


    public String savePosition() {
        tc.updatePosition(position);
        return "positions";
    }

    public String editPosition(Position position) {
        this.position = position;
        return "position";
    }

    public void removePosition(Position position) {
        tc.removePosition(position);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    
}
