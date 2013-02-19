package es.regueiro.lyricsDownloader.results;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import es.regueiro.lyricsDownloader.lyrics.Lyric;

@Component
public class SearchResult {

	private List<Lyric> lyricList;

	public SearchResult() {
		this.lyricList = new ArrayList<Lyric>();
	}

	public List<Lyric> getLyricList() {
		return lyricList;
	}

	public void setLyricList(List<Lyric> lyricList) {
		if (lyricList != null) {
			this.lyricList = lyricList;
		} else {
			this.clear();
		}
	}

	public boolean addLyric(Lyric l) {
		return lyricList.add(l);
	}

	public boolean removeLyric(Lyric l) {
		return lyricList.remove(l);
	}

	public boolean addAllLyrics(Collection<? extends Lyric> c) {
		return lyricList.addAll(c);
	}

	public boolean removeAllLyrics(Collection<?> c) {
		return lyricList.removeAll(c);
	}

	public void clear() {
		lyricList.clear();
	}
}
