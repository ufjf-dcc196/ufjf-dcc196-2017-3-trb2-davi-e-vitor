package dcc196.trabalho_dcc;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import dcc196.trabalho_dcc.model.DBHelper;

/**
 * Created by Vitor on 30/11/2017.
 */

public class ParticipanteAdapter extends CursorAdapter {

    private DBHelper databaseHelper;

    public ParticipanteAdapter(Context context, Cursor c) {
        super(context, c, 0);
        databaseHelper = DBHelper.getInstance(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
