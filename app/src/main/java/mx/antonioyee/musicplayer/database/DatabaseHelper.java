package mx.antonioyee.musicplayer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by antonioyee on 12/05/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MusicPlayer.db";
    private static final int VER_1 = 1;
    private static final int DATABASE_VERSION = VER_1;

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS artist (" +
                    "  id_artist INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "  name VARCHAR(250) NOT NULL," +
                    "  age INTEGER NULL," +
                    "  photo VARCHAR(250) NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS genre (" +
                    "  id_genre INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "  name VARCHAR(250) NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS album (" +
                    "  id_album INT NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "  name VARCHAR(250) NULL," +
                    "  posterPic VARCHAR(45) NULL," +
                    "  id_artist INT NOT NULL," +
                    "  id_genre INT NOT NULL," +
                    "  FOREIGN KEY(id_artist) REFERENCES artist (id_artist)," +
                    "  FOREIGN KEY(id_genre) REFERENCES genre (id_genre) )");

        db.execSQL("CREATE TABLE IF NOT EXISTS song (" +
                    "  id_song INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "  name VARCHAR(250) NULL," +
                    "  fileName VARCHAR(250) NULL," +
                    "  id_album INTEGER NOT NULL," +
                    "  FOREIGN KEY(id_album) REFERENCES album (id_album) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if ( oldVersion != DATABASE_VERSION ){
            db.execSQL("DROP TABLE IF EXISTS artist");
            db.execSQL("DROP TABLE IF EXISTS genre");
            db.execSQL("DROP TABLE IF EXISTS album");
            db.execSQL("DROP TABLE IF EXISTS song");
        }
    }
}
