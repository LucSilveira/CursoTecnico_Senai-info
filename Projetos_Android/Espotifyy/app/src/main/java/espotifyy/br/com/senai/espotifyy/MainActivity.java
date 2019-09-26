package espotifyy.br.com.senai.espotifyy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView AoCubo;
    private ImageView Cubo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AoCubo = findViewById(R.id.abm01);
        Cubo = findViewById(R.id.abm02);

        AoCubo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AoCubo.class);
                startActivity(intent);
            }
        });

        Cubo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Cubo.class);
                startActivity(intent);
            }
        });
    }
}
