package dcc196.trabalho_dcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import dcc196.trabalho_dcc.model.Livro;
import dcc196.trabalho_dcc.model.Participante;
import dcc196.trabalho_dcc.model.Reserva;


public class CadastroReserva extends AppCompatActivity {

    private Button btnReservarLivro;
    private AutoCompleteTextView autoCompleteParticipantes;
    private AutoCompleteTextView autoCompleteLivros;
    private ParticipanteHelper ph;
    private LivroHelper lh;
    private List<Participante> participantes;

    private Participante p;
    private Livro l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_reserva);

        //ph = ParticipanteHelper.getInstance();
        //lh = LivroHelper.getInstance();

        btnReservarLivro = (Button) findViewById(R.id.btnReservarLivro);
        autoCompleteParticipantes = (AutoCompleteTextView) findViewById(R.id.autoCompleteParticipantes);
        autoCompleteLivros = (AutoCompleteTextView) findViewById(R.id.autoCompleteLivros);

        //participantes = ParticipanteHelper.getInstance().listarParticipantes();
        final ArrayAdapter<Participante> adpParticipante = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, participantes);
        autoCompleteParticipantes.setAdapter(adpParticipante);

        List<Livro> livros = lh.listarLivros();
        final ArrayAdapter<Livro> adpLivro = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, livros);
        autoCompleteLivros.setAdapter(adpLivro);

        autoCompleteParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p = adpParticipante.getItem(position);
            }
        });

        autoCompleteLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                l = adpLivro.getItem(position);
            }
        });

        btnReservarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p != null && l != null) {
                    Reserva r = new Reserva();
                    r.setParticipante(p);
                    r.setLivro(l);
                    ReservaHelper.getInstance().criar(r);
                    Toast.makeText(getApplicationContext(), "Reserva cadastrada", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
