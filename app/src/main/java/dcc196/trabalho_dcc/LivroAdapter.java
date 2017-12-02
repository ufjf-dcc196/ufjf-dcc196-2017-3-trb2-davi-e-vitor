package dcc196.trabalho_dcc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Random;

import dcc196.trabalho_dcc.model.DBHelper;
import dcc196.trabalho_dcc.model.Livro;

/**
 * Created by Vitor on 30/11/2017.
 */

public class LivroAdapter extends CursorAdapter {

    private DBHelper dbHelper;

    public LivroAdapter(Context context, Cursor c) {
        super(context, c, 0);
        dbHelper = DBHelper.getInstance(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.listview_layout,viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv_titulo = (TextView) view.findViewById(R.id.textview_titulo);

        String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Livro.COLUMN_NAME_TITULO));

        tv_titulo.setText(titulo);
    }

    public void atualizar(){
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] visao = {
                    DatabaseContract.Livro._ID,
                    DatabaseContract.Livro.COLUMN_NAME_TITULO,
                    //DatabaseContract.Reserva.COLUMN_NAME_IDLIVRO,
            };
            Cursor c = db.query(DatabaseContract.Livro.TABLE_NAME, visao, null, null, null, null, null);
            this.changeCursor(c);

        } catch (Exception e) {
            Log.e("LIVRO", e.getLocalizedMessage());
            Log.e("LIVRO", e.getStackTrace().toString());
        }
    }

    public void inserir(Livro livro){
        try {
            Random rnd = new Random();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(DatabaseContract.Livro.COLUMN_NAME_TITULO, livro.getTitulo());
            values.put(DatabaseContract.Livro.COLUMN_NAME_EDITORA, livro.getEditora());
            values.put(DatabaseContract.Livro.COLUMN_NAME_ANO, livro.getAnoPlubicacao());

            long id = db.insert(DatabaseContract.Livro.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("LIVRO", e.getLocalizedMessage());
            Log.e("LIVRO", e.getStackTrace().toString());
        }
    }
}