package astdevelopment.br.com.senai.st_development;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 46923597811 on 14/03/2018.
 */

public class StDao extends SQLiteOpenHelper{

    public StDao (Context context){
        super(context, "Listas", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Mensagem(id integer primary key, mensagem text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "DROP TABLE IF EXISTS Mensagem";
        db.execSQL(sql);
    }

    public void inserir (Mensagem mensagem){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(mensagem);
        db.insert("Mensagem", null, dados);
    }

    @NonNull
    private ContentValues getContentValues(Mensagem mensagem){
        ContentValues dados = new ContentValues();
        dados.put("mensagem", mensagem.getMensagem());
        return dados;
    }
    public List<Mensagem> buscaMensagens(){
        String sql = "SELECT * FROM Mensagem";
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null);
        List<Mensagem> mensagens = new ArrayList<Mensagem>();

        while (c.moveToNext()){
            Mensagem mensagem = new Mensagem();
            mensagem.setId(c.getLong(c.getColumnIndex("id")));
            mensagem.setMensagem(c.getString(c.getColumnIndex("mensagem")));
            mensagens.add(mensagem);
        }
        return mensagens;
    }

    public void remover(Mensagem mensagem){
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {
                String.valueOf(mensagem.getId())
        };
        db.delete("Mensagem", "id = ?", parametros);
    }

    public void editar(Mensagem mensagem){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues dados = getContentValues(mensagem);
        String[] parametros = {
                mensagem.getId().toString()
        };
        db.update("Mensagem", dados, "id = ?", parametros);
    }
}