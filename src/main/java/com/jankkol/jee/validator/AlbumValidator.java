package com.jankkol.jee.validator;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.jankkol.jee.domain.Album;
import com.jankkol.jee.service.AlbumService;

@FacesValidator("albumValidator")
public class AlbumValidator implements Validator {

	@Inject
	private AlbumService as;

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String name = (String) value;
		List<Album> albums = as.getAllAlbums();

		for (Album a : albums) {
			if (name.equals(a.getName())) {
				FacesMessage message = new FacesMessage();
				message.setDetail("Album istnieje w bazie.");
				message.setSummary("Album o nazwie " + name
						+ " istnieje już w bazie! Podaj inna nazwe.");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}

	}
}
