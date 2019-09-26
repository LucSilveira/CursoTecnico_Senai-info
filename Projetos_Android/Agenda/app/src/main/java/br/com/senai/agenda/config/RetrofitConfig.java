package br.com.senai.agenda.config;

import br.com.senai.agenda.service.ContatoService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static br.com.senai.agenda.utils.AppUtils.BASE_URL;

/**
 * Created by adminLocal on 16/05/2018.
 */

public class RetrofitConfig {
    private final Retrofit retrofit;

    //Nossa configuração será feita no construtor;
    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    public ContatoService getContatoInterface(){
        return retrofit.create(ContatoService.class);
    }

}
