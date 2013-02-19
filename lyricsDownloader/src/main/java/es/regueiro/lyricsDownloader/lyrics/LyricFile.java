package es.regueiro.lyricsDownloader.lyrics;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class LyricFile extends Lyric {

	private String fileName;
	private URL sourceURL;
	private String uploader;
	private long rating;
	private int votes;
	private int downloads;
	
	public LyricFile() {
		
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public URL getSourceURL() {
		return sourceURL;
	}
	public void setSourceURL(String sourceURL) throws MalformedURLException {
		this.sourceURL = new URL(sourceURL);
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public long getRating() {
		return rating;
	}
	public void setRating(long rating) {
		this.rating = rating;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public int getDownloads() {
		return downloads;
	}
	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}
	
	@Override
	public String toString() {
		return "LyricFile [getArtist()=" + getArtist() + ", getTitle()="
				+ getTitle() + ", getLength()=" + getLength() + ", getAlbum()="
				+ getAlbum() + ", uploader=" + uploader + ", fileName="
				+ fileName + ", rating=" + rating + ", votes=" + votes
				+ ", downloads=" + downloads + ", sourceURL=" + sourceURL.toString() + "]";
	}

}
