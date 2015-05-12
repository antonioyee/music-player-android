package mx.antonioyee.musicplayer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by antonioyee on 12/05/15.
 */
public class DatabaseAdapter {

    private static DatabaseHelper mDBHelper;
    private static SQLiteDatabase mDb;

    public static SQLiteDatabase getDB(Context context){
        if ( mDb == null ){
            openDB(context);
        }

        return mDb;
    }

    public static boolean openDB(Context context){
        if ( mDBHelper != null ){
            mDBHelper.close();
        }

        mDBHelper = new DatabaseHelper(context);

        try {
            mDb =  mDBHelper.getWritableDatabase();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
