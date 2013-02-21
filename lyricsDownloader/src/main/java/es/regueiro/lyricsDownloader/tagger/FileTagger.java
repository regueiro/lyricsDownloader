package es.regueiro.lyricsDownloader.tagger;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class FileTagger {
	private AudioFile file;
	private Tag tag;
	
	public void setFile(File file) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		this.file = AudioFileIO.read(file);
		this.tag = this.file.getTag();
	}	

	public String getArtist() {
		return this.tag.getFirst(FieldKey.ARTIST);
	}
	
	public String getTitle() {
		return this.tag.getFirst(FieldKey.TITLE);
	}
	
	public String getLyrics() {
		return this.tag.getFirst(FieldKey.LYRICS);
	}
}