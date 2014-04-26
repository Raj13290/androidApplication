package com.manish.android.playsound;

import java.io.Serializable;

public class Song implements Serializable {
	/**
	 * adding serival version
	 */
	private static final long serialVersionUID = 1L;
	private String songName;
	private String duration;
	private String albumName;
	private String path;

	public Song() {
	}

	public Song(String songName, String duration, String albumName, String path) {
		super();
		this.songName = songName;
		this.duration = duration;
		this.albumName = albumName;
		this.path = path;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
