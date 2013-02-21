package es.regueiro.lyricsDownloader.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.apache.http.client.ClientProtocolException;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXTextField;


import es.regueiro.lyricsDownloader.api.lyrics.Song;
import es.regueiro.lyricsDownloader.api.lyrics.LyricResult;
import es.regueiro.lyricsDownloader.api.plugins.Plugin;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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

		initialize();
	}
	

	private void searchAndPopulateList(String artist, String title, int page) {
		this.artist = artist;
		this.title = title;
		this.page = page;
		this.pageCount = 0;
		
		List<LyricResult> lyricList = plugin.listLyricsFor(artist, title);
		
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		if (lyricList != null) {
			//this.pageCount = results.getPageCount();
			
			for (LyricResult lyr : lyricList) {
				String name = lyr.getArtist()+" - "+lyr.getTitle();
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
		frame.getContentPane().setLayout(
				new MigLayout("", "[]20px[grow]", "[]10px[]10px[]10px[grow]10px[]"));

		JLabel lblNewLabel = new JLabel("Artist");
		frame.getContentPane().add(lblNewLabel, "cell 0 0,alignx trailing");

		artistField = new JXTextField();
		artistField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFind.doClick();
			}
		});
		artistField.setPrompt("artist");
		frame.getContentPane().add(artistField, "cell 1 0,growx");

		JLabel lblNewLabel_1 = new JLabel("Track");
		frame.getContentPane().add(lblNewLabel_1, "cell 0 1,alignx trailing");

		trackField = new JXTextField();
		trackField.setPrompt("track");
		frame.getContentPane().add(trackField, "cell 1 1,growx");

		lblAlbum = new JLabel("Album");
		frame.getContentPane().add(lblAlbum, "cell 0 2,alignx trailing");

		albumField = new JXTextField();
		albumField.setPrompt("album");
		frame.getContentPane().add(albumField, "cell 1 2,growx");

		scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, "cell 0 3 2 1,grow");

		list = new JXList();
		scrollPane.setViewportView(list);
		list.setAutoCreateRowSorter(true);
		list.setDoubleBuffered(true);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panel = new JXPanel();
		frame.getContentPane().add(panel,
				"flowx,cell 0 4 2 1,alignx center,growy");
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
								if (pageCount-1 > page) {
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
	}

}
