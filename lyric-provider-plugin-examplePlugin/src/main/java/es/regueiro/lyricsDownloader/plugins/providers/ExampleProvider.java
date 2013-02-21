package es.regueiro.lyricsDownloader.plugins.providers;

import es.regueiro.lyricsDownloader.api.lyrics.Lyric;
import es.regueiro.lyricsDownloader.api.lyrics.LyricResult;
import es.regueiro.lyricsDownloader.api.plugins.Plugin;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExampleProvider implements Plugin {

	@Override
	public String getName() {
		return "Example provider";
	}

	@Override
	public void init() {
		System.out.println("Initializing " + getName());
	}

	@Override
	public void shutdown() {
		System.out.println("Shutting down " + getName());
	}

	@Override
	public List<LyricResult> listLyricsFor(String artist, String title) {
		List<LyricResult> resultList = new ArrayList<LyricResult>();

		for (int i = 0; i < 100; i++) {
			LyricResult result = new LyricResult();
			result.setArtist(artist);
			result.setTitle(title + " " + i);
			result.setAlbum("test album");

			resultList.add(result);
		}

		return resultList;
	}

	@Override
	public Lyric getLyricsFromURL(URL url) {
		Lyric lyr = new Lyric();

		lyr.setAlbum("URLalbum");
		lyr.setArtist("URLartist");
		lyr.setTitle("URLtitle");
		lyr.setLyrics("URLlyrics");

		return lyr;
	}

	@Override
	public Lyric getLyricFor(String artist, String title) {
		Lyric lyr = new Lyric();

		lyr.setAlbum("SINGLEalbum");
		lyr.setArtist("SINGLEartist");
		lyr.setTitle("SINGLEtitle");
		lyr.setLyrics("SINGLElyrics");

		return lyr;
	}

}
