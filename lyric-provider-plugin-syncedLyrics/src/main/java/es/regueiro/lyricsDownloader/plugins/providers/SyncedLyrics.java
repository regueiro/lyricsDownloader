package es.regueiro.lyricsDownloader.plugins.providers;

import es.regueiro.lyricsDownloader.api.lyrics.Lyric;
import es.regueiro.lyricsDownloader.api.lyrics.Song;
import es.regueiro.lyricsDownloader.api.lyrics.LyricResult;
import es.regueiro.lyricsDownloader.api.plugins.Plugin;

import com.pedrohlc.viewlyricsppensearcher.LyricInfo;
import com.pedrohlc.viewlyricsppensearcher.Result;
import com.pedrohlc.viewlyricsppensearcher.ViewLyricsSearcher;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.client.ClientProtocolException;

public class SyncedLyrics implements Plugin {
	private static Logger logger = Logger.getLogger(SyncedLyrics.class
			.getName());

	public String getName() {
		return "SyncedLyrics";
	}

	public void init() {
		logger.info(getName() + " initialized!");
	}

	private List<LyricResult> searchSyncedLyrics(String artist, String title) {
		List<LyricResult> lyricList = new ArrayList<LyricResult>();
		int page = 0;
		int pageCount = 0;
		Result results = null;

		try {
			results = ViewLyricsSearcher.search(artist, title, page);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}

		if (results != null) {
			pageCount = results.getPageCount();

			for (LyricInfo lyr : results.getLyricsInfo()) {

				LyricResult lyric = new LyricResult();
				
				lyric.setArtist(lyr.getMusicArtist());
				lyric.setTitle(lyr.getMusicTitle());

				
				lyricList.add(lyric);
			}
		}

		return lyricList;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LyricResult> listLyricsFor(String artist, String title) {
		return searchSyncedLyrics(artist, title);
	}

	@Override
	public Lyric getLyricsFromURL(URL url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lyric getLyricFor(String artist, String title) {
		// TODO Auto-generated method stub
		return null;
	}
}
