package br.com.senai.agenda;

import android.widget.EditText;
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

    public FormularioHelper(FormularioActivity formulario) {
        nome = formulario.findViewById(R.id.editNome);
        telefone = formulario.findViewById(R.id.editTelefone);
        endereco = formulario.findViewById(R.id.editEnd);
        email = formulario.findViewById(R.id.editEmail);
        face = formulario.findViewById(R.id.editFace);
        classificacao = formulario.findViewById(R.id.classificacao);
        contato = new Contato();
    }
    public Contato pegaContato() {

        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());
        contato.setEndereco(endereco.getText().toString());
        contato.setEmail(email.getText().toString());
        contato.setFace(face.getText().toString());
        contato.setClassificacao(Double.valueOf(classificacao.getProgress()));
        return contato;
    }
    public void preecherFormulario(Contato contato) {
        nome.setText(contato.getNome());
        telefone.setText(contato.getTelefone());
        endereco.setText(contato.getEndereco());
        email.setText(contato.getEmail());
        face.setText(contato.getFace());
        classificacao.setProgress(contato.getClassificacao().intValue());
        this.contato = contato;
    }
}
