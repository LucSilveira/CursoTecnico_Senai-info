package br.com.senai.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.senai.agenda.modelo.Contato;

/**
 * Created by adminLocal on 23/02/2018.
 */

public class FormularioHelper {
    public EditText nome;
    public EditText telefone;
    public EditText endereco;
    public EditText email;
    public EditText face;
    public RatingBar classificacao;
    public Contato contato;
    public ImageView fotoFormulario;
    public Button botaoFoto;

    public FormularioHelper(FormularioActivity formulario) {
        contato = new Contato();
        nome = formulario.findViewById(R.id.editNome);
        telefone = formulario.findViewById(R.id.editTelefone);
        endereco = formulario.findViewById(R.id.editEnd);
        email = formulario.findViewById(R.id.editEmail);
        face = formulario.findViewById(R.id.editFace);
        classificacao = formulario.findViewById(R.id.classificacao);
        fotoFormulario = formulario.findViewById(R.id.formulario_foto);
        botaoFoto = formulario.findViewById(R.id.formulario_botao_foto);
    }

    public Contato pegaContato() {
        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());
        contato.setEndereco(endereco.getText().toString());
        contato.setEmail(email.getText().toString());
        contato.setFace(face.getText().toString());
        contato.setCaminhoFoto((String) fotoFormulario.getTag());
        contato.setClassificacao(Double.valueOf(classificacao.getProgress()));

        return contato;
    }

    public void preecheFormulario(Contato contato) {
        nome.setText(contato.getNome());
        telefone.setText(contato.getTelefone());
        endereco.setText(contato.getEndereco());
        email.setText(contato.getEmail());
        face.setText(contato.getFace());
        carregaImagem(contato.getCaminhoFoto());
        classificacao.setProgress(contato.getClassificacao().intValue());
        this.contato = contato;
    }

    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto !=null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            fotoFormulario.setImageBitmap(bitmapReduzido);
            fotoFormulario.setTag(caminhoFoto);
        }
    }
}
