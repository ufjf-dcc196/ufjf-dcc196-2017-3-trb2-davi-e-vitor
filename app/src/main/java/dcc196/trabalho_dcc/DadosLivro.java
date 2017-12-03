package dcc196.trabalho_dcc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dcc196.trabalho_dcc.model.DBHelper;
import dcc196.trabalho_dcc.model.Livro;
import dcc196.trabalho_dcc.model.Participante;

public class DadosLivro extends AppCompatActivity {

    private TextView tvTitulo;
    private TextView tvEditora;
    private TextView tvAnoPublicacao;
    private ListView lvParticipantesReservaram;

    private ParticipanteAdapter participanteAdapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_livro);

        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tvEditora = (TextView) findViewById(R.id.tvEditora);
        tvAnoPublicacao = (TextView) findViewById(R.id.tvAnoPublicacao);
        lvParticipantesReservaram = (ListView) findViewById(R.id.lvParticipantesReservaram);

        Intent i = getIntent();
        long id = i.getLongExtra("Id_Livro", 0);

        dbHelper = DBHelper.getInstance(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] visao = {
                DatabaseContract.Livro._ID,
                DatabaseContract.Livro.COLUMN_NAME_TITULO,
                DatabaseContract.Livro.COLUMN_NAME_EDITORA,
                DatabaseContract.Livro.COLUMN_NAME_ANO,
        };
        String selecao = DatabaseContract.Livro._ID + " > ?";
        String[] args = {Long.toString(id)};
        Cursor c = db.query(DatabaseContract.Livro.TABLE_NAME, visao, selecao, args, null, null, null);

        c.moveToFirst();

        String titulo = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Livro.COLUMN_NAME_TITULO));
        String editora = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Livro.COLUMN_NAME_EDITORA));
        String ano = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Livro.COLUMN_NAME_ANO));

        String rawQuery = "SELECT * FROM " + DatabaseContract.Reserva.TABLE_NAME + " tr" +
                " INNER JOIN " + DatabaseContract.Livro.TABLE_NAME + " tl ON tr." + DatabaseContract.Reserva.COLUMN_NAME_IDLIVRO + " = tl." +
                DatabaseContract.Livro._ID + " INNER JOIN " + DatabaseContract.Participante.TABLE_NAME + " tp ON tr." +
                DatabaseContract.Reserva.COLUMN_NAME_IDPARTICIPANTE + " = tp." + DatabaseContract.Participante._ID;

        tvTitulo.setText(titulo);
        tvEditora.setText(editora);
        tvAnoPublicacao.setText(ano);

        participanteAdapter = new ParticipanteAdapter(getApplicationContext(), null);
        lvParticipantesReservaram.setAdapter(participanteAdapter);
        participanteAdapter.atualizar(rawQuery);
    }
}
