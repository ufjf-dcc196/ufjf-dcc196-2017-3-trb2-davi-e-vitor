package dcc196.trabalho_dcc;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dcc196.trabalho_dcc.model.Livro;
import dcc196.trabalho_dcc.model.Participante;
import dcc196.trabalho_dcc.model.Reserva;

/**
 * Created by Davi on 22/10/2017.
 */

public class ReservaHelper {

    private SQLiteDatabase db;

    private static ReservaHelper rhs = null;
    private List<Reserva> reservas = new ArrayList<>();

    private ReservaHelper () {

    }

    private ReservaHelper (SQLiteDatabase db) {
        this.db = db;
        criarTabela();
    }

    private void criarTabela() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS reserva (idParticipante INTEGER, idLivro INTEGER)");
            db.execSQL("ALTER TABLE reserva ADD PRIMARY KEY (idParticipante, idLivro)");
            db.execSQL("ALTER TABLE reserva ADD FOREING KEY (idParticipante) REFERENCES participante(id)");
            db.execSQL("ALTER TABLE reserva ADD FOREING KEY (idLivro) REFERENCES livro(id)");
        } catch (Exception e) {
            Log.e("Reverva", "Erro ao Criar a Tabela");
            Log.e("Reserve", e.getMessage());
        }
    }

    public void criar(Reserva reserva) {
        /*try {
            db.execSQL("INSERT INTO reserva (idParticipante, idLivro) VALUES ('" +
                    reserva.getParticipante().getId() +"', '" +
                    reserva.getLivro().getId()+")");
        } catch (Exception e) {
            Log.e("Reserva", "Erro ao inserir uma Reserva");
            Log.e("Reserva", e.getLocalizedMessage());
        }*/
    }

    public List<Reserva> listarReservas() {
        return reservas;
    }

    public List<Participante> reservaramLivro(Livro livro) {
        List<Participante> r = new ArrayList<>();

        /*try {
            Cursor db.rawQuery("SELECT idParticipante, idLivro FROM reserva WHERE idLivro = "+ livro.getId(), null);
        } catch (Exception e) {
            Log.e("Reserva", "Erro ao selecionar participantes que reservaram o livro" + livro.getTitulo());
            Log.e("Reserva", e.getLocalizedMessage());
        }*/
        /*for (Reserva rs : reservas)
              {
            if (rs.getLivro().equals(livro))
                r.add(rs.getParticipante());
        }*/


        return r;
    }

    public static ReservaHelper getInstance() {
        if (rhs == null) {
            rhs = new ReservaHelper();
        }
        return rhs;
    }
}
