package es.regueiro.lyricsDownloader.ui;

import java.io.File;

import javax.swing.filechooser.FileFilter;


public class AudioFileFilter extends FileFilter {

	// Accept all directories and all gif, jpg, tiff, or png files.
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = Utils.getExtension(f);
		if (extension != null) {
			if (extension.equals(Utils.mp3) || extension.equals(Utils.flac)
					|| extension.equals(Utils.m4a)
					|| extension.equals(Utils.ogg)
					|| extension.equals(Utils.wma)) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	// The description of this filter
	public String getDescription() {
		return "Just Images";
	}
}

