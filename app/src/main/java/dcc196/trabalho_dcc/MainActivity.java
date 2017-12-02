package dcc196.trabalho_dcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;

import dcc196.trabalho_dcc.model.DBHelper;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastrarParticipante;
    private Button btnCadastrarReserva;
    private Button btnCadastrarLivro;
    private ListView lvParticipantes;

    private DBHelper dbHelper;
    private LivroAdapter livroAdapter;

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

        livroAdapter = new LivroAdapter(getApplicationContext(), null);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date hora;  // Ou qualquer outra forma que tem
        //String dataFormatada = sdf.format(hora);

        lvParticipantes.setAdapter(livroAdapter);
        livroAdapter.atualizar();

        dbHelper.getInstance(getApplicationContext());
        //livroAdapter.inserirAleatorio();

        livroAdapter.atualizar();

        /*ParticipanteHelper p = ParticipanteHelper.getInstance();
        LivroHelper l = LivroHelper.getInstance();
        if (p != null) {
        //Carregamento de dados
            Participante p1 = new Participante();
            p1.setNomeCompleto("Davi de Almeida");
            p1.setEmail("davi.c@gmail.com");
            p.criar(p1);

            Participante p2 = new Participante();
            p2.setNomeCompleto("Ricardo Tuboly");
            p2.setEmail("ricardo.c@gmail.com");
            p.criar(p2);

            if (l != null) {
                Livro l1 = new Livro();
                l1.setTitulo("Senhor dos Anéis");
                l1.setEditora("Almenara");
                l1.setAnoPlubicacao("2000");
                l.criar(l1);

                Livro l2 = new Livro();
                l2.setTitulo("Star Wars");
                l2.setEditora("Yoda");
                l2.setAnoPlubicacao("1990");
                l.criar(l2);
            }



            ArrayAdapter<Participante> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, ParticipanteHelper.getInstance().listarParticipantes());

            lvParticipantes.setAdapter(adapter);


        }*/

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
                //Log.d("TAG", String.valueOf(ParticipanteHelper.getInstance().listarParticipantes().size()));
                Intent intent = new Intent(MainActivity.this, CadastroParticipante.class);
                startActivity(intent);
            }
        });

        lvParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                /*Participante p = ParticipanteHelper.getInstance().listarParticipantes().get(position);

                if (p.getHoraEntrada() == null) {
                    p.setHoraEntrada(Calendar.getInstance().getTime());
                    Toast.makeText(getApplicationContext(), "HoraEntrada atualizada", Toast.LENGTH_SHORT).show();
                } else if (p.getHoraSaida() == null) {
                    p.setHoraSaida(Calendar.getInstance().getTime());
                    Toast.makeText(getApplicationContext(), "HoraSaida atualizada", Toast.LENGTH_SHORT).show();
                } else {
                    p.setHoraEntrada(null);
                    p.setHoraSaida(null);
                    Toast.makeText(getApplicationContext(), "Horas de entrada e saidas resetadas", Toast.LENGTH_SHORT).show();
                }*/
                return true;
            }
        });

        lvParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //Participante p = ParticipanteHelper.getInstance().listarParticipantes().get(position);
                Intent intent = new Intent(MainActivity.this, DetalheParticipante.class);
                //intent.putExtra("Participante", p);
                startActivity(intent);
            }
        });
    }
}
