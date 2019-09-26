package br.com.senai.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.senai.agenda.modelo.Prova;

public class ProvasActivity extends AppCompatActivity {
    private ListView listaDeProvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        List<String> topicosPort = Arrays.asList("Sujeito","Objeto direto", "Objeto Indireto");
        Prova provaPortugues = new Prova("Portugues", "03/12/2017", topicosPort);

        List<String> topicosMat = Arrays.asList("Equações de seungdo grau", "Trigonometria");
        Prova provaMatematica = new Prova("Matematica", "27/03/2018", topicosMat);

        List<Prova> provas = Arrays.asList(provaMatematica, provaPortugues);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(this, android.R.layout.simple_list_item_1, provas);

        listaDeProvas = findViewById(R.id.provas_lista);
        listaDeProvas.setAdapter(adapter);



    }
}
