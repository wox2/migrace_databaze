package timetable.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author valekfra
 */
@FacesValidator(value="nameValidator")
public class NameValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!(value instanceof String)) throw new ValidatorException(new FacesMessage("Validation error: this string is not name (string must contain aplhabetical characters only."));
        for (int i = 0; i < ((String) value).length(); i++) {
            Character ch = ((String) value).charAt(i);
            if (!Character.isLetter(ch)) throw new ValidatorException(new FacesMessage("Validation error: this string is not name (string must contain aplhabetical characters only."));
        }
    }

}