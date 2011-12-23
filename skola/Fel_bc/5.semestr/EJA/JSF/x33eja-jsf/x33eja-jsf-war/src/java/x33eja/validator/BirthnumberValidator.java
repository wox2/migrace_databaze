package x33eja.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author valekfra
 */
@FacesValidator(value="birthnumberValidator")
public class BirthnumberValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        //Validator rodneho cisla - napr. slozitejsi kontrola delitelnosti s vyjimkami apod.
    }

}
