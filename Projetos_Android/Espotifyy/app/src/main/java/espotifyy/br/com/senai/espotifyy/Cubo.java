package espotifyy.br.com.senai.espotifyy;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Cubo extends AppCompatActivity implements View.OnClickListener{

    private ImageView Mil;
    private ImageView Quem;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubo);

        Mil = findViewById(R.id.idMil);
        Quem = findViewById(R.id.idQem);

        Mil.setOnClickListener(this);
        Quem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.idMil:
                mediaPlayer = MediaPlayer.create(Cubo.this, R.raw.mil);
                tocarMusica();
                Toast.makeText(Cubo.this, "1980.mp3", Toast.LENGTH_LONG).show();
                break;
            case R.id.idQem:
                mediaPlayer = MediaPlayer.create(Cubo.this, R.raw.te_viu);
                tocarMusica();
                Toast.makeText(Cubo.this, "Quem te viu.mp3", Toast.LENGTH_LONG).show();
                break;
        }

    }
    public void tocarMusica(){
        if (mediaPlayer != null){
            mediaPlayer.start();
        }
    }
}
