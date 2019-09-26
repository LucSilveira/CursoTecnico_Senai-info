package br.com.senai.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.senai.agenda.MainActivity;
import br.com.senai.agenda.R;
import br.com.senai.agenda.modelo.Contato;

/**
 * Created by mac12 on 20/03/2018.
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

        return contatos.size();// retorna quantos item tem na lista
    }

    @Override
    public Object getItem(int i) {
        return contatos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contatos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Contato contato = contatos.get(i);
        View visao = view;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (view == null){
            visao = inflater.inflate(R.layout.lista_de_itens, viewGroup, false);
        }


        TextView campoNome = visao.findViewById(R.id.item_nome);
        TextView campoTelefone = visao.findViewById(R.id.item_telefone);
        ImageView campoFoto = visao.findViewById(R.id.item_foto);
        TextView campoNota = visao.findViewById(R.id.item_nota);

        campoNome.setText(contato.getNome());
        campoTelefone.setText(contato.getEmail());
        String caminhoFoto = contato.getCaminhoFoto();

        if (caminhoFoto !=null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);

        }
        campoNota.setText(String.valueOf(contato.getClassificacao()));


        return visao;
    }
}
