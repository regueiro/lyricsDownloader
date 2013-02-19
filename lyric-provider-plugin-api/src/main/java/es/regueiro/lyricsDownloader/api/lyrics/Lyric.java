package es.regueiro.lyricsDownloader.api.lyrics;

public abstract class Lyric {

	private String artist;
	private String title;
	private String album;
	private int length;
		
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
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	@Override
	public String toString() {
		return "Lyric [artist=" + artist + ", title=" + title + ", length="
				+ length + ", album=" + album + "]";
	}

}
