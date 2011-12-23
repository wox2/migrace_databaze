package timetable.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author woxie
 */
@FacesValidator(value="usernameValidator")
public class UsernameValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!(value instanceof String) || ((String) value).length() < 5) throw new ValidatorException(new FacesMessage("Validation error: this string is not valid. (String must contain alphanumerical characters only and be at least 5 characters long. The string must not begin with a digit.)"));
        for (int i = 0; i < ((String) value).length(); i++) {
            Character ch = ((String) value).charAt(i);
            if (!(Character.isLetter(ch) || (i > 0 && Character.isDigit(ch)))) throw new ValidatorException(new FacesMessage("Validation error: this string is not valid. (String must contain alphanumerical characters only and be at least 5 characters long. The string must not begin with a digit.)"));
        }
    }

}