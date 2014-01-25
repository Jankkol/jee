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

@FacesValidator("songValidator")
public class SongValidator implements Validator {

	@Inject
	private SongService ss;
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		String title = (String) value;
		List<Song> songs = ss.getAllSongs();

		for(Song s : songs){
			if (title.equals(s.getTitle())) {
				FacesMessage message = new FacesMessage();
				message.setDetail("Piosenka istnieje w bazie.");
				message.setSummary("Piosenka o tytule " + title +" istnieje już w bazie! Podaj inny tytul.");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
		
	}
}
