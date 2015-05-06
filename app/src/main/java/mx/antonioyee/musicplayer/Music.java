package mx.antonioyee.musicplayer;

import android.graphics.drawable.Drawable;

/**
 * Created by antonioyee on 06/05/15.
 */
public class Music {

    private String title;
    private String artist;
    private String album;
    private String duration;
    private Drawable photoAlbum;
    private String file;

    public Music(String title, String artist, String album, String duration, Drawable photoAlbum, String file) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.photoAlbum = photoAlbum;
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Drawable getPhotoAlbum() {
        return photoAlbum;
    }

    public void setPhotoAlbum(Drawable photoAlbum) {
        this.photoAlbum = photoAlbum;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
