package dcc196.trabalho_dcc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dcc196.trabalho_dcc.model.Livro;
import dcc196.trabalho_dcc.model.Participante;

/**
 * Created by Davi on 22/10/2017.
 */

public class ParticipanteHelper {

    private SQLiteDatabase db;

    private ParticipanteHelper (SQLiteDatabase db) {
        this.db = db;
        criaTabela();
    }

    private void criaTabela() {

        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS participante (id INTEGER PRIMARY KEY AUTOINCREMENT, nomeCompleto VARCHAR, email VARCHAR, horaEntrada TEXT, horaSaida TEXT)");
        }catch (Exception e){
            Log.e("Participante", "Erro ao criar a tabela!");
            Log.e("Participante", e.getMessage());
        }
    }

    public List<Participante> listarParticipantes() {

        Cursor resultado = db.rawQuery("SELECT nomeCompleto, email, horaEntrada, horaEntrada FROM participante", null);
        List<Participante> participantes = new ArrayList<>();
        resultado.moveToPosition(-1);
        while (resultado.moveToNext()){
            Participante p = new Participante();
            p.setNomeCompleto(resultado.getString(0));
            p.setEmail(resultado.getString(1));
            p.setHoraEntrada(resultado.getString(2));
            p.setHoraSaida(resultado.getString(3));
            participantes.add(p);
        }
        return participantes;
    }

    public void criar(Participante p) {

        try{
            db.execSQL("INSERT INTO livro (nomeCompleto, email, horaEntrada, horaEntrada) VALUES ('" +
                    p.getNomeCompleto()+"', '" +
                    p.getEmail()+"', '" +
                    p.getHoraEntrada()+"', " +
                    p.getHoraSaida()+")");
            //db.execSQL(String.format("INSERT INTO livro (titulo, autor, editora, ano, preco) VALUES ('%s','%s','%s',%d,%f)", l.getTitulo(), l.getAutor(), l.getEditora(), l.getAno(), l.getPreco()));

        }catch(Exception e){
            Log.e("Participante", "Erro ao inserir um Participante");
            Log.e("Participante", e.getLocalizedMessage());
        };
    }

}
