package es.regueiro.lyricsDownloader.api.plugins;

import java.net.URL;
import java.util.List;

import es.regueiro.lyricsDownloader.api.lyrics.Lyric;
import es.regueiro.lyricsDownloader.api.lyrics.LyricResult;

/**
 * The interface plugins must conform to
 */
public interface Plugin {

	/**
	 * Returns the name of the plugin.
	 * 
	 * @return The name of the plugin
	 */
	String getName();

	/**
	 * Called when the plugin is being initialised.
	 */
	void init();

	/**
	 * Called when the plugin is being uninstalled.
	 */
	void shutdown();

	/**
	 * Searches for a list of lyrics that match the parameters provided.
	 * 
	 * @param artist
	 *            The artist of the song
	 * @param title
	 *            The title of the song
	 * @return A list of the found lyrics or an empty list or null if nothing is
	 *         found
	 */
	List<LyricResult> listLyricsFor(String artist, String title);

	/**
	 * Connects to the provided url to download the lyrics and return them.
	 * 
	 * @param url
	 *            The URL of the lyrics
	 * @return A lyric with the contents of the url
	 */
	Lyric getLyricsFromURL(URL url);

	/**
	 * Returns a single lyric matching the parameters provided.
	 * 
	 * @param artist
	 *            The artist of the song
	 * @param title
	 *            The title of the song
	 * @return The lyrics of the song
	 */
	Lyric getLyricFor(String artist, String title);
}