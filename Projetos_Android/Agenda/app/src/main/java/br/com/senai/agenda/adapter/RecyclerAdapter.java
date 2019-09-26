package br.com.senai.agenda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.senai.agenda.R;
import br.com.senai.agenda.holder.ContatosViewHolder;
import br.com.senai.agenda.modelo.Contato;

/**
 * Created by mac12 on 22/03/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {
    private final List<Contato> contatos;
    private Context context;
    private ArrayList<Contato> contatoLista;

    public RecyclerAdapter(Context context, List<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_de_itens, parent, false);
        ContatosViewHolder holder = new ContatosViewHolder(view, this);
        return  holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContatosViewHolder nossoHolder = (ContatosViewHolder) holder;
        Contato contato = contatos.get(position);
        nossoHolder.preencher(contato);

    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public void filtarPorNome(String valorPesquisado) {
        valorPesquisado = valorPesquisado.toLowerCase(Locale.getDefault());
        contatos.clear();
        if (valorPesquisado.length() == 0){
            contatos.addAll(contatoLista);
        }else
        {
            for (Contato c: contatoLista){
                if (c.getNome().toLowerCase(Locale.getDefault()).contains(valorPesquisado)){
                    contatos.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }
    public void remove(int position) {
        contatos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}
