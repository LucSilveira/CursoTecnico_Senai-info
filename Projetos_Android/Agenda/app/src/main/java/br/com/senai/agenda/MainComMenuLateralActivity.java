package br.com.senai.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.agenda.adapter.RecyclerAdapter;
import br.com.senai.agenda.config.RetrofitConfig;
import br.com.senai.agenda.holder.ContatosViewHolder;
import br.com.senai.agenda.modelo.Contato;
import br.com.senai.agenda.modelo.ContatoDAO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainComMenuLateralActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {


    private RecyclerView recyclerLista;
    private SearchView campoPesquisa;
    private RecyclerAdapter recyclerAdapter;
    private ContatoDAO contatoDAO;
    private List<Contato> contatos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_com_menu_lateral);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FormularioActivity.class);
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

        recyclerLista = findViewById(R.id.recycler);
        campoPesquisa = findViewById(R.id.campoPesquisa);
        campoPesquisa.setQueryHint("Digite um nome");
        campoPesquisa.setOnQueryTextListener(this);
       // ContatoDAO dao = new ContatoDAO(this);
        //carregarLista(dao.buscaContato());

        //Carregando lista com Retrofit

        Call<List<Contato>> callLista = new RetrofitConfig().getContatoInterface().listarContatos();

        callLista.enqueue(new Callback<List<Contato>>() {
            @Override
            public void onResponse(Call<List<Contato>> call, Response<List<Contato>> response) {
                if (response.isSuccessful()){
                    contatos = response.body();
                    if (contatos != null){
                        carregarLista(contatos);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Contato>> call, Throwable t) {

            }
        });



    }//fim OnCreate




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
        getMenuInflater().inflate(R.menu.main_com_menu_lateral, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //ContatoDAO dao = new ContatoDAO(this);
        //carregarLista(dao.buscaContato());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       // Toast.makeText(this, "Pesquisando", Toast.LENGTH_SHORT).show();
//        ContatoDAO dao = new ContatoDAO(this);
//        carregarLista(dao.buscarNome(newText));
        return true;
    }


    private void carregarLista(List<Contato> contatos) {
        RecyclerAdapter adapter = new RecyclerAdapter(this, contatos);
        recyclerLista.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerLista.setLayoutManager(layoutManager);
        recyclerLista.setNestedScrollingEnabled(true);

    }
}
