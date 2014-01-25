package com.jankkol.jee.validator;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.jankkol.jee.domain.Song;
import com.jankkol.jee.service.SongService;

@FacesValidator("mandatoryValidator")
public class MandatoryValidator implements Validator {

	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		

			if (value == null || "".equals(value.toString().trim())) {
				FacesMessage message = new FacesMessage();
				message.setDetail("This field is required!");
				message.setSummary("");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}

	
	}
}
