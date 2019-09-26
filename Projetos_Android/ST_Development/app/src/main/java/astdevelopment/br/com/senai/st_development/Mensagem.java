package astdevelopment.br.com.senai.st_development;

import java.io.Serializable;

/**
 * Created by 46923597811 on 16/03/2018.
 */

public class Mensagem implements Serializable{

    private Long id;
    private String mensagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    @Override
    public String toString(){
        return getId()+" - "+getMensagem();
    }
}
