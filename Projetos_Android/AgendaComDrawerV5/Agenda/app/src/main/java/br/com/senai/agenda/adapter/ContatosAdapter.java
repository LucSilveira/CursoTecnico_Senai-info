package br.com.senai.agenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.senai.agenda.MainActivity;
import br.com.senai.agenda.R;
import br.com.senai.agenda.modelo.Contato;

/**
 * Created by adminLocal on 21/03/2018.
 */

public class ContatosAdapter extends BaseAdapter{


    private final List<Contato> contatos;
    private final Context context;

    public ContatosAdapter(Context context, List<Contato> contatos) {
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contato contato = contatos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_principal,null);
        TextView campoNome = view.findViewById(R.id.item_nome);
        campoNome.setText(contato.getNome());

        TextView campoEmail = view.findViewById(R.id.item_email);
        campoEmail.setText(contato.getEmail());

        TextView campoTelefone = view.findViewById(R.id.item_telefone);
        campoTelefone.setText(contato.getTelefone());

        TextView campoNota = view.findViewById(R.id.item_nota);
        campoNota.setText(String.valueOf(contato.getClassificacao()));

        TextView campoEnd = view.findViewById(R.id.item_endereco);
        if (campoEnd != null) {
            campoEnd.setText(contato.getEndereco());
        }

        return view;
    }
}
