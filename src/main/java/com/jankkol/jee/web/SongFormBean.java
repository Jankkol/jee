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
import com.jankkol.jee.service.SongService;

@SessionScoped
@ManagedBean(name = "songBean")
public class SongFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Song song = new Song();
	private ListDataModel<Song> songs = new ListDataModel<Song>();

	private Song songToShow = new Song();

	@Inject
	private SongService ss;

	private long artistId;
	private long albumId;

	
	
	public long getArtistId() {
		return artistId;
	}

	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Song getSongToShow() {
		return songToShow;
	}

	public void setSongToShow(Song songToShow) {
		this.songToShow = songToShow;
	}

	public ListDataModel<Song> getAllSongs() {
		songs.setWrappedData(ss.getAllSongs());
		return songs;
	}

	// Actions
	public String addSong() {
		ss.addSong(song, artistId, albumId);
		return "showSong";
		// return null;
	}

	public String deleteSong() {
		Song songToDelete = songs.getRowData();
		ss.deleteSong(songToDelete);
		return null;
	}
	
	public String updateSong(){
		ss.updateSong(song, artistId, albumId);
		return "showSong";
	}
	
	public String editSong(){
		setSong(songs.getRowData());
		return "updateSong";
	}

	public String showDetails() {
		setSongToShow(songs.getRowData());
		return "details";
	}

	public List<Artist> getAllArtists() {
		return ss.getAllArtists();
	}

	public List<Album> getAllAlbums() {
		return ss.getAllAlbums();
	}
}
