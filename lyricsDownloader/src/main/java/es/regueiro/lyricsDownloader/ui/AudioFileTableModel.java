package es.regueiro.lyricsDownloader.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import es.regueiro.lyricsDownloader.system.AudioFile;

public class AudioFileTableModel extends AbstractTableModel {
	private List<AudioFile> data;
	private List<String> columns;

	public AudioFileTableModel() {
		columns = new ArrayList<String>();

		columns.add("Filename");
		columns.add("Artist");
		columns.add("Title");
		columns.add("Lyrics");
		
		data = new ArrayList<AudioFile>();
	}
	
	public void addFile(AudioFile file){
		data.add(file);
		this.fireTableDataChanged();
	}

	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public String getColumnName(int column) {
		return columns.get(column);
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int column) {
		AudioFile audioFile = data.get(row);
		
		Object result = null;
		
		switch (column) {
		case 0:
			result = audioFile.getFileName().toString();
			break;
		case 1:
			result = audioFile.getArtist();
			break;
		case 2:
			result = audioFile.getTitle();
			break;
		case 3:
			result = audioFile.getLyrics();
			break;
		}

		return result;
	}
}