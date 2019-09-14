package br.senai.sp.info.pweb.servlet03.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sp.info.pweb.servlet03.models.Nota;
import br.senai.sp.info.servlet03.dao.NotaDao;

@WebServlet(value = "/notas/cadastrar")
public class CadastrarNotaController extends ServletTop{
	
	private NotaDao notaDao;
	
	public CadastrarNotaController() {
		notaDao = new NotaDao();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//pegando os parametros da requisicao
		String titulo = req.getParameter("titulo");
		String descricao = req.getParameter("descricao");
		
		//comparando diferente de null porque caso a prioridadeAlta nao venha
		//logo ela nao foi marcada (da false) caso contrario, da true
		Boolean prioridadeAlta = req.getParameter("prioridadeAlta") != null;
		
		String dataEmTexto = req.getParameter("dataDeConclusao");
		
		//----------------COnvertendo text em util.Date
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		try {
			data = fmt.parse(dataEmTexto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//----------Criando modelo e aplicando valores nele
		Nota nota = new Nota();
		nota.setDataDeConclusao(data);
		nota.setDescricao(descricao);
		nota.setId(0L);
		nota.setPrioridadeAlta(prioridadeAlta);
		nota.setTitulo(titulo);
		
		//Cadastra a nota na Dao
		notaDao.add(nota);
		
		//Enviar o objeto para a view
		
		
		
		//Aplicar a nota na resposta
		req.setAttribute("nota", nota);
		forward("mostra", req, res);
	}
}
