package dcc196.trabalho_dcc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import dcc196.trabalho_dcc.model.Participante;

public class DetalheParticipante extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtEmail;
    private TextView horaEntrada;
    private TextView horaSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_participante);

        txtNome = (TextView) findViewById(R.id.txtNome);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        horaEntrada = (TextView) findViewById(R.id.txtHoraEntrada);
        horaSaida = (TextView) findViewById(R.id.txtHoraSaida);

        Participante p = (Participante) getIntent().getSerializableExtra("Participante");

        txtNome.setText(p.getNomeCompleto().toString());
        txtEmail.setText(p.getEmail().toString());
        if (p.getHoraEntrada() != null && p.getHoraSaida() != null) {
            horaEntrada.setText(p.getHoraEntrada().toString());
            horaSaida.setText(p.getHoraSaida().toString());
        } else if (p.getHoraEntrada() != null && p.getHoraSaida() == null)
            horaEntrada.setText(p.getHoraEntrada().toString());
        else if (p.getHoraEntrada() == null && p.getHoraSaida() != null)
            horaSaida.setText(p.getHoraSaida().toString());

    }
}
