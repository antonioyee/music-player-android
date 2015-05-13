package mx.antonioyee.musicplayer.models;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import mx.antonioyee.musicplayer.R;
import mx.antonioyee.musicplayer.database.DatabaseAdapter;

/**
 * Created by antonioyee on 06/05/15.
 */
public class Music {

    public static final String TABLE_NAME = "song";
    public static final String SONG_ID = "id_song";
    public static final String NAME = "name";
    public static final String FILE_NAME = "fileName";
    public static final String ALBUM_ID = "id_album";

    private int id_song;
    private String name;
    private String file_name;
    private int id_album;

    public Music(String name, String file_name, int id_album) {
        this.name = name;
        this.file_name = file_name;
        this.id_album = id_album;
    }

    public int getId_song() {
        return id_song;
    }

    public void setId_song(int id_song) {
        this.id_song = id_song;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public int getId_album() {
        return id_album;
    }

    public void setId_album(int id_album) {
        this.id_album = id_album;
    }

    public static long insert(Context context, Music music){
        ContentValues cv = new ContentValues();

        cv.put(NAME, music.getName());
        cv.put(FILE_NAME, music.getFile_name());
        cv.put(ALBUM_ID, music.getId_album());

        return DatabaseAdapter.getDB(context).insert(TABLE_NAME, null, cv);
    }
}
