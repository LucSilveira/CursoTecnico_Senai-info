package astdevelopment.br.com.senai.st_development;

import android.widget.EditText;

/**
 * Created by 46923597811 on 14/03/2018.
 */

public class FormularioHelper {

    private EditText mensagemInserida;
    private Mensagem mensagem;

    public FormularioHelper(SendMensagem activity){
        mensagemInserida = activity.findViewById(R.id.setMensg);
        mensagem = new Mensagem();
    }

    public Mensagem pegarMensagem(){
        mensagem.setMensagem(mensagemInserida.getText().toString());
        return mensagem;
    }

    public void preencherFormulario(Mensagem mensagem){
        mensagemInserida.setText(mensagem.getMensagem());
        this.mensagem = mensagem;

    }

}
