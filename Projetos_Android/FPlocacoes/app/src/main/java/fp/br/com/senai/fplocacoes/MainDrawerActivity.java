package fp.br.com.senai.fplocacoes;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import fp.br.com.senai.fplocacoes.models.TranspDao;


public class MainDrawerActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int GALERIA_CODE = 1;
    private static final int PERMISSAO_REQUEST = 1;
    private EditText email;
    private EditText senha;
    private ImageView imagem;
    private Button cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        email = findViewById(R.id.setEmail);
        senha = findViewById(R.id.setSenha);
        cadastro = findViewById(R.id.btnCadastro);
        imagem = findViewById(R.id.img);
        imagem.setOnClickListener(this);

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*final FormularioHelper helper = new FormularioHelper(this);
                Transporte tranp = helper.pegaTransporte();*/
                TranspDao dao = new TranspDao(MainDrawerActivity.this);
                Intent inte = new Intent(MainDrawerActivity.this, MainActivityHome.class);
                startActivity(inte);
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
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img:
                Intent intent =
                        new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA_CODE);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == GALERIA_CODE){
            Uri imagemSelect = data.getData();
            String[] caminhoDireit = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(imagemSelect, caminhoDireit, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(caminhoDireit[0]);
            String caminhoImagem = c.getString(columnIndex);
            c.close();
            Bitmap imagemRetun = (BitmapFactory.decodeFile(caminhoImagem));
            imagem.setImageBitmap(imagemRetun);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSAO_REQUEST){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED);

        } else{
            // A permissão foi negada. Precisa ver o que deve ser desabilitado
        }
        return;
    }
}
