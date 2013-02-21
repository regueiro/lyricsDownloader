package es.regueiro.lyricsDownloader.api.lyrics;

// TODO: Auto-generated Javadoc
/**
 * The Class Song.
 */
public abstract class Song {

	/** The artist. */
	private String artist;
	
	/** The title. */
	private String title;
	
	/** The album. */
	private String album;

	/**
	 * Gets the artist.
	 *
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Sets the artist.
	 *
	 * @param artist the new artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the album.
	 *
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * Sets the album.
	 *
	 * @param album the new album
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Lyric [artist=" + artist + ", title=" + title + ", album="
				+ album + "]";
	}

}
