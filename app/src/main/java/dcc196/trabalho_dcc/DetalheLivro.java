package dcc196.trabalho_dcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import dcc196.trabalho_dcc.model.Livro;
import dcc196.trabalho_dcc.model.Participante;
import dcc196.trabalho_dcc.model.Reserva;

public class DetalheLivro extends AppCompatActivity {

    private ListView lvLivros;
    private LivroAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_livro);

        lvLivros = (ListView) findViewById(R.id.lvLivros);
        livroAdapter = new LivroAdapter(getApplicationContext(), null);

        lvLivros.setAdapter(livroAdapter);
        livroAdapter.atualizar();

        //ArrayAdapter<Livro> adapter = new ArrayAdapter<>(
        //        this, android.R.layout.simple_list_item_1, LivroHelper.getInstance().listarLivros());

        //lvLivros.setAdapter(adapter);

        lvLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DetalheLivro.this, DadosLivro.class);
                intent.putExtra("Id_Livro", id);
                startActivity(intent);
            }
        });
    }
}
