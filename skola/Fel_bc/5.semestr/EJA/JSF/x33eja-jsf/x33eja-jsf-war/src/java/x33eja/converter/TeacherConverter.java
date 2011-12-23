/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package x33eja.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import x33eja.sb.SchoolCenterLocal;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import x33eja.model.Teacher;

/**
 *
 * @author valekfra
 */
@FacesConverter(value = "teacherConv")
public class TeacherConverter implements Converter {

    SchoolCenterLocal schoolCenter = lookupSchoolCenterLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return schoolCenter.getTeacher(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if (value != null) {
            return String.valueOf(((Teacher) value).getBirthNumber());
//        } else {
//            return null;
//        }
    }

    private SchoolCenterLocal lookupSchoolCenterLocal() {
        try {
            Context c = new InitialContext();
            return (SchoolCenterLocal) c.lookup("java:global/x33eja-jsf/x33eja-sb-ejb/SchoolCenter!x33eja.sb.SchoolCenterLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
