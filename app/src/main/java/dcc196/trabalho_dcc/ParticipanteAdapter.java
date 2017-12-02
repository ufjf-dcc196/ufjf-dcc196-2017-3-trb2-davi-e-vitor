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

import dcc196.trabalho_dcc.model.DBHelper;
import dcc196.trabalho_dcc.model.Participante;

/**
 * Created by Vitor on 30/11/2017.
 */

public class ParticipanteAdapter extends CursorAdapter {

    private DBHelper dbHelper;

    public ParticipanteAdapter(Context context, Cursor c) {
        super(context, c, 0);
        dbHelper = DBHelper.getInstance(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.listview_layout, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv_nome = (TextView) view.findViewById(R.id.textview_titulo);

        String nome = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Participante.COLUMN_NAME_NOME));

        tv_nome.setText(nome);
    }

    public void atualizar(){
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] visao = {
                    DatabaseContract.Participante._ID,
                    DatabaseContract.Participante.COLUMN_NAME_NOME,
            };
            Cursor c = db.query(DatabaseContract.Participante.TABLE_NAME, visao, null, null, null, null, null);
            this.changeCursor(c);

        } catch (Exception e) {
            Log.e("PARTICIPANTE", e.getLocalizedMessage());
            Log.e("PARTICIPANTE", e.getStackTrace().toString());
        }
    }

    public void inserir(Participante participante){
        try {

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(DatabaseContract.Participante.COLUMN_NAME_NOME, participante.getNomeCompleto());
            values.put(DatabaseContract.Participante.COLUMN_NAME_EMAIL, participante.getEmail());

            long id = db.insert(DatabaseContract.Participante.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("PARTICIPANTE", e.getLocalizedMessage());
            Log.e("PARTICIPANTE", e.getStackTrace().toString());
        }
    }
}
