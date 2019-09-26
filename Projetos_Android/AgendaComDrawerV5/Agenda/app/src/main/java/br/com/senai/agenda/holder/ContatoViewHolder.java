package br.com.senai.agenda.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.senai.agenda.FormularioActivity;
import br.com.senai.agenda.R;
import br.com.senai.agenda.adapter.ContatoRecyclerAdpater;
import br.com.senai.agenda.modelo.Contato;

/**
 * Created by adminLocal on 23/03/2018.
 */

public class ContatoViewHolder extends RecyclerView.ViewHolder{

    private final ContatoRecyclerAdpater adapter;
    private TextView campoNome;
    private TextView campoEmail;
    private TextView campoTelefone;
    private TextView campoNota;
    private TextView campoEnd;
    private Long contatoId;

    public ContatoViewHolder(View itemView, ContatoRecyclerAdpater adapter) {
        super(itemView);
        this.adapter = adapter;
        campoNome = itemView.findViewById(R.id.item_nome);
        campoEmail = itemView.findViewById(R.id.item_email);
        campoTelefone = itemView.findViewById(R.id.item_telefone);
        campoNota = itemView.findViewById(R.id.item_nota);
        campoEnd = itemView.findViewById(R.id.item_endereco);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Activity context = (Activity) view.getContext();
                final Intent intent = new Intent(context, FormularioActivity.class);
                intent.putExtra("contatoId", contatoId);
                context.startActivityForResult(intent,1);
            }
        });


    }

    public void preencher(Contato contato) {
        contatoId = contato.getId();
        campoNome.setText(contato.getNome());
        campoEmail.setText(contato.getEmail());
        campoTelefone.setText(contato.getTelefone());
        campoNota.setText(contato.getClassificacao().toString());
        if (campoEnd != null) {
            campoEnd.setText(contato.getEndereco());
        }
    }
}
