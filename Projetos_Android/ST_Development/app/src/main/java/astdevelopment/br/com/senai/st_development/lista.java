package astdevelopment.br.com.senai.st_development;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.ContextMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class lista extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ListView listaUsers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listaUsers = findViewById(R.id.idLISTA);
        listaUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Mensagem mensagem = (Mensagem) listaUsers.getItemAtPosition(position);

                Intent intent = new Intent(lista.this, SendMensagem.class);
                intent.putExtra("mensagem", mensagem);
                startActivity(intent);
            }
        });

        carregarLista();
        registerForContextMenu(listaUsers);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(lista.this, SendMensagem.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Mensagem mensg = (Mensagem) listaUsers.getItemAtPosition(info.position);
                //Toast.makeText(getApplicationContext(), "Contato: "+contato.getName()+"\n\tRemovido", Toast.LENGTH_LONG).show();
                StDao dao = new StDao(lista.this);
                dao.remover(mensg);
                dao.close();
                carregarLista();
                return false;
            }
        });
    }

    public void carregarLista(){
        StDao dao = new StDao(this);

        List<Mensagem> mensagens = dao.buscaMensagens();
        ArrayAdapter<Mensagem> adaptor = new ArrayAdapter<Mensagem>(this, android.R.layout.simple_list_item_1, mensagens);
        listaUsers.setAdapter(adaptor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lista, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_atua) {
            Intent intent  = new Intent(lista.this, areas.class);
            startActivity(intent);
        } else if (id == R.id.nav_historia) {
            Intent intent  = new Intent(lista.this, historia.class);
            startActivity(intent);
        } else if (id == R.id.nav_contato) {
            Intent intent  = new Intent(lista.this, SendMensagem.class);
            startActivity(intent);
        } else if (id == R.id.nav_lista) {
            Intent intent  = new Intent(lista.this, lista.class);
            startActivity(intent);
        }else if (id == R.id.nav_logout){
            Intent intent  = new Intent(lista.this, MainActivity.class);
            startActivity(intent);
        }else if(id == R.id.nav_site){
            Uri uri = Uri.parse("https://www.google.com.br/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
