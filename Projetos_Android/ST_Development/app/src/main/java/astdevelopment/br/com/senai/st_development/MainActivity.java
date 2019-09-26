package astdevelopment.br.com.senai.st_development;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity/*
        implements NavigationView.OnNavigationItemSelectedListener*/{
    private EditText name;
    private EditText senha;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.setName);
        senha = findViewById(R.id.setSenha);
        cadastrar = findViewById(R.id.btnCadastro);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().length() == 0 && senha.getText().length() >= 8){
                    Toast.makeText(getApplicationContext(), "Insira o nome!", Toast.LENGTH_LONG).show();
                }else if(senha.getText().length() == 0 && name.getText().length() >= 3){
                    Toast.makeText(getApplicationContext(), "insira uma senha!", Toast.LENGTH_LONG).show();
                }else if(name.getText().length() == 0 && senha.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Campos vazios!", Toast.LENGTH_LONG).show();
                }else if(senha.getText().length() < 8){
                    Toast.makeText(getApplicationContext(), "a senha deve\nter 8 caracteres!", Toast.LENGTH_LONG).show();
                }else if (name.getText().length() >= 3 && senha.getText().length() >= 8) {

                    Intent intent = new Intent(MainActivity.this, bem_vindo.class);
                    intent.putExtra("nome", name.getText().toString());
                    startActivity(intent);
                }
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Não está logado!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}