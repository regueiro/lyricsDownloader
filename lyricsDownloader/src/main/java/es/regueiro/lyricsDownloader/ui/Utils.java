package es.regueiro.lyricsDownloader.ui;

import java.io.File;

public class Utils {

	public final static String mp3 = "mp3";
	public final static String flac = "flac";
	public final static String wma = "wma";
	public final static String ogg = "ogg";
	public final static String m4a = "m4a";

	/*
	 * Get the extension of a file.
	 */
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}