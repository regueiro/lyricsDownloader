package es.regueiro.lyricsDownloader.plugins;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Logger;

import es.regueiro.lyricsDownloader.api.plugins.Plugin;

public class StandardPluginService implements PluginService {
	private static StandardPluginService pluginService;
	private ServiceLoader<Plugin> serviceLoader;
	private Logger logger = Logger.getLogger(getClass().getName());

	private StandardPluginService() {
		// load all the classes in the classpath that have implemented the
		// interface
		serviceLoader = ServiceLoader.load(Plugin.class);
	}

	public static StandardPluginService getInstance() {
		if (pluginService == null) {
			pluginService = new StandardPluginService();
		}
		return pluginService;
	}

	public Iterator<Plugin> getPlugins() {
		return serviceLoader.iterator();
	}

	public void initPlugins() {
		Iterator<Plugin> iterator = getPlugins();
		if (!iterator.hasNext()) {
			logger.info("No plugins were found!");
		}
		while (iterator.hasNext()) {
			Plugin plugin = iterator.next();
			logger.info("Initializing the plugin " + plugin.getName());
			plugin.init();
		}
	}
}