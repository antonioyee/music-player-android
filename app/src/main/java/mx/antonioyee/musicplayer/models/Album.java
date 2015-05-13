package mx.antonioyee.musicplayer.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mx.antonioyee.musicplayer.database.DatabaseAdapter;

/**
 * Created by antonioyee on 13/05/15.
 */
public class Album {

    public static final String TABLE_NAME   = "album";
    public static final String ALBUM_ID     = "id_album";
    public static final String NAME         = "name";
    public static final String POSTER_PIC   = "posterPic";
    public static final String ARTIST_ID    = "id_artist";
    public static final String GENRE_ID     = "id_genre";

    private int id_album;
    private String name;
    private String posterPic;
    private int id_artist;
    private int id_genre;

    public Album(String name, String posterPic, int id_artist, int id_genre) {
        this.name = name;
        this.posterPic = posterPic;
        this.id_artist = id_artist;
        this.id_genre = id_genre;
    }

    public int getId_album() {
        return id_album;
    }

    public void setId_album(int id_album) {
        this.id_album = id_album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPic() {
        return posterPic;
    }

    public void setPosterPic(String posterPic) {
        this.posterPic = posterPic;
    }

    public int getId_artist() {
        return id_artist;
    }

    public void setId_artist(int id_artist) {
        this.id_artist = id_artist;
    }

    public int getId_genre() {
        return id_genre;
    }

    public void setId_genre(int id_genre) {
        this.id_genre = id_genre;
    }

    public static long insert(Context context, Album album){
        ContentValues cv = new ContentValues();

        cv.put(NAME, album.getName());
        cv.put(POSTER_PIC, album.getPosterPic());
        cv.put(ARTIST_ID, album.getId_artist());
        cv.put(GENRE_ID, album.getId_genre());

        return DatabaseAdapter.getDB(context).insert(TABLE_NAME, null, cv);
    }

    public static int GetIdAlbum(Context context, String nameAlbum){
        try{
            int id = 0;
            Cursor cursor = DatabaseAdapter.getDB(context).query(TABLE_NAME, null, NAME +"=?", new String[]{ nameAlbum }, null,null,null,null);

            if ( cursor != null){
                for ( cursor.moveToFirst(); ! cursor.isAfterLast(); cursor.moveToNext() ){
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(ALBUM_ID));
                }
            }

            return id;

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
