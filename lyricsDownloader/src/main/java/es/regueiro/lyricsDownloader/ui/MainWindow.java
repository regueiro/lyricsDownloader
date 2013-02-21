package es.regueiro.lyricsDownloader.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.apache.http.client.ClientProtocolException;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXTextField;

import es.regueiro.lyricsDownloader.api.lyrics.Song;
import es.regueiro.lyricsDownloader.api.lyrics.LyricResult;
import es.regueiro.lyricsDownloader.api.plugins.Plugin;
import es.regueiro.lyricsDownloader.system.AudioFile;
import es.regueiro.lyricsDownloader.tagger.FileTagger;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import org.jdesktop.swingx.JXTreeTable;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;
import javax.swing.SwingConstants;
import org.jdesktop.swingx.JXTable;

public class MainWindow {

	private JFrame frame;
	private JLabel lblAlbum;
	private JXButton btnFind;
	private JXTextField artistField;
	private JXTextField trackField;
	private JXTextField albumField;
	private JXList list;
	private JScrollPane scrollPane;
	private JXButton btnNextPage;
	private JXButton btnPreviousPage;
	private JXPanel panel;
	private JXButton btnClear;
	private int page;
	private int pageCount;
	private String artist;
	private String title;

	private Plugin plugin;
	private JPanel panel_1;
	private JTabbedPane tabbedPane;
	private JPanel panel_2;
	private JXButton btnAddFile;
	private JXButton btnAddFolder;
	private JXButton btnClear_1;
	private JLabel lblFileList;
	private JXTable table;
	private AudioFileTableModel tableModel;
	private JScrollPane scrollPane_1;
	
	private FileTagger tagger;

