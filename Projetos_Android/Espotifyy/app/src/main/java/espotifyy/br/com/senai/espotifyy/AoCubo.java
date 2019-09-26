package espotifyy.br.com.senai.espotifyy;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class AoCubo extends AppCompatActivity implements View.OnClickListener{

    private ImageView Chamado;
    private ImageView Tudo;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ao_cubo);

        Chamado = findViewById(R.id.idMeu);
        Tudo = findViewById(R.id.idTud);

        Chamado.setOnClickListener(this);
        Tudo.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.idMeu:
                mediaPlayer = MediaPlayer.create(AoCubo.this, R.raw.chamado);
                tocarMusica();
                Toast.makeText(AoCubo.this, "Meu chamado.mp3", Toast.LENGTH_LONG).show();
                break;
            case R.id.idTud:
                mediaPlayer = MediaPlayer.create(AoCubo.this, R.raw.tudo);
                tocarMusica();
                Toast.makeText(AoCubo.this, "Tudo Nosso.mp3", Toast.LENGTH_LONG).show();
                break;
        }
    }
    public void tocarMusica(){
        if(mediaPlayer != null){
            mediaPlayer.start();
        }
    }
}
