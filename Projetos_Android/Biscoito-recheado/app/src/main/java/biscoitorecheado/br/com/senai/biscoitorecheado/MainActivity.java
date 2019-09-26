package biscoitorecheado.br.com.senai.biscoitorecheado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private CheckBox cbkBiscoito;
    private CheckBox cbkBiscoitoRecheado;
    private SeekBar seekBiscoito;
    private SeekBar seekBiscoitoRecheado;
    private TextView tvBiscoito;
    private TextView tvBiscoitoRecheado;
    private Button botaoEnviar;
    private TextView resposta;
    private ImageView imgPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbkBiscoito = findViewById(R.id.checkBisct);
        cbkBiscoitoRecheado = findViewById(R.id.checkBisctRch);
        seekBiscoito = findViewById(R.id.skBiscoito);
        seekBiscoitoRecheado = findViewById(R.id.skBiscoitioRch);
        tvBiscoito = findViewById(R.id.txtBist);
        tvBiscoitoRecheado = findViewById(R.id.txtBisctRch);
        botaoEnviar = findViewById(R.id.btnEnviar);
        resposta = findViewById(R.id.textResposta);
        imgPrincipal = findViewById(R.id.imgPrima);

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itensSelecionados = "";
                //itensSelecionados += "Item: "+cbkBiscoito.getText()+"Status: "+cbkBiscoito.isChecked() +"\n";
                //itensSelecionados += "Item: "+cbkBiscoitoRecheado.getText()+"Status: "+cbkBiscoitoRecheado.isChecked() +"\n";
                if(cbkBiscoitoRecheado.isChecked() && cbkBiscoito.isChecked()){
                    imgPrincipal.setImageResource(R.drawable.biscbisc);
                }else if(cbkBiscoito.isChecked()){
                    itensSelecionados += "Biscoito \n";
                    imgPrincipal.setImageResource(R.drawable.biscoitao);
                }else if(cbkBiscoitoRecheado.isChecked()){
                    itensSelecionados += "Biscoito Recheado \n";
                    imgPrincipal.setImageResource(R.drawable.biscoitinho);
                }else{
                    imgPrincipal.setImageResource(R.drawable.biscoitos);
                }

                resposta.setText(itensSelecionados);
            }
        });

        //Configurando o seekBar
        seekBiscoito.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvBiscoito.setText(""+progress);
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

        seekBiscoitoRecheado.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvBiscoitoRecheado.setText(""+progress);
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
