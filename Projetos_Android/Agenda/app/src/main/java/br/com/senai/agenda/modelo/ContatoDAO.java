package br.com.senai.agenda.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adminLocal on 23/02/2018.
 */

public class ContatoDAO extends SQLiteOpenHelper{

    public ContatoDAO(Context context) {
        super(context, "AgendaContatos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE contato(id INTEGER PRIMARY KEY, nome TEXT, telefone TEXT,endereco TEXT, email TEXT, facebook TEXT,caminhoFoto TEXT, classificacao REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS contato;";
        db.execSQL(sql);
    }

    public void inserir(Contato contato) {
       SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(contato);

       db.insert("contato",null,dados);
    }

    @NonNull
    private ContentValues getContentValues(Contato contato) {
        ContentValues dados = new ContentValues();
        dados.put("nome", contato.getNome());
        dados.put("telefone",contato.getTelefone());
        dados.put("endereco", contato.getEndereco());
        dados.put("email",contato.getEmail());
        dados.put("facebook", contato.getFace());
        dados.put("caminhoFoto", contato.getCaminhoFoto());
        dados.put("classificacao",contato.getClassificacao());
        return dados;
    }

    public List<Contato> buscaContato() {
        String sql = "SELECT * FROM Contato;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Contato> contatos = new ArrayList<Contato>();
        while (c.moveToNext()){
            Contato contato = new Contato();
            contato.setId(c.getLong(c.getColumnIndex("id")));
            contato.setTelefone(c.getString(c.getColumnIndex("telefone")));
            contato.setNome(c.getString(c.getColumnIndex("nome")));
            contato.setEndereco(c.getString(c.getColumnIndex("endereco")));
            contato.setEmail(c.getString(c.getColumnIndex("email")));
            contato.setFace(c.getString(c.getColumnIndex("facebook")));
            contato.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
            contato.setClassificacao(c.getDouble(c.getColumnIndex("classificacao")));
         contatos.add(contato);
        }
        c.close();
        return contatos;
    }

    public Contato localizar(Long id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT id, telefone, nome, endereco, email, facebook, caminhoFoto, classificacao FROM Contato WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        Contato contatoRetornado = new Contato();
        contatoRetornado.setId(cursor.getLong(0));
        contatoRetornado.setTelefone(cursor.getString(1));
        contatoRetornado.setNome(cursor.getString(2));
        contatoRetornado.setEndereco(cursor.getString(3));
        contatoRetornado.setEmail(cursor.getString(4));
        contatoRetornado.setFace(cursor.getString(5));
        contatoRetornado.setCaminhoFoto(cursor.getString(6));
        contatoRetornado.setClassificacao(cursor.getDouble(7));
        db.close();
        return contatoRetornado;
    }


    public void remover(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {contato.getId().toString()};

        db.delete("Contato", "id = ?", parametros);
    }

    public void alterar(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(contato);
        String[] parametros = {contato.getId().toString()};

        db.update("Contato",dados,"id = ?", parametros);

    }

    public List<Contato>  buscarNome(String nome){
        SQLiteDatabase db = getWritableDatabase();
        try{
            String sql = "select * from Contato where nome like'"+nome+"%'";
            Cursor c = db.rawQuery(sql, null);
            List<Contato> contatos = new ArrayList<Contato>();
            while (c.moveToNext()){
                Contato contato = new Contato();
                contato.setId(c.getLong(c.getColumnIndex("id")));
                contato.setTelefone(c.getString(c.getColumnIndex("telefone")));
                contato.setNome(c.getString(c.getColumnIndex("nome")));
                contato.setEndereco(c.getString(c.getColumnIndex("endereco")));
                contato.setEmail(c.getString(c.getColumnIndex("email")));
                contato.setFace(c.getString(c.getColumnIndex("facebook")));
                contato.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
                contato.setClassificacao(c.getDouble(c.getColumnIndex("classificacao")));
                contatos.add(contato);
            }
            c.close();
            return contatos;

        }finally {
            db.close();
        }
    }
}
