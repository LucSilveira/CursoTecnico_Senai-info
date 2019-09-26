package fp.br.com.senai.fplocacoes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fp.br.com.senai.fplocacoes.R;
import fp.br.com.senai.fplocacoes.models.Transporte;

public class TransAdapter extends BaseAdapter{

    private final List<Transporte> trans;
    private final Context context;

    public TransAdapter(Context context, List<Transporte> trans){
        this.trans = trans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return trans.size();
    }

    @Override
    public Object getItem(int position) {
        return trans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return trans.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Transporte transporte = trans.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_principal, null);

        TextView campoModelo = view.findViewById(R.id.item_modelo);
        campoModelo.setText(transporte.getModelo());

        TextView campoPlaca = view.findViewById(R.id.item_placa);
        campoPlaca.setText(transporte.getPlaca());

        TextView campoMarca = view.findViewById(R.id.item_marca);
        campoMarca.setText(transporte.getMarca());

        TextView campoAno = view.findViewById(R.id.item_ano);
        campoAno.setText(transporte.getAno());

        TextView campoCor = view.findViewById(R.id.item_cor);
        campoCor.setText(transporte.getCor());

        TextView campoTipoCarro = view.findViewById(R.id.item_tipo);
        campoTipoCarro.setText(transporte.getTipoCarro());

        TextView campoLugares = view.findViewById(R.id.item_lugares);
        campoLugares.setText(transporte.getLugares());

        return view;
    }
}
