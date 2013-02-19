package es.regueiro.lyricsDownloader.results;

import org.springframework.stereotype.Component;

@Component
public class PartialResult extends SearchResult {

	private int page;
	private int numPages;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 0) {
			throw new IllegalArgumentException(
					"The number of the page may not be negative");
		} else if (page > numPages) {
			throw new IllegalArgumentException(
					"The number of the page can not excede the total number "
							+ "of pages");
		} else {
			this.page = page;
		}
	}

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		if (numPages < 0) {
			throw new IllegalArgumentException(
					"The number of pages may not be negative");
		} else {
			this.numPages = numPages;
		}
	}
}
