package es.regueiro.lyricsDownloader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.regueiro.lyricsDownloader.lyrics.LyricFile;
import es.regueiro.lyricsDownloader.ui.MainWindow;

public class lyricsDownloader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"lyric-beans.xml");
		LyricFile lyricFile = (LyricFile) context.getBean("lyricFile");

		MainWindow.run();
	}

}
