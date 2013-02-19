package es.regueiro.lyricsDownloader.lyrics;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class LyricText extends Lyric {

	private String lyrics;
	private URL sourceURL;
	
	public LyricText() {
		
	}
	public String getLyrics() {
		return lyrics;
	}
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	public URL getSourceURL() {
		return sourceURL;
	}
	public void setSourceURL(String sourceURL) throws MalformedURLException {
		this.sourceURL = new URL(sourceURL);
	}
	
	@Override
	public String toString() {
		return "LyricText [getArtist()=" + getArtist() + ", getTitle()="
				+ getTitle() + ", getLength()=" + getLength() + ", getAlbum()="
				+ getAlbum() + ", lyrics=" + lyrics + ", sourceURL="
				+ sourceURL.toString() + "]";
	}
	
	
}
