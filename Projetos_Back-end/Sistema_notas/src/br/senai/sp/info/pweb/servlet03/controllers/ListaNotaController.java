package br.senai.sp.info.pweb.servlet03.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sp.info.pweb.servlet03.models.Nota;
import br.senai.sp.info.servlet03.dao.NotaDao;

@WebServlet(value = "/notas")
public class ListaNotaController extends ServletTop{
	
	private NotaDao notaDao;
	
	public ListaNotaController() {
		notaDao = new NotaDao();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//Busca as notas cadastradas
		List<Nota> notas = notaDao.buscaTodos();
		
		//Passando os dados para a view lista
		req.setAttribute("notas", notas);
		
		//Abre a view
		forward("lista", req, res);
	}

}
