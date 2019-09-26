package jokenpo.br.com.senai.jukenpo;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import junit.framework.Test;

import java.util.Random;

public class MJogo extends AppCompatActivity {

    private ImageView Pedra;
    private ImageView Papel;
    private ImageView Tesoura;
    private Button Teste;
    private ImageView Jogador;
    private ImageView Maquina;
    private String[] numbers = {"Papel", "Pedra", "Tesoura"};

    private ImageView usuario;
    private ImageView maquina;
    public String resultadoJogador;
    public String resultadoMaquina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mjogo);

        Pedra = findViewById(R.id.idPedra);
        Papel = findViewById(R.id.idPapel);
        Tesoura = findViewById(R.id.idTesoura);
        Teste = findViewById(R.id.btnJogar);
        Jogador = findViewById(R.id.idPlayer);
        Maquina = findViewById(R.id.idMaquina);


        /*Pedra.setOnClickListener(this);
        Papel.setOnClickListener(this);
        Tesoura.setOnClickListener(this);*/

        Teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int numeroAleatorio = rand.nextInt(numbers.length);
                if (numeroAleatorio == 0) {
                    Maquina.setImageResource(R.drawable.papel);
                    resultadoMaquina = "Papel";
                } else if (numeroAleatorio == 1) {
                    Maquina.setImageResource(R.drawable.pedra);
                    resultadoMaquina = "Pedra";
                } else if (numeroAleatorio == 2) {
                    Maquina.setImageResource(R.drawable.tesoura);
                    resultadoMaquina = "Tesoura";
                }else if(Jogador == null){
                    onDestroy();
                }
                desafio();
            }
        });

        Pedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resultadoJogador = "Pedra";
                Jogador.setImageResource(R.drawable.pedra);
            }
        });
        Papel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultadoJogador = "Papel";
                Jogador.setImageResource(R.drawable.papel);
            }
        });
        Tesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultadoJogador = "Tesoura";
                Jogador.setImageResource(R.drawable.tesoura);
            }
        });
    }

    public void desafio() {

        if (resultadoJogador.equals("Pedra") && resultadoMaquina.equals("Papel")) {
            Toast.makeText(getApplicationContext(), "Perdeu!", Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recomecar();
                }
            }, 900);
        } else if (resultadoJogador.equals("Pedra") && resultadoMaquina.equals("Pedra")) {
            Toast.makeText(getApplicationContext(), "Empatou!", Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recomecar();
                }
            }, 900);
        } else if (resultadoJogador.equals("Pedra") && resultadoMaquina.equals("Tesoura")) {
            Toast.makeText(getApplicationContext(), "Ganhou!", Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recomecar();
                }
            }, 900);
        } else if (resultadoJogador.equals("Papel") && resultadoMaquina.equals("Pedra")) {
            Toast.makeText(getApplicationContext(), "Ganhou!", Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recomecar();
                }
            }, 900);
        } else if (resultadoJogador.equals("Papel") && resultadoMaquina.equals("Papel")) {
            Toast.makeText(getApplicationContext(), "Empatou!", Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recomecar();
                }
            }, 900);
        } else if (resultadoJogador.equals("Papel") && resultadoMaquina.equals("Tesoura")) {
            Toast.makeText(getApplicationContext(), "Perdeu!", Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recomecar();
                }
            }, 900);
        } else if (resultadoJogador.equals("Tesoura") && resultadoMaquina.equals("Pedra")) {
            Toast.makeText(getApplicationContext(), "Perdeu!", Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recomecar();
                }
            }, 900);
        } else if (resultadoJogador.equals("Tesoura") && resultadoMaquina.equals("Papel")) {
            Toast.makeText(getApplicationContext(), "Ganhou!", Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recomecar();
                }
            }, 900);
        } else if (resultadoJogador.equals("Tesoura") && resultadoMaquina.equals("Tesoura")) {
            Toast.makeText(getApplicationContext(), "Empate!", Toast.LENGTH_LONG).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recomecar();
                }
            }, 900);
        }else{
            onDestroy();
            recomecar();
        }
    }

    public void recomecar() {
        if (Jogador != null) {
            Jogador.setImageResource(R.drawable.player);
            Maquina.setImageResource(R.drawable.machine);
            Teste.setText("TESTE SUA SORTE");
        }
    }

    public void onDestroy() {
        if (resultadoJogador == null) {
            super.onDestroy();
        }
    }
}
