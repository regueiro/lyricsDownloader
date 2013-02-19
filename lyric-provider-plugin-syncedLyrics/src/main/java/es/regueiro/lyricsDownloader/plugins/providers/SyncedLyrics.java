package es.regueiro.lyricsDownloader.plugins.providers;

import es.regueiro.lyricsDownloader.plugins.api.Plugin;

import java.util.logging.Logger;

public class SyncedLyrics implements Plugin
{
    private static Logger logger = Logger.getLogger(SyncedLyrics.class.getName());
 
    public String getName()
    {
        return "SyncedLyrics";
    }
 
    public void init()
    {
        logger.info(getName() + " initialized!");
    }
}

