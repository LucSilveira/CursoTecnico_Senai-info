package fp.br.com.senai.fplocacoes.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fp.br.com.senai.fplocacoes.MainActivityCars;
import fp.br.com.senai.fplocacoes.R;
import fp.br.com.senai.fplocacoes.adapter.TransRecyclerAdpter;
import fp.br.com.senai.fplocacoes.models.Transporte;

public class TransViewHolder extends RecyclerView.ViewHolder{

    private final TransRecyclerAdpter adapter;
    private TextView campoModelo;
    private TextView campoPlaca;
    private TextView campoMarca;
    private TextView campoAno;
    private TextView campoCor;
    private TextView campoTipoCarro;
    private TextView campoLugares;
    private Long transpId;

    public TransViewHolder(View itemView, TransRecyclerAdpter adapter){
        super(itemView);
        this.adapter = adapter;
        campoModelo = itemView.findViewById(R.id.item_modelo);
        campoPlaca = itemView.findViewById(R.id.item_placa);
        campoMarca = itemView.findViewById(R.id.item_marca);
        campoAno = itemView.findViewById(R.id.item_ano);
        campoCor = itemView.findViewById(R.id.item_cor);
        campoTipoCarro = itemView.findViewById(R.id.item_tipo);
        campoLugares = itemView.findViewById(R.id.item_lugares);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Activity context = (Activity) view.getContext();
                final Intent intent = new Intent(context, MainActivityCars.class);
                intent.putExtra("transpId", transpId);
                context.startActivityForResult(intent, 1);
            }
        });
    }

    public void preencher(Transporte trans){

        transpId = trans.getId();
        campoModelo.setText(trans.getModelo());
        campoPlaca.setText(trans.getPlaca());
        campoMarca.setText(trans.getMarca());
        campoAno.setText(trans.getAno());
        campoCor.setText(trans.getCor());
        campoTipoCarro.setText(trans.getTipoCarro());
        campoLugares.setText(trans.getLugares());

    }

}
