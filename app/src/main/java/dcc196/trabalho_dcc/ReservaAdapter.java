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

import java.util.Random;

import dcc196.trabalho_dcc.model.DBHelper;

/**
 * Created by Vitor on 30/11/2017.
 */

public class ReservaAdapter extends CursorAdapter{
    private DBHelper databaseHelper;

    public ReservaAdapter(Context context, Cursor c) {
        super(context, c, 0);
        databaseHelper = DBHelper.getInstance(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.activity_cadastro_reserva, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }

    public void atualizar(){
        try {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            String[] visao = {
                    DatabaseContract.Reserva._ID,
                    DatabaseContract.Reserva.COLUMN_NAME_IDPARTICIPANTE,
                    DatabaseContract.Reserva.COLUMN_NAME_IDLIVRO,
            };
            String selecao = DatabaseContract.Reserva.COLUMN_NAME_IDPARTICIPANTE + " > ?";
            String[] args = {"0"};
            String sort = DatabaseContract.Reserva.COLUMN_NAME_IDPARTICIPANTE + " DESC";
            //db.query(tabela, colunas, selecao, args, group, havinf, order);
            Cursor c = db.query(DatabaseContract.Reserva.TABLE_NAME, visao, selecao, args, null, null, sort);
            this.changeCursor(c);

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }

    public void inserirAleatorio(){
        try {
            Random rnd = new Random();
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.Reserva.COLUMN_NAME_IDPARTICIPANTE, rnd.nextInt(50));
            values.put(DatabaseContract.Reserva.COLUMN_NAME_IDLIVRO, rnd.nextInt(100));
            long id = db.insert(DatabaseContract.Reserva.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("Reserva", e.getLocalizedMessage());
            Log.e("Reserva", e.getStackTrace().toString());
        }
    }
}
