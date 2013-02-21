package es.regueiro.lyricsDownloader.api.lyrics;

// TODO: Auto-generated Javadoc
/**
 * The Class Lyric.
 */
public class Lyric extends Song {

	/** The lyrics. */
	private String lyrics;

	/**
	 * Instantiates a new lyric.
	 */
	public Lyric() {

	}

	/**
	 * Gets the lyrics.
	 *
	 * @return the lyrics
	 */
	public String getLyrics() {
		return lyrics;
	}

	/**
	 * Sets the lyrics.
	 *
	 * @param lyrics the new lyrics
	 */
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	/* (non-Javadoc)
	 * @see es.regueiro.lyricsDownloader.api.lyrics.Song#toString()
	 */
	@Override
	public String toString() {
		return "LyricText [getArtist()=" + getArtist() + ", getTitle()="
				+ getTitle() + ", getAlbum()=" + getAlbum() + ", lyrics="
				+ lyrics + "]";
	}

}
