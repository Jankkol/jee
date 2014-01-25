package com.jankkol.jee.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hsqldb.persist.Log;

import com.jankkol.jee.domain.Album;
import com.jankkol.jee.domain.Artist;

@Stateless
public class ArtistService implements Serializable {

	@PersistenceContext
	EntityManager em;

	public void addArtist(Artist artist) {
		artist.setId(null);
		em.persist(artist);
	}

	public void deleteArtist(Artist artist) {
		artist = em.find(Artist.class, artist.getId());
		em.remove(artist);
	}
	
	public void updateArtist(Artist artist){
		em.merge(artist);
	}

	@SuppressWarnings("unchecked")
	public List<Artist> getAllArtists() {
		return em.createNamedQuery("artist.all").getResultList();
	}

	public List<Album> getAllAlbumsFromArtist(Artist artist){
		
		return artist.getAlbums();
	}

}
