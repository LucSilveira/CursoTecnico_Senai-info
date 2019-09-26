package livros.br.com.senai.livros.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 46923597811 on 04/04/2018.
 */

public class LivroDao extends SQLiteOpenHelper{

    public LivroDao(Context context){
        super(context, "Livro", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE livro(id INTEGER PRIMARY KEY, nome TEXT, autor TEXT, genero TEXT, editora TEXT" +
                ", ano_producao TEXT";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Livro";
        db.execSQL(sql);
    }

    public void inserir(Livro livro){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(livro);

        db.insert("livro", null, dados);
    }

    @NonNull
    private ContentValues getContentValues(Livro livro) {
        ContentValues dados = new ContentValues();
        dados.put("nome", livro.getNome());
        dados.put("autor", livro.getAutor());
        dados.put("genero", livro.getGenero());
        dados.put("editora", livro.getEditora());
        dados.put("ano_producao", livro.getAno_produção().toString());
        return dados;
    }

    public List<Livro> buscaLivro(){
        String sql = "SELECT * FROM Livro;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c  = db.rawQuery(sql, null);
        List<Livro> livros = new ArrayList<Livro>();
        while (c.moveToNext()){
            Livro livro = new Livro();
            livro.setId(c.getLong(c.getColumnIndex("id")));
            livro.setNome(c.getString(c.getColumnIndex("nome")));
            livro.setAutor(c.getString(c.getColumnIndex("autor")));
            livro.setGenero(c.getString(c.getColumnIndex("genero")));
            livro.setEditora(c.getString(c.getColumnIndex("editora")));
            livro.setAno_produção(c.getString(c.getColumnIndex("ano_producao")));
            livros.add(livro);
        }
        c.close();
        return livros;
    }

    public Livro localizar(Long livroId) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * from Livro WHERE id = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(livroId)});
        c.moveToFirst();
        Livro livroRetornado = new Livro();
        livroRetornado.setId(c.getLong(c.getColumnIndex("id")));
        livroRetornado.setNome(c.getString(c.getColumnIndex("nome")));
        livroRetornado.setAutor(c.getString(c.getColumnIndex("autor")));
        livroRetornado.setGenero(c.getString(c.getColumnIndex("genero")));
        livroRetornado.setEditora(c.getString(c.getColumnIndex("editora")));
        livroRetornado.setAno_produção(c.getString(c.getColumnIndex("ano_producao")));
        db.close();
        return livroRetornado;
    }

    public void deletar (Livro livro){
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {String.valueOf(livro.getId())};
        db.delete("Livro", "id = ?", parametros);
    }

    public void alterar(Livro livro){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(livro);
        String[] parametros = {livro.getId().toString()};
        db.update("Livro", dados, "id = ?", parametros);
    }
}
