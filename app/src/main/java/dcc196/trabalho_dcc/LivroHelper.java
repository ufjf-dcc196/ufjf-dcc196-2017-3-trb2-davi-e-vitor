package dcc196.trabalho_dcc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dcc196.trabalho_dcc.model.Livro;

/**
 * Created by Davi on 22/10/2017.
 */

public class LivroHelper {

    private SQLiteDatabase db;

    private LivroHelper (SQLiteDatabase db) {
        this.db = db;
        criaTabela();
    }

    private void criaTabela() {

        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS livro (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR, editora VARCHAR, anoPublicacao VARCHAR)");
        }catch (Exception e){
            Log.e("Livro", "Erro ao criar a tabela!");
            Log.e("Livro", e.getMessage());
        }
    }

    public void criar(Livro l) {
        try{
            db.execSQL("INSERT INTO livro (titulo, autor, editora, ano, preco) VALUES ('" +
                    l.getTitulo()+"', '" +
                    l.getEditora()+"', " +
                    l.getAnoPlubicacao()+")");
            //db.execSQL(String.format("INSERT INTO livro (titulo, autor, editora, ano, preco) VALUES ('%s','%s','%s',%d,%f)", l.getTitulo(), l.getAutor(), l.getEditora(), l.getAno(), l.getPreco()));

        }catch(Exception e){
            Log.e("Livro", "Erro ao inserir um livro");
            Log.e("Livro", e.getLocalizedMessage());
        };
    }

    public List<Livro> listarLivros() {
        Cursor resultado = db.rawQuery("SELECT titulo, editora, anoPublicacao FROM livro", null);
        List<Livro> livros = new ArrayList<>();
        resultado.moveToPosition(-1);
        while (resultado.moveToNext()){
            Livro l = new Livro();
            l.setTitulo(resultado.getString(0));
            l.setEditora(resultado.getString(1));
            l.setAnoPlubicacao(resultado.getString(2));
            livros.add(l);
        }
        return livros;
    }

}
