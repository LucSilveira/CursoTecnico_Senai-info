package br.com.senai.agenda;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.senai.agenda.adapter.ContatosAdapter;
import br.com.senai.agenda.modelo.Contato;
import br.com.senai.agenda.modelo.ContatoDAO;

public class MainActivity extends AppCompatActivity {

    private ListView listaDeContatos;
    private Button botaoAdicionar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaDeContatos = findViewById(R.id.listaContatos);

        listaDeContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                Contato contato = (Contato) listaDeContatos.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("contato",contato);
                startActivity(intent);
                //  Toast.makeText(getApplicationContext(),"Contato: "+contato.getNome()+" Clicado", Toast.LENGTH_LONG).show();
            }
        });

        carregarLista();

        botaoAdicionar = findViewById(R.id.btnAdicionar);
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

      registerForContextMenu(listaDeContatos);
    }//Fim onCreate

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Contato contato = (Contato) listaDeContatos.getItemAtPosition(info.position);

        MenuItem deletar =  menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem item) {
              AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
              Contato contato = (Contato) listaDeContatos.getItemAtPosition(info.position);
              ContatoDAO dao = new ContatoDAO(MainActivity.this);
              dao.deletar(contato);
              dao.close();
              carregarLista();
              //  Toast.makeText(getApplicationContext(),"Contato: "+contato.getNome()+" Removido", Toast.LENGTH_LONG).show();
               return false;
           }
       });//fim deletar
/*
        //Acessando navegador
        MenuItem facebook = menu.add("Acessar Perfil");
        Intent intentFace = new Intent(Intent.ACTION_VIEW);
        String face  = contato.getFace();
        if (!face.startsWith("http://www.facebook.com/")){
            face = "http://www.facebook.com/"+ face;
        }
        intentFace.setData(Uri.parse(face));
        facebook.setIntent(intentFace);
        //Enviando SMS

        MenuItem sms = menu.add("Enviar SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:"+contato.getTelefone()));
        sms.setIntent(intentSMS);
        //fim SMS

        MenuItem localizacao = menu.add("Ver no mapa");
        Intent intentLocalizacao = new Intent(Intent.ACTION_VIEW);
        intentLocalizacao.setData(Uri.parse("geo:0,0?q="+contato.getEndereco()));
        localizacao.setIntent(intentLocalizacao);
        //Acessando o telefone
        MenuItem ligar = menu.add("Ligar");
        Intent intentLigar = new Intent(Intent.ACTION_VIEW);
        intentLigar.setData(Uri.parse("tel:"+contato.getTelefone()));
        ligar.setIntent(intentLigar);
*/


    }

    private void carregarLista() {
        ContatoDAO dao = new ContatoDAO(this);
        List<Contato> contatos = dao.buscaContato();
        ContatosAdapter adaptador = new ContatosAdapter(MainActivity.this ,contatos);
        listaDeContatos.setAdapter(adaptador);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
