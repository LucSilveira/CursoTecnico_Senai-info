package br.com.senai.agenda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.senai.agenda.config.RetrofitConfig;
import br.com.senai.agenda.modelo.Contato;
import br.com.senai.agenda.modelo.ContatoDAO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormularioActivity extends AppCompatActivity{

    public static final int CODIGO_CAMERA = 567;
    private Button botaoCadastrar;
    public String caminhoFoto;
    private ContatoDAO dao;
    private Contato contato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        botaoCadastrar = findViewById(R.id.btnCadastrar);

        dao = new ContatoDAO(getApplicationContext());

        final FormularioHelper helper = new FormularioHelper(this);

    final Bundle extras = getIntent().getExtras();
    Long contatoId = (extras != null)? extras.getLong("contatoId"): null;

        if(contatoId == null){
            Contato contato = new Contato();
        }else{

           // Contato contatoLocalizado = dao.localizar(contatoId);
            Call<Contato> call = new RetrofitConfig().getContatoInterface().buscarContato(contatoId);

            call.enqueue(new Callback<Contato>() {
                @Override
                public void onResponse(Call<Contato> call, Response<Contato> response) {
                    Contato contatoLocalizado = response.body();
                    helper.preecheFormulario(contatoLocalizado);
                }

                @Override
                public void onFailure(Call<Contato> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Não foi possível carregar o contato", Toast.LENGTH_LONG).show();
                }
            });




        }

        Button botaoFoto = helper.botaoFoto;

        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamera =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// Chamando a camera
               caminhoFoto = getExternalFilesDir(null)+"/"+ System.currentTimeMillis()+".jpg";
                File arquivoFoto = new File(caminhoFoto);
                try{
                   arquivoFoto = createImageFile();
                }catch (IOException e){
                    e.printStackTrace();
                }

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(FormularioActivity.this,BuildConfig.APPLICATION_ID+".provider",arquivoFoto));//armazenando na intent para recebermos
                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contato = helper.pegaContato();
//                ContatoDAO dao = new ContatoDAO(FormularioActivity.this);
//
//                if(contato.getId() != null){
//                    dao.alterar(contato);
//                }else {
//                    dao.inserir(contato);
//                }
//                dao.close();


                if (contato.getId() != null){
                    Call<Void> call = new RetrofitConfig().getContatoInterface().alterarContato(contato.getId(), contato);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(getApplicationContext(), "Contato Alterado com sucesso!", Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Erro ao Alterar!", Toast.LENGTH_LONG).show();

                        }
                    });

                }else{
                    Call<List<Contato>> call = new RetrofitConfig().getContatoInterface().salvarContato(contato);

                    call.enqueue(new Callback<List<Contato>>() {
                        @Override
                        public void onResponse(Call<List<Contato>> call, Response<List<Contato>> response) {
                            Toast.makeText(getApplicationContext(), "FOI", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<List<Contato>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Errou", Toast.LENGTH_LONG).show();
                        }
                    });
                }
               finish();
            }
        });

    }

    File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("MMddyyyy_HHmmss").format(new Date());
        String nomeDaImagem = "IMAGE_"+timeStamp+"_";
        File diretorioDeArmazenamento = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagem = File.createTempFile(nomeDaImagem,".jpg", diretorioDeArmazenamento);
        caminhoFoto = imagem.getAbsolutePath();



        return imagem;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


            if (requestCode == CODIGO_CAMERA) {
                FormularioHelper helper = new FormularioHelper(this);
                helper.carregaImagem(caminhoFoto);
            }

    }


}
