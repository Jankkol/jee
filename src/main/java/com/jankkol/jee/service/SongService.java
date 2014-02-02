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
public class SongService implements Serializable {

    @PersistenceContext
    EntityManager em;

    public void addSong(Song song, Long artistId, Long albumId) {
        song.setId(null);
        song.setArtist(em.find(Artist.class, artistId));
        song.setAlbum(em.find(Album.class, albumId));
        em.persist(song);

        Album album = em.find(Album.class, albumId);
        album.getSongs().add(song);
        em.merge(album);

    }

    public void deleteSong(Song song) {

        song = em.find(Song.class, song.getId());
        Album album = em.find(Album.class, song.getAlbum().getId());
        album.getSongs().remove(song);

        em.merge(album);
        em.remove(song);
    }

    public void updateSong(Song song, Long artistId, Long albumId) {

        Album album = em.find(Album.class, albumId);
        album.getSongs().remove(em.find(Song.class, song.getId()));
        em.merge(album);

        song.setArtist(em.find(Artist.class, artistId));
        song.setAlbum(em.find(Album.class, albumId));
        em.merge(song);

        Album album2 = em.find(Album.class, albumId);
        album2.getSongs().add(song);
        em.merge(album2);


    }

    @SuppressWarnings("unchecked")
    public List<Song> getAllSongs() {
        return em.createNamedQuery("song.all").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Album> getAllAlbums() {
        return em.createNamedQuery("album.all").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Artist> getAllArtists() {
        return em.createNamedQuery("artist.all").getResultList();
    }

}
