package dcc196.trabalho_dcc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dcc196.trabalho_dcc.model.Participante;

public class CadastroParticipante extends AppCompatActivity {

    private EditText edtNomeParticipante;
    private EditText edtEmailParticipante;
    private Button btnSalvarParticipante;
    //private ParticipanteHelper ph = ParticipanteHelper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);

        edtNomeParticipante = (EditText) findViewById(R.id.edtNomeParticipante);
        edtEmailParticipante = (EditText) findViewById(R.id.edtEmailParticipante);
        btnSalvarParticipante = (Button) findViewById(R.id.btnSalvarParticipante);

        btnSalvarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Participante participante = new Participante();
                participante.setNomeCompleto(edtNomeParticipante.getText().toString());
                participante.setEmail(edtEmailParticipante.getText().toString());
        //        ph.criar(participante);
                Toast.makeText(getApplicationContext(), "Participante cadastrado", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
