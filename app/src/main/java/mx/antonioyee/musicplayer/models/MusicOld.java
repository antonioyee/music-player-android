package mx.antonioyee.musicplayer.models;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import mx.antonioyee.musicplayer.R;

/**
 * Created by antonioyee on 13/05/15.
 */
public class MusicOld {

    private String title;
    private String artist;
    private String album;
    private String duration;
    private Drawable photoAlbum;
    private String file;

    public MusicOld(String title, String artist, String album, String duration, Drawable photoAlbum, String file) {
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

    public static ArrayList<MusicOld> getSongs(Context context) {

        ArrayList<MusicOld> musica = new ArrayList<MusicOld>();

        musica.add(new MusicOld("In Public", "Tigerskin", "Try the imposible EP", "8:00", context.getResources().getDrawable(R.mipmap.tigerskininpublic), "tigerskin_in_public"));
        musica.add(new MusicOld("Like a Stone", "Audioslave", "Audioslave", "4.53", context.getResources().getDrawable(R.mipmap.audioslavelikeastone), "audioslave_like_a_stone"));
        musica.add(new MusicOld("Midnight City", "M83", "Midnight City", "4:03", context.getResources().getDrawable(R.mipmap.m83midnightcity), "m83_midnight_city"));
        musica.add(new MusicOld("Otherside", "Red Hot Chili Peppers", "Californication", "4:15", context.getResources().getDrawable(R.mipmap.otherside), "red_hot_chili_peppers_otherside"));
        musica.add(new MusicOld("Ready of Star", "Arcade Fire", "The Suburbs", "4:15", context.getResources().getDrawable(R.mipmap.ready_of_star), "arcade_fire_ready_to_start"));

        return musica;

    }
}
