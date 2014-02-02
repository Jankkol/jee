package com.jankkol.jee.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import com.jankkol.jee.domain.Song;

@Stateless
public class AlbumService implements Serializable {

	@PersistenceContext
	EntityManager em;

	public void addAlbum(Album album, Long artistId) {

		album.setId(null);
		Artist artist = em.find(Artist.class, artistId);
		album.setArtist(artist);
		em.persist(album);
		artist.getAlbums().add(album);
		em.persist(artist);

	}

    public void deleteAlbum(Album album){
        album = em.find(Album.class, album.getId());
        Artist artist = em.find(Artist.class, album.getArtist().getId());
        artist.getAlbums().remove(album);
        em.merge(artist);
        em.remove(album);
    }

	@SuppressWarnings("unchecked")
	public List<Album> getAllAlbums() {
		return em.createNamedQuery("album.all").getResultList();
	}

	public List<Song> getSongsFromAlbum(Album album) {

		Album a = em.find(Album.class, album.getId());
		List<Song> songs = a.getSongs();
		return songs;
	}

	public void editAlbum(Album album, Long artistId) {
		
		Album oldAlbum = em.find(Album.class, album.getId());
		Artist artist = em.find(Artist.class, oldAlbum.getArtist().getId());
		
		artist.getAlbums().remove(oldAlbum);
		Artist artist2 = em.find(Artist.class, artistId);
		album.setArtist(artist2);
		artist2.getAlbums().add(album);
		
		em.merge(artist);
		em.merge(artist2);
		em.merge(album);
	}


}
