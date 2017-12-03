package dcc196.trabalho_dcc.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dcc196.trabalho_dcc.DatabaseContract;

/**
 * Created by Davi on 30/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper sInstance;
    private static final String DATABASE_NAME = "feira_livros";
    private static final int DATABASE_VERSION = 2;

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DBHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.SQL_CREATE_PARTICIPANTE);
        db.execSQL(DatabaseContract.SQL_CREATE_LIVRO);
        db.execSQL(DatabaseContract.SQL_CREATE_RESERVA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.SQL_DROP_RESERVA);
        db.execSQL(DatabaseContract.SQL_DROP_PARTICIPANTE);
        db.execSQL(DatabaseContract.SQL_DROP_LIVRO);
        onCreate(db);
    }
}
