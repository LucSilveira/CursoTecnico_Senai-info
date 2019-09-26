package esportes.br.com.senai.esportes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView question;
    private SeekBar seekDados;
    private ImageView imgPrincipal;
    private TextView numeros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekDados = findViewById(R.id.skQuanto);
        imgPrincipal = findViewById(R.id.imgPrima);
        question = findViewById(R.id.textPergunta);
        numeros = findViewById(R.id.txt);

        seekDados.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //String itensSelecionados = "";
                numeros.setText(""+progress);

                if(progress < 1){
                    imgPrincipal.setImageResource(R.drawable.jogos2);
                }else if(progress >= 1 && progress <= 3){
                    imgPrincipal.setImageResource(R.drawable.pouco);
                }else if(progress >= 4 && progress <= 6){
                    imgPrincipal.setImageResource(R.drawable.medio);
                }else if(progress >= 7){
                    imgPrincipal.setImageResource(R.drawable.muito);
                }else{
                    imgPrincipal.setImageResource(R.drawable.jogos2);
                }
               // numeros.setText(itensSelecionados);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "On", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Off", Toast.LENGTH_LONG).show();
            }
        });
    }
}
