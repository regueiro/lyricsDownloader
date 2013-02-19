package es.regueiro.lyricsDownloader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.regueiro.lyricsDownloader.lyrics.LyricFile;
import es.regueiro.lyricsDownloader.plugins.PluginService;
import es.regueiro.lyricsDownloader.plugins.PluginServiceFactory;
import es.regueiro.lyricsDownloader.plugins.api.Plugin;
//import es.regueiro.lyricsDownloader.plugins.Plugin;
//import es.regueiro.lyricsDownloader.plugins.PluginManager;
import es.regueiro.lyricsDownloader.ui.MainWindow;

public class lyricsDownloader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"lyric-beans.xml");
		LyricFile lyricFile = (LyricFile) context.getBean("lyricFile");

		Plugin plu = null;
		
		for(Plugin p: loadPlugins()) {
			plu = p;
		}

		MainWindow.run(plu);
	}

	private static List<Plugin> loadPlugins() {
		PluginService pluginService = PluginServiceFactory
				.createPluginService();
		pluginService.initPlugins();
		
		List<Plugin> list = new ArrayList<Plugin>();
		Iterator<Plugin> iter = pluginService.getPlugins();
		
		while (iter.hasNext()) {
			list.add(iter.next());
		}
		
		return list;
	}

	// private static void loadPlugins(){
	// PluginManager manager = new PluginManager();
	// List<Plugin> plugins;
	// try {
	// plugins = manager.loadPlugins();
	// for(Plugin plugin:plugins){
	// plugin.run();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

}
