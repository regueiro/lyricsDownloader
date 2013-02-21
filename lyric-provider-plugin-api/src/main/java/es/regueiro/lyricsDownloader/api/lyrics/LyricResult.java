package es.regueiro.lyricsDownloader.api.lyrics;

import java.net.URL;

// TODO: Auto-generated Javadoc
/**
 * The Class LyricResult.
 */
public class LyricResult extends Song {

	/** The file name. */
	private String fileName;
	
	/** The source url. */
	private URL sourceURL;
	
	/** The uploader. */
	private String uploader;
	
	/** The rating. */
	private long rating;
	
	/** The votes. */
	private int votes;
	
	/** The downloads. */
	private int downloads;

	/**
	 * Instantiates a new lyric result.
	 */
	public LyricResult() {

	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the source url.
	 *
	 * @return the source url
	 */
	public URL getSourceURL() {
		return sourceURL;
	}

	/**
	 * Sets the source url.
	 *
	 * @param sourceURL the new source url
	 */
	public void setSourceURL(URL sourceURL) {
		this.sourceURL = sourceURL;
	}

	/**
	 * Gets the uploader.
	 *
	 * @return the uploader
	 */
	public String getUploader() {
		return uploader;
	}

	/**
	 * Sets the uploader.
	 *
	 * @param uploader the new uploader
	 */
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public long getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(long rating) {
		this.rating = rating;
	}

	/**
	 * Gets the votes.
	 *
	 * @return the votes
	 */
	public int getVotes() {
		return votes;
	}

	/**
	 * Sets the votes.
	 *
	 * @param votes the new votes
	 */
	public void setVotes(int votes) {
		this.votes = votes;
	}

	/**
	 * Gets the downloads.
	 *
	 * @return the downloads
	 */
	public int getDownloads() {
		return downloads;
	}

	/**
	 * Sets the downloads.
	 *
	 * @param downloads the new downloads
	 */
	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}

	/* (non-Javadoc)
	 * @see es.regueiro.lyricsDownloader.api.lyrics.Song#toString()
	 */
	@Override
	public String toString() {
		return "LyricFile [getArtist()=" + getArtist() + ", getTitle()="
				+ getTitle() + ", getAlbum()=" + getAlbum() + ", uploader="
				+ uploader + ", fileName=" + fileName + ", rating=" + rating
				+ ", votes=" + votes + ", downloads=" + downloads
				+ ", sourceURL=" + sourceURL.toString() + "]";
	}

}
