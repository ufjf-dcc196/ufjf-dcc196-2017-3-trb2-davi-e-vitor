package dcc196.trabalho_dcc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.text.SimpleDateFormat;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dcc196.trabalho_dcc.model.DBHelper;

public class MainActivity extends AppCompatActivity {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US);

    private Button btnCadastrarParticipante;
    private Button btnCadastrarReserva;
    private Button btnCadastrarLivro;
    private ListView lvParticipantes;

    private DBHelper dbHelper;
    private ParticipanteAdapter participanteAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        BaseAdapter adapter = (BaseAdapter) lvParticipantes.getAdapter();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrarParticipante = (Button) findViewById(R.id.btnCadastrarParticipante);
        btnCadastrarReserva = (Button) findViewById(R.id.btnCadastrarReserva);
        btnCadastrarLivro = (Button) findViewById(R.id.btnCadastrarLivro);
        lvParticipantes = (ListView) findViewById(R.id.lvParticipantes);

        participanteAdapter = new ParticipanteAdapter(getApplicationContext(), null);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date hora;  // Ou qualquer outra forma que tem
        //String dataFormatada = sdf.format(hora);

        lvParticipantes.setAdapter(participanteAdapter);
        participanteAdapter.atualizar();

        dbHelper.getInstance(getApplicationContext());
        //livroAdapter.inserirAleatorio();

        participanteAdapter.atualizar();


        btnCadastrarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroReserva.class);
                startActivity(intent);
            }


        });

        btnCadastrarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroLivro.class);
                startActivity(intent);
            }
        });

        btnCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroParticipante.class);
                startActivity(intent);
            }
        });

        lvParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dbHelper = DBHelper.getInstance(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                String [] args = {Long.toString(id)};
                Cursor c = db.query(DatabaseContract.Participante.TABLE_NAME, null, Long.toString(id), null, null, null, null);
                c.moveToFirst();
                String horario_entrada = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Participante.COLUMN_NAME_HORA_ENTRADA));
                String horario_saida = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Participante.COLUMN_NAME_HORA_SAIDA));

                String horaAtual = dateFormat.format(Calendar.getInstance().getTime());
                ContentValues values = new ContentValues();
                if (horario_entrada == null) {
                    values.put("horaentrada", horaAtual);
                    db.update(DatabaseContract.Participante.TABLE_NAME, values, "_id ="+id, null);
                    Toast.makeText(getApplicationContext(), "HoraEntrada atualizada", Toast.LENGTH_SHORT).show();
                } else if (horario_saida == null) {
                    values.put("horasaida", horaAtual);
                    db.update(DatabaseContract.Participante.TABLE_NAME, values, "_id ="+id, null);
                    Toast.makeText(getApplicationContext(), "HoraSaida atualizada", Toast.LENGTH_SHORT).show();
                } else {
                    horaAtual = null;
                    values.put("horaentrada", horaAtual);
                    values.put("horasaida", horaAtual);
                    db.update(DatabaseContract.Participante.TABLE_NAME, values, "_id="+id, null);
                    Toast.makeText(getApplicationContext(), "Horas de entrada e saidas resetadas", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        lvParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetalheParticipante.class);
                intent.putExtra("ID_PARTICIPANTE", id);
                startActivity(intent);
            }
        });
    }
}
