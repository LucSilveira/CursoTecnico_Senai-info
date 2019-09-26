package calculadorabinaria.br.com.senai.calculadorabinaria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button Limpar;
    private Button Converter;
    private EditText valorInserido;
    private EditText resultadoConvertido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Limpar = findViewById(R.id.btnLimpar);
        Converter = findViewById(R.id.btnConverter);
        valorInserido = findViewById(R.id.vlrInserido);
        resultadoConvertido = findViewById(R.id.resultId);


        Converter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int valor = Integer.parseInt(valorInserido.getText().toString());
                binarios(valor);

            }

        });

    }

    public void limparCampos(View view) {
        valorInserido.setText("");
        resultadoConvertido.setText("");
        Toast.makeText(getApplicationContext(), "Campos Limpos!", Toast.LENGTH_LONG).show();
    }

    public void binarios(int valor) {
        try {
            int d = valor;
            StringBuffer binario = new StringBuffer();
            while (d > 0) {
                int b = d % 2;
                binario.append(b);
                d = d >> 1;
            }
            resultadoConvertido.setText(binario.reverse().toString());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cheque sua conversão!", Toast.LENGTH_LONG).show();
        }
    }
}