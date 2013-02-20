package es.regueiro.lyricsDownloader.plugins.providers;

import es.regueiro.lyricsDownloader.api.lyrics.Lyric;
import es.regueiro.lyricsDownloader.api.lyrics.LyricFile;
import es.regueiro.lyricsDownloader.plugins.api.Plugin;
import com.pedrohlc.viewlyricsppensearcher.LyricInfo;
import com.pedrohlc.viewlyricsppensearcher.Result;
import com.pedrohlc.viewlyricsppensearcher.ViewLyricsSearcher;

import java.io.IOException;
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

	@Override
	public List<Lyric> search(String artist, String title) {
		return searchSyncedLyrics(artist, title);
	}

	private List<Lyric> searchSyncedLyrics(String artist, String title) {
		List<Lyric> lyricList = new ArrayList<Lyric>();
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

				Lyric lyric = new LyricFile();
				
				lyric.setArtist(lyr.getMusicArtist());
				lyric.setTitle(lyr.getMusicTitle());

				
				lyricList.add(lyric);
			}
		}

		return lyricList;
	}
}
