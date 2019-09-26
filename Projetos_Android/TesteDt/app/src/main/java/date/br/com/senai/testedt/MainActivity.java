package date.br.com.senai.testedt;

import android.app.DatePickerDialog;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String ZERO = "0";
    private static final String BARRA = "/";

    public final Calendar c = Calendar.getInstance();

    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_YEAR);
    final int ano = c.get(Calendar.YEAR);

    EditText setFechar;
    ImageButton imgFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        setFechar = (EditText) findViewById(R.id.et_mostrar_fecha_picker);
        imgFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha);
        imgFecha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_obtener_fecha:
                obtenerFecha();
                break;
        }
    }

    private void obtenerFecha(){
        DatePickerDialog rcFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMoth) {
                final int mesAtual = month + 1;
                String diaFt = (dayOfMoth < 10)? ZERO + String.valueOf(dayOfMoth):String.valueOf(dayOfMoth);
                String mesFt = (month < 10)? ZERO + String.valueOf(month):String.valueOf(month);
                setFechar.setText(diaFt + BARRA + mesFt + BARRA + year);

            }
        }, ano, mes, dia);
        rcFecha.show();
    }
}
