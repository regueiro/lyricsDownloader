package es.regueiro.lyricsDownloader.system;

import java.io.File;

public class AudioFile {

	private File fileName;
	private String artist;
	private String title;
	private String lyrics;
	
	
	public File getFileName() {
		return fileName;
	}
	public void setFileName(File fileName) {
		this.fileName = fileName;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLyrics() {
		return lyrics;
	}
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	
	
}
