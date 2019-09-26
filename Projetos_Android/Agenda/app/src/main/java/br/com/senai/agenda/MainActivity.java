package br.com.senai.agenda;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.senai.agenda.adapter.ContatosAdapter;
import br.com.senai.agenda.converter.ContatoConverter;
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
            public void onItemClick(AdapterView<?> lista, View item, int i, long l) {
                Contato contato = (Contato) listaDeContatos.getItemAtPosition(i);

                Intent irParaOForm = new Intent(MainActivity.this, FormularioActivity.class);
                irParaOForm.putExtra("contato", contato);
                startActivity(irParaOForm);

               // Toast.makeText(getApplicationContext(),"Aluno: "+ contato.getNome()+" Clicado", Toast.LENGTH_LONG).show();
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

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Contato contato = (Contato) listaDeContatos.getItemAtPosition(info.position);

        MenuItem itemSMS = menu.add("Enviar SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW) ;
        intentSMS.setData(Uri.parse("sms:"+contato.getTelefone()));
        itemSMS.setIntent(intentSMS);

        MenuItem itemMapa = menu.add("Visualizar no mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?q=" + contato.getEndereco()));
        itemMapa.setIntent(intentMapa);


        MenuItem irFace = menu.add("Acessar Perfil Facebook");
        Intent intentFace = new Intent(Intent.ACTION_VIEW);
        String endFace =  contato.getFace();
        if (!endFace.startsWith("http://")){
            endFace = "http://facebook.com/"+endFace;
        }
        intentFace.setData(Uri.parse(endFace));
        irFace.setIntent(intentFace);



        MenuItem remover =  menu.add("Remover");
        remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                ContatoDAO dao = new ContatoDAO(MainActivity.this);
                dao.remover(contato);
                dao.close();
                carregarLista();
                return false;
            }
        });//fim botao Remover


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_enviar_notas:
                ContatoDAO dao = new ContatoDAO(this);
                List<Contato> contatos = dao.buscaContato();
                dao.close();
                ContatoConverter conversor = new ContatoConverter();

                String json = conversor.converteParaJSON(contatos);
                Toast.makeText(getApplicationContext(),json, Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregarLista() {
        ContatoDAO dao = new ContatoDAO(this);
        List<Contato> contatos = dao.buscaContato();
        ContatosAdapter adaptador = new ContatosAdapter(this, contatos);
        listaDeContatos.setAdapter(adaptador);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();

    }
}
