package astdevelopment.br.com.senai.st_development;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class informativa extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView Mens;
    private ImageView Lista;
    private ImageView Emp;
    private ImageView Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informativa);

        Mens = findViewById(R.id.mensagem);
        Lista = findViewById(R.id.list);
        Emp = findViewById(R.id.empresa);
        Log = findViewById(R.id.logo);

        Mens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(informativa.this, SendMensagem.class);
                startActivity(intent);
            }
        });
        Lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(informativa.this, lista.class);
                startActivity(intent);
            }
        });
        Emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(informativa.this, historia.class);
                startActivity(intent);
            }
        });
        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(informativa.this, areas.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent  = new Intent(informativa.this, SendMensagem.class);
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
        getMenuInflater().inflate(R.menu.informativa, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_atua) {
            Intent intent  = new Intent(informativa.this, areas.class);
            startActivity(intent);
        } else if (id == R.id.nav_historia) {
            Intent intent  = new Intent(informativa.this, historia.class);
            startActivity(intent);
        } else if (id == R.id.nav_contato) {
            Intent intent  = new Intent(informativa.this, SendMensagem.class);
            startActivity(intent);
        } else if (id == R.id.nav_lista) {
            Intent intent  = new Intent(informativa.this, lista.class);
            startActivity(intent);
        }else if (id == R.id.nav_logout){
            Intent intent  = new Intent(informativa.this, MainActivity.class);
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
