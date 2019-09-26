package br.com.senai.agenda.service;

import java.util.List;

import br.com.senai.agenda.modelo.Contato;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by adminLocal on 16/05/2018.
 */

public interface ContatoService {

    @GET("api/contatos")
    Call<List<Contato>> listarContatos();

    @POST("api/contatos")
    Call<List<Contato>> salvarContato(@Body Contato contato);

    @GET("api/contatos/{id}")
    Call<Contato> buscarContato(@Path("id") Long id);

    @PUT("api/contatos/{id}")
    Call<Void> alterarContato(@Path("id")Long id,@Body Contato contato);

    @DELETE("api/contatos/{id}")
    Call<Void> deletarContato(@Path("id") Long id);


}
