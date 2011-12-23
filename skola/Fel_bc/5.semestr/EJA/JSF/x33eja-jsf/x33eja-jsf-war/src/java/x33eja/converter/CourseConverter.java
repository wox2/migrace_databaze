package x33eja.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import x33eja.model.Course;
import x33eja.sb.SchoolCenterLocal;

/**
 *
 * @author valekfra
 */
@FacesConverter(value = "courseConv")
public class CourseConverter implements Converter {
    SchoolCenterLocal schoolCenter = lookupSchoolCenterLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return schoolCenter.getCourse(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Course) value).getCourseId());
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
