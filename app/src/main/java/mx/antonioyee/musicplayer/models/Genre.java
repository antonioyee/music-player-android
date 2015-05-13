package mx.antonioyee.musicplayer.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mx.antonioyee.musicplayer.database.DatabaseAdapter;

/**
 * Created by antonioyee on 13/05/15.
 */
public class Genre {

    public static final String TABLE_NAME   = "genre";
    public static final String ID_GENRE = "id_genre";
    public static final String NAME = "name";

    private int id_genre;
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public int getId_genre() {
        return id_genre;
    }

    public void setId_genre(int id_genero) {
        this.id_genre = id_genero;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long insert(Context context, Genre genre){
        ContentValues cv = new ContentValues();

        cv.put(NAME, genre.getName());

        return DatabaseAdapter.getDB(context).insert(TABLE_NAME, null, cv);
    }

    public static int GetIdGenre(Context context, String nameGenre){
        try{
            int id = 0;
            Cursor cursor = DatabaseAdapter.getDB(context).query(TABLE_NAME, null, NAME +"=?", new String[]{ nameGenre }, null,null,null,null);

            if ( cursor != null){
                for ( cursor.moveToFirst(); ! cursor.isAfterLast(); cursor.moveToNext() ){
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_GENRE));
                }
            }

            return id;

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

}
