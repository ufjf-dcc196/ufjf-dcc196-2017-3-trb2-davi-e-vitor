package dcc196.trabalho_dcc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import dcc196.trabalho_dcc.model.DBHelper;

public class DetalheParticipante extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtEmail;
    private TextView txtHoraEntrada;
    private TextView txtHoraSaida;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_participante);

        txtNome = (TextView) findViewById(R.id.txtNome);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtHoraEntrada = (TextView) findViewById(R.id.txtHoraEntrada);
        txtHoraSaida = (TextView) findViewById(R.id.txtHoraSaida);

        Intent i = getIntent();
        long id = i.getLongExtra("ID_PARTICIPANTE",0);

        dbHelper = DBHelper.getInstance(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] visao = {
                    DatabaseContract.Participante._ID,
                    DatabaseContract.Participante.COLUMN_NAME_NOME,
                    DatabaseContract.Participante.COLUMN_NAME_EMAIL,
                    DatabaseContract.Participante.COLUMN_NAME_HORA_ENTRADA,
                    DatabaseContract.Participante.COLUMN_NAME_HORA_SAIDA,
        };

        String selecao = DatabaseContract.Participante._ID + " = ?";
        String[] args = {Long.toString(id)};
        Cursor c = db.query(DatabaseContract.Participante.TABLE_NAME, visao, selecao, args, null, null, null);

        c.moveToFirst();

        String nome = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Participante.COLUMN_NAME_NOME));
        String email = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Participante.COLUMN_NAME_EMAIL));
        String horaEntrada = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Participante.COLUMN_NAME_HORA_ENTRADA));
        String horaSaida = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Participante.COLUMN_NAME_HORA_SAIDA));

        txtNome.setText(nome);
        txtEmail.setText(email);
        if (horaEntrada != null &&  horaSaida != null) {
            txtHoraEntrada.setText(horaEntrada);
            txtHoraSaida.setText( horaSaida.toString());
        } else if (horaEntrada != null &&  horaSaida == null)
            txtHoraEntrada.setText(horaEntrada.toString());
        else if (horaEntrada == null &&  horaSaida != null)
            txtHoraSaida.setText( horaSaida.toString());

    }
}
