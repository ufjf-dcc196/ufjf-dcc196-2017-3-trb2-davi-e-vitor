package dcc196.trabalho_dcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dcc196.trabalho_dcc.model.DBHelper;
import dcc196.trabalho_dcc.model.Livro;

public class CadastroLivro extends AppCompatActivity {

    private Button btnListarLivros;
    private Button btnCadastrarLivro;
    private EditText campoTitulo;
    private EditText campoEditora;
    private EditText campoAnoPubliicacao;

    private DBHelper dbHelper;
    private LivroAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        btnListarLivros = (Button) findViewById(R.id.btnListarLivros);
        btnCadastrarLivro = (Button) findViewById(R.id.btnSalvarLivro);
        campoTitulo = (EditText) findViewById(R.id.txtCampoTitulo);
        campoEditora = (EditText) findViewById(R.id.txtCampoEditora);
        campoAnoPubliicacao = (EditText) findViewById(R.id.txtCampoAnoPublicacao);

        livroAdapter = new LivroAdapter(getApplicationContext(), null);
        dbHelper.getInstance(getApplicationContext());

        btnCadastrarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Livro livro = new Livro();
                livro.setTitulo(campoTitulo.getText().toString());
                livro.setEditora(campoEditora.getText().toString());
                livro.setAnoPlubicacao(campoAnoPubliicacao.getText().toString());

                livroAdapter.inserir(livro);

                Toast.makeText(getApplicationContext(), "Livro cadastrado", Toast.LENGTH_SHORT).show();
            }
        });

        btnListarLivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroLivro.this, DetalheLivro.class);
                startActivity(intent);
            }
        });
    }
}
