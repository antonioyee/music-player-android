package mx.antonioyee.musicplayer.database;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
                "  id_album INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "  name VARCHAR(250) NULL," +
                "  posterPic VARCHAR(45) NULL," +
                "  id_artist INTEGER NOT NULL," +
                "  id_genre INTEGER NOT NULL," +
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

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}
