package timetable.converter;

import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author woxie
 */
@FacesConverter(value = "timeConv")
public class TimeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String[] values = value.split(":");
        if (values.length != 2) throw new ConverterException("Validation error: expected format HH:MM, example: 12:53.");
        try {
            int hrs = Integer.parseInt(values[0]);
            int min = Integer.parseInt(values[1]);
            if (hrs > 23 || hrs < 0 || min > 59 || min < 0) throw new ConverterException("Validation error: expected format HH:MM, example: 12:53.");
            //hrs -= Calendar.getInstance().getTimeZone().getRawOffset();
            //return new GregorianCalendar(0,0,0,hrs,min).getTime();
            Date res = new Date(0,0,0,hrs,min);
            return res;
        } catch (NumberFormatException ex) {
            throw new ConverterException(ex);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Date)) throw new ConverterException("Validation error: expected format HH:MM, example: 12:53.");
        int hrs = ((Date) value).getHours();
        int min = ((Date) value).getMinutes();
        return hrs + ":" + ((min < 10) ? ("0" + min) : min);
    }


}
