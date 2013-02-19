package es.regueiro.lyricsDownloader.plugins.api;

import java.util.List;

import es.regueiro.lyricsDownloader.api.lyrics.Lyric;

public interface Plugin {

    String getName();
    void init();
    
    List<Lyric> search(String artist, String title);
}