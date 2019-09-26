package br.com.senai.agenda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.senai.agenda.MainDrawerActivity;
import br.com.senai.agenda.R;
import br.com.senai.agenda.holder.ContatoViewHolder;
import br.com.senai.agenda.modelo.Contato;

/**
 * Created by adminLocal on 23/03/2018.
 */

public class ContatoRecyclerAdpater extends RecyclerView.Adapter{
    private final Context context;
    private final List<Contato> contatos;

    public ContatoRecyclerAdpater(Context context, List<Contato> contatos) {
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.lista_principal, parent,false);
        ContatoViewHolder holder = new ContatoViewHolder(view,this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContatoViewHolder nossoHolder = (ContatoViewHolder) holder;
        Contato contato = contatos.get(position);
        nossoHolder.preencher(contato);

    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }
}
