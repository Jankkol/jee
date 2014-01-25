package com.jankkol.jee.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import com.jankkol.jee.domain.Album;
import com.jankkol.jee.domain.Artist;
import com.jankkol.jee.service.ArtistService;

@SessionScoped
@ManagedBean(name = "artistBean")
public class ArtistFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Artist artist = new Artist();
	private ListDataModel<Artist> artists = new ListDataModel<Artist>();
	private ListDataModel<Album> albums = new ListDataModel<Album>();

	private Artist artistToShow = new Artist();

	@Inject
	private ArtistService as;

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public ListDataModel<Artist> getAllArtist() {
		artists.setWrappedData(as.getAllArtists());
		return artists;
	}

	public ListDataModel<Album> getAlbums() {
		albums.setWrappedData(as.getAllAlbumsFromArtist(artistToShow));
		return albums;
	}

	public void setAlbums(ListDataModel<Album> albums) {
		this.albums = albums;
	}

	// Actions
	public String addArtist() {
		as.addArtist(artist);
		return "showArtist";
		// return null;
	}

	public String deleteArtist() {
		Artist artistToDelete = artists.getRowData();
		as.deleteArtist(artistToDelete);
		return null;
	}

	public String updateArtist() {
		as.updateArtist(artist);
		return "showArtist";
	}

	public String showDetails() {
		setArtistToShow(artists.getRowData());
		return "detailsArtist";
	}

	public Artist getArtistToShow() {
		return artistToShow;
	}

	public void setArtistToShow(Artist artistToShow) {
		this.artistToShow = artistToShow;
	}

	public String editArtist() {

		setArtist(artists.getRowData());

		return "updateArtist";
	}

}
