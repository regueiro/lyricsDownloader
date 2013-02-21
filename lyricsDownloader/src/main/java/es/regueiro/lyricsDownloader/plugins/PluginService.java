package es.regueiro.lyricsDownloader.plugins;

import es.regueiro.lyricsDownloader.api.plugins.Plugin;

import java.util.Iterator;

public interface PluginService {
	Iterator<Plugin> getPlugins();

	void initPlugins();
}
