package dcc196.trabalho_dcc;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import dcc196.trabalho_dcc.model.DBHelper;
import dcc196.trabalho_dcc.model.Livro;
import dcc196.trabalho_dcc.model.Participante;
import dcc196.trabalho_dcc.model.Reserva;


public class CadastroReserva extends AppCompatActivity {

    private Button btnReservarLivro;
    private AutoCompleteTextView autoCompleteParticipantes;
    private AutoCompleteTextView autoCompleteLivros;

    LivroAdapter livroAdapter;
    ParticipanteAdapter participanteAdapter;

    private Long idParticipante;
    private Long idLivro;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_reserva);

        btnReservarLivro = (Button) findViewById(R.id.btnReservarLivro);
        autoCompleteParticipantes = (AutoCompleteTextView) findViewById(R.id.autoCompleteParticipantes);
        autoCompleteLivros = (AutoCompleteTextView) findViewById(R.id.autoCompleteLivros);

        participanteAdapter = new ParticipanteAdapter(getApplicationContext(), null);
        livroAdapter = new LivroAdapter(getApplicationContext(), null);

        autoCompleteParticipantes.setAdapter(participanteAdapter);
        participanteAdapter.atualizar();

        autoCompleteLivros.setAdapter(livroAdapter);
        livroAdapter.atualizar();

        autoCompleteParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), Long.toString(id), Toast.LENGTH_SHORT).show();
                idParticipante = id;
            }
        });

        autoCompleteLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), Long.toString(id), Toast.LENGTH_SHORT).show();
                idLivro = id;
            }
        });

        btnReservarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idParticipante != null && idLivro != null) {
                    dbHelper = DBHelper.getInstance(getApplicationContext());
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put(DatabaseContract.Reserva.COLUMN_NAME_IDPARTICIPANTE, idParticipante);
                    values.put(DatabaseContract.Reserva.COLUMN_NAME_IDLIVRO, idLivro);
                    long id = db.insert(DatabaseContract.Reserva.TABLE_NAME, null, values);

                    Toast.makeText(getApplicationContext(), "Reserva cadastrada", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
