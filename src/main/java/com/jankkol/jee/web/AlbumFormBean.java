package com.jankkol.jee.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import com.jankkol.jee.domain.Album;
import com.jankkol.jee.domain.Artist;
import com.jankkol.jee.domain.Song;
import com.jankkol.jee.service.AlbumService;
import com.jankkol.jee.service.ArtistService;

@SessionScoped
@ManagedBean(name = "albumBean")
public class AlbumFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Album album = new Album();
	private ListDataModel<Album> albums = new ListDataModel<Album>();
	private ListDataModel<Song> songs = new ListDataModel<Song>();
	
	private Album albumToShow = new Album();

	@Inject
	private AlbumService as;

	@Inject
	private ArtistService artistService;

	private Long artistId;

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public ListDataModel<Album> getAllAlbums() {
		albums.setWrappedData(as.getAllAlbums());
		return albums;
	}

	// Actions
	public String addAlbum() {

		as.addAlbum(album, artistId);
		return "showAlbum";
		// return null;
	}

	public String deleteAlbum() {
		Album albumToDelete = albums.getRowData();
		as.deleteAlbum(albumToDelete);
		return null;
	}
	
	public String showDetails() {
		setAlbumToShow(albums.getRowData());
		return "detailsAlbum";
	}

	public Album getSetAlbumToShow() {
		return albumToShow;
	}

	public void setAlbumToShow(Album albumToShow) {
		this.albumToShow = albumToShow;
	}

	public List<Artist> getAllArtists() {
		return artistService.getAllArtists();
	}

	public Long getArtistId() {
		return artistId;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

	public ListDataModel<Song> getSongs() {
		songs.setWrappedData(as.getSongsFromAlbum(albumToShow));
		return songs;
	}

	public void setSongs(ListDataModel<Song> songs) {
		this.songs = songs;
	}

	public String editAlbum(){
		setAlbum(albums.getRowData());
		return "updateAlbum";
	}
	
	public String updateAlbum(){
		
		as.editAlbum(album, artistId);
		return "showAlbum";
	}

	
	
}
