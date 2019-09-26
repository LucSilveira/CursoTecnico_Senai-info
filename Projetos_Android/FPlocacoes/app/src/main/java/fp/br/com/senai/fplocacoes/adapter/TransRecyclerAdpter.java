package fp.br.com.senai.fplocacoes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fp.br.com.senai.fplocacoes.R;
import fp.br.com.senai.fplocacoes.holder.TransViewHolder;
import fp.br.com.senai.fplocacoes.models.Transporte;

public class TransRecyclerAdpter extends RecyclerView.Adapter{

    private final Context context;
    private final List<Transporte> trans;


    public TransRecyclerAdpter(Context context, List<Transporte> trans){
        this.context = context;
        this.trans = trans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista_principal, parent, false);
        TransViewHolder holder = new TransViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TransViewHolder nossoHolder = (TransViewHolder) holder;
        Transporte transp = trans.get(position);
        nossoHolder.preencher(transp);
    }

    @Override
    public int getItemCount() {
        return trans.size();
    }
}
