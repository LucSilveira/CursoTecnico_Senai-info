package astdevelopment.br.com.senai.st_development;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SendMensagem extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button enviar;
    private EditText mensg;
    private EditText mail;
    private AlertDialog.Builder dialog;
    private Button site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mensagem);

        enviar = findViewById(R.id.idSend);
        mail = findViewById(R.id.setEmail);
        mensg = findViewById(R.id.setMensg);



        final FormularioHelper help = new FormularioHelper(this);

        Intent extras = getIntent();
        final Mensagem mensagem = (Mensagem) getIntent().getSerializableExtra("mensagem");
        if(mensagem != null){
            help.preencherFormulario(mensagem);
        }

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mensg.getText().length() == 0 && mail.getText().length() > 0){
                    Toast.makeText(getApplicationContext(), "Insira uma mensegm!", Toast.LENGTH_LONG).show();
                }else if(mensg.getText().length() > 0 && mail.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Insira seu email!", Toast.LENGTH_LONG).show();
                }else if(mensg.getText().length() > 0 && mail.getText().length() > 0) {
                    Mensagem mensagem = help.pegarMensagem();
                    StDao dao = new StDao(SendMensagem.this);
                    if(mensagem.getId() != null){
                        dao.editar(mensagem);
                    }else{
                        dao.inserir(mensagem);
                    }
                    dao.close();
                    dialog = new AlertDialog.Builder(SendMensagem.this);
                    dialog.setTitle("Mensagem concluida!");
                    dialog.setMessage("A sua mensagem foi salva com sucess!");
                    dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(SendMensagem.this, lista.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    dialog.create();
                    dialog.show();
                }

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
                Intent intent  = new Intent(SendMensagem.this, SendMensagem.class);
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
        getMenuInflater().inflate(R.menu.send_mensagem, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_atua) {
            Intent intent  = new Intent(SendMensagem.this, areas.class);
            startActivity(intent);
        } else if (id == R.id.nav_historia) {
            Intent intent  = new Intent(SendMensagem.this, historia.class);
            startActivity(intent);
        } else if (id == R.id.nav_contato) {
            Intent intent  = new Intent(SendMensagem.this, SendMensagem.class);
            startActivity(intent);
        } else if (id == R.id.nav_lista) {
            Intent intent  = new Intent(SendMensagem.this, lista.class);
            startActivity(intent);
        }else if (id == R.id.nav_logout){
            Intent intent  = new Intent(SendMensagem.this, MainActivity.class);
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
