package br.com.senai.agenda.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.senai.agenda.modelo.Contato;

/**
 * Created by mac12 on 20/03/2018.
 */

public class ContatoConverter {
    public String converteParaJSON(List<Contato> contatos) {
        JSONStringer js = new JSONStringer();
        try {
            js.object().key("list").array().object().key("aluno").array();
            for (Contato contato : contatos) {
                js.object();
                js.key("nome").value(contato.getNome());

                js.endObject();
            }
            js.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return js.toString();
    }
}
