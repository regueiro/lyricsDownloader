package es.regueiro.lyricsDownloader.plugins;

import es.regueiro.lyricsDownloader.plugins.api.Plugin;
import java.util.Iterator;
 
public interface PluginService
{
    Iterator<Plugin> getPlugins();
    void initPlugins();
}