	/**
	 * Launch the application.
	 */
	public static void run(final Plugin plugin) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.plugin = plugin;
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}

		tagger = new FileTagger();
		
		initialize();
	}

	private List<File> processDirectory(File file) {
		List<File> foundFiles = new ArrayList<File>();

		if (file.isFile()) {
			String extension = Utils.getExtension(file);
			if (extension != null) {
				if (extension.equals(Utils.mp3) || extension.equals(Utils.flac)
						|| extension.equals(Utils.m4a)
						|| extension.equals(Utils.ogg)
						|| extension.equals(Utils.wma)) {
					foundFiles.add(file);
				}
			}
		} else if (file.isDirectory()) {
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++)
				foundFiles.addAll(processDirectory(listOfFiles[i]));
		}
		return foundFiles;
	}

	private void handleFile(File file) {
		List<File> fileList;

		fileList = processDirectory(file);

		for (File f : fileList) {
			try {
				tagger.setFile(f);
				AudioFile audioFile = new AudioFile();
				audioFile.setFileName(f);
				audioFile.setArtist(tagger.getArtist());
				audioFile.setTitle(tagger.getTitle());
				audioFile.setLyrics(tagger.getLyrics());
				tableModel.addFile(audioFile);
			} catch (CannotReadException | IOException | TagException
					| ReadOnlyFileException | InvalidAudioFrameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	private void showFileChooser() {
		final JFileChooser fc = new JFileChooser();

		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		FileFilter filter = new AudioFileFilter();
		fc.setFileFilter(filter);

		fc.setMultiSelectionEnabled(true);

		int returnVal = fc.showOpenDialog(frame);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			handleFile(file);

		} else {

		}
	}

	private void searchAndPopulateList(String artist, String title, int page) {
		this.artist = artist;
		this.title = title;
		this.page = page;
		this.pageCount = 0;

		List<LyricResult> lyricList = plugin.listLyricsFor(artist, title);

		DefaultListModel<String> listModel = new DefaultListModel<String>();

		if (lyricList != null) {
			// this.pageCount = results.getPageCount();

			for (LyricResult lyr : lyricList) {
				String name = lyr.getArtist() + " - " + lyr.getTitle();
				listModel.addElement(name);
			}
		}

		list.setModel(listModel);
	}

	private void showNextPage() {
		searchAndPopulateList(artist, title, page + 1);
	}

	private void showPreviousPage() {
		if (page > 0) {
			searchAndPopulateList(artist, title, page - 1);
		} else {
			searchAndPopulateList(artist, title, 0);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 684, 602);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 423, 0 };
		gridBagLayout.rowHeights = new int[] { 273, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);

		panel_1 = new JPanel();
		tabbedPane.addTab("Manual Search", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[56.00][grow]", "[][][][grow][]"));

		JLabel lblNewLabel = new JLabel("Artist");
		panel_1.add(lblNewLabel, "cell 0 0,alignx trailing");

		artistField = new JXTextField();
		panel_1.add(artistField, "cell 1 0,growx");
		artistField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFind.doClick();
			}
		});
		artistField.setPrompt("artist");

		JLabel lblNewLabel_1 = new JLabel("Track");
		panel_1.add(lblNewLabel_1, "cell 0 1,alignx trailing");

		trackField = new JXTextField();
		panel_1.add(trackField, "cell 1 1,growx");
		trackField.setPrompt("track");

		lblAlbum = new JLabel("Album");
		panel_1.add(lblAlbum, "cell 0 2,alignx trailing");

		albumField = new JXTextField();
		panel_1.add(albumField, "cell 1 2,growx");
		albumField.setPrompt("album");

		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, "cell 0 3 2 1,grow");

		list = new JXList();
		scrollPane.setViewportView(list);
		list.setAutoCreateRowSorter(true);
		list.setDoubleBuffered(true);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panel = new JXPanel();
		panel_1.add(panel, "cell 0 4 2 1,alignx center,growy");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 55, 85, 20, 0, 0 };
		gbl_panel.rowHeights = new int[] { 25, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		btnFind = new JXButton();
		GridBagConstraints gbc_btnFind = new GridBagConstraints();
		gbc_btnFind.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnFind.insets = new Insets(0, 0, 0, 50);
		gbc_btnFind.gridx = 0;
		gbc_btnFind.gridy = 0;
		panel.add(btnFind, gbc_btnFind);
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						searchAndPopulateList(artistField.getText(),
								trackField.getText(), 0);

						if (pageCount > 1) {
							btnNextPage.setEnabled(true);
						}
						btnPreviousPage.setEnabled(false);
						btnClear.setEnabled(true);
					}
				});

			}
		});
		btnFind.setText("Find");

		btnNextPage = new JXButton();
		GridBagConstraints gbc_btnNextPage = new GridBagConstraints();
		gbc_btnNextPage.insets = new Insets(0, 0, 0, 5);
		gbc_btnNextPage.gridx = 2;
		gbc_btnNextPage.gridy = 0;
		panel.add(btnNextPage, gbc_btnNextPage);
		btnNextPage.setEnabled(false);
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						showNextPage();
						btnClear.setEnabled(true);
						if (pageCount - 1 > page) {
							btnNextPage.setEnabled(true);
						} else {
							btnNextPage.setEnabled(false);
						}
						btnPreviousPage.setEnabled(true);
					}
				});

			}
		});
		btnNextPage.setText("Next Page");

		btnClear = new JXButton();
		btnClear.setEnabled(false);
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 50, 0, 0);
		gbc_btnClear.gridx = 3;
		gbc_btnClear.gridy = 0;
		panel.add(btnClear, gbc_btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						list.setModel(new DefaultListModel<String>());
						btnClear.setEnabled(false);
						btnNextPage.setEnabled(false);
						btnPreviousPage.setEnabled(false);
					}
				});

			}
		});
		btnClear.setText("Clear");

		btnPreviousPage = new JXButton();
		btnPreviousPage.setEnabled(false);
		GridBagConstraints gbc_btnPreviousPage = new GridBagConstraints();
		gbc_btnPreviousPage.insets = new Insets(0, 0, 0, 5);
		gbc_btnPreviousPage.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnPreviousPage.gridx = 1;
		gbc_btnPreviousPage.gridy = 0;
		panel.add(btnPreviousPage, gbc_btnPreviousPage);
		btnPreviousPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						showPreviousPage();
						btnClear.setEnabled(true);
						btnNextPage.setEnabled(true);
						if (page > 0) {
							btnPreviousPage.setEnabled(true);
						}
					}
				});

			}
		});
		btnPreviousPage.setText("Previous Page");

		panel_2 = new JPanel();
		tabbedPane.addTab("Library Search", null, panel_2, null);
		panel_2.setLayout(new MigLayout("", "[grow][]", "[][][][grow]"));

		lblFileList = new JLabel("File List");
		panel_2.add(lblFileList, "cell 0 0");

		btnAddFile = new JXButton();
		btnAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFileChooser();
			}
		});
		btnAddFile.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddFile.setText("Add File");
		panel_2.add(btnAddFile, "cell 1 0,growx");

		scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, "cell 0 1 1 3,grow");

		tableModel = new AudioFileTableModel();
		table = new JXTable();
		scrollPane_1.setViewportView(table);
		table.setColumnMargin(5);
		table.setColumnControlVisible(true);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setModel(tableModel);

		btnAddFolder = new JXButton();
		btnAddFolder.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddFolder.setText("Add Folder");
		panel_2.add(btnAddFolder, "cell 1 1,growx");

		btnClear_1 = new JXButton();
		btnClear_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnClear_1.setText("Clear");
		panel_2.add(btnClear_1, "cell 1 2,growx");

	}

}
