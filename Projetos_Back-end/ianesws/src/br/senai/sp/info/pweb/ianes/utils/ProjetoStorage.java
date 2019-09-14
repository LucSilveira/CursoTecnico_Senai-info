package br.senai.sp.info.pweb.ianes.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjetoStorage {

	/**
	 * Guarda informações sobre onde esta rodando o projeto
	 */
	@Autowired
	private ServletContext context;
	
	/**
	 * Retorna o caminho da pasta do projeto
	 * @return
	 */
	public String pegarCaminhoDoProjeto() {
		return context.getRealPath("");
	}
	
	/**
	 * Cria um novo arquivo para a foto de perfil do usuário em
	 * <pastaDoProjeto>/assets/fotos/<nomeDoArquivo>
	 * @param nomeDoArquivo
	 * @param dadosDoArquivo
	 * @throws IOException 
	 */
	public void armazenarFotoDePerfil(String nomeDoArquivo, byte[] dadosDoArquivo) throws IOException {
		//Pega o caminho para a pasta /assets/fotos
		String caminhoFotos = pegarCaminhoDoProjeto() + "assets/fotos";
		String caminhoArquivo = caminhoFotos + "/" + nomeDoArquivo;
		
		System.out.println("Pasta usuario::::::::::::::::::::::::::: " + caminhoFotos);
		System.out.println("Arquivo uaurio::::::::::::::::::::::;; " + caminhoArquivo);
		
		//Criar a pasta de fotos caso ela nao exista
		File pastaFoto = new File(caminhoFotos);
		if(!pastaFoto.exists()) {
			pastaFoto.mkdirs(); //Cria a pasta
		}
		
		//Cria o arquivo na pasta
		File arquivoFoto = new File(caminhoArquivo);
		if(arquivoFoto.exists()) {
			arquivoFoto.delete();
		}
		
		arquivoFoto.createNewFile();
		
		//Salva o conteudo no arquivo
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arquivoFoto));
		bos.write(dadosDoArquivo);
		bos.close();
	}
	
	public void armazenarFotoPatrimonio(String nomeArquivo, byte[] dadosArquivo) throws IOException {
		
		String caminhoFoto = pegarCaminhoDoProjeto() + "assets/fotos";
		String caminhoArquivo = caminhoFoto + "/" + nomeArquivo;
		
		System.out.println("Pasta::::::::::::::::::::::::::: " + caminhoFoto);
		System.out.println("Arquivo::::::::::::::::::::::;; " + caminhoArquivo);
		
			File pastaFoto = new File(caminhoFoto);
				if (!pastaFoto.exists()) {
					pastaFoto.mkdirs();
					}		
		
				//Cria o arquivo na pasta
				File arquivoFoto = new File(caminhoArquivo);
				if(arquivoFoto.exists()) {
					arquivoFoto.delete();
				}
				
				arquivoFoto.createNewFile();

				//Salva o conteudo no arquivo
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arquivoFoto));
				bos.write(dadosArquivo);
				bos.close();
		
		
	}
}
