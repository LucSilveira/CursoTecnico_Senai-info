package fp.br.com.senai.fplocacoes.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fp.br.com.senai.fplocacoes.models.Transporte;

/**
 * Created by 46923597811 on 25/04/2018.
 */

public class TranspDao extends SQLiteOpenHelper{

    public TranspDao(Context context){ super(context, "Transportes", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Veiculo(id INTEGER PRIMARY KEY," +
                "modelo TEXT, placa TEXT, marca TEXT, ano TEXT, cor TEXT, tipoCarro TEXT, lugares TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Transportes";
        db.execSQL(sql);
    }

    public void inserir(Transporte transp){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(transp);
        db.insert("Veiculo", null, dados);
    }

    @NonNull
    private ContentValues getContentValues(Transporte transp){
        ContentValues dados = new ContentValues();
        dados.put("modelo", transp.getModelo());
        dados.put("placa", transp.getPlaca());
        dados.put("marca", transp.getMarca());
        dados.put("ano", transp.getAno());
        dados.put("cor", transp.getCor());
        dados.put("tipoCarro", transp.getTipoCarro());
        dados.put("lugares", transp.getLugares());
        return dados;
    }

    public List<Transporte> buscaTransporte(){
        String sql = "SELECT * FROM Transporte";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Transporte> trans = new ArrayList<Transporte>();
        while(c.moveToNext()){
            Transporte tran = new Transporte();
            tran.setId(c.getLong(c.getColumnIndex("id")));
            tran.setModelo(c.getString(c.getColumnIndex("modelo")));
            tran.setPlaca(c.getString(c.getColumnIndex("placa")));
            tran.setMarca(c.getString(c.getColumnIndex("marca")));
            tran.setAno(c.getString(c.getColumnIndex("ano")));
            tran.setCor(c.getString(c.getColumnIndex("cor")));
            tran.setTipoCarro(c.getString(c.getColumnIndex("tipoCarro")));
            tran.setLugares(c.getString(c.getColumnIndex("lugares")));
            trans.add(tran);
        }
        c.close();
        return trans;
    }

    public Transporte localizar(Long transId){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM Veiculo WHERE id = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(transId)});
        c.moveToFirst();
        Transporte transRetornado = new Transporte();
        transRetornado.setId(c.getLong(c.getColumnIndex("id")));
        transRetornado.setModelo(c.getString(c.getColumnIndex("modelo")));
        transRetornado.setPlaca(c.getString(c.getColumnIndex("placa")));
        transRetornado.setMarca(c.getString(c.getColumnIndex("marca")));
        transRetornado.setAno(c.getString(c.getColumnIndex("ano")));
        transRetornado.setCor(c.getString(c.getColumnIndex("cor")));
        transRetornado.setTipoCarro(c.getString(c.getColumnIndex("tipoCarro")));
        transRetornado.setLugares(c.getString(c.getColumnIndex("lugares")));
        db.close();
        return transRetornado;
    }

    public void deletar (Transporte trans){
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {String.valueOf(trans.getId())};
        db.delete("Veiculo", "id = ?", parametros);
    }

    public void alterar(Transporte trans){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(trans);
        String[] parametros = {trans.getId().toString()};
        db.update("Veiculo", dados, "id = ?", parametros);
    }

}
