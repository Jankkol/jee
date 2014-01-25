package com.jankkol.jee.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

@Entity
@NamedQueries({ @NamedQuery(name = "album.all", query = "Select a from Album a") ,
				@NamedQuery(name = "album.byName", query = "Select a from Album a where a.name=:name")
				})
public class Album {

	private Long id;
	private String name;
	private String coverImage;
	private Date yop = new Date();
	private Artist artist;
	private List<Song> songs = new ArrayList<Song>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	@Temporal(TemporalType.DATE)
	@Past(message = "Year of production must be in past!")
	public Date getYop() {
		return yop;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public void setYop(Date yop) {
		this.yop = yop;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

}
