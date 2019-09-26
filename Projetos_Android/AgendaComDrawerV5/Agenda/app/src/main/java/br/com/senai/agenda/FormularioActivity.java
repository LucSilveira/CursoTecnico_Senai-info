package br.com.senai.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.senai.agenda.modelo.Contato;
import br.com.senai.agenda.modelo.ContatoDAO;

public class FormularioActivity extends AppCompatActivity {

    private Button botaoCadastrar;
    private ContatoDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        botaoCadastrar = findViewById(R.id.btnCadastrar);
        dao = new ContatoDAO(this);

        final FormularioHelper helper = new FormularioHelper(this);

       /* Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");
        */

       Bundle extras = getIntent().getExtras();
       Long contatoId = (extras != null)? extras.getLong("contatoId"): null;

        if (contatoId == null){
            Contato contato = new Contato();
        }else {
            Contato contato = dao.localizar(contatoId);
            helper.preecherFormulario(contato);
        }


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato contato = helper.pegaContato();
                ContatoDAO dao = new ContatoDAO(FormularioActivity.this);
                if (contato.getId() != null){
                    dao.alterar(contato);
                }else {
                    dao.inserir(contato);
                }
                dao.close();
                Toast.makeText(getApplicationContext(),"Contato: "+contato.getNome(), Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
