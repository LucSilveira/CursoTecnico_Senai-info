package br.senai.sp.info.pweb.servlet03.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sp.info.servlet03.dao.NotaDao;

@WebServlet("/notas/deletar")
public class DeletaNotaController extends ServletTop{
	
	private NotaDao notaDao;
	
	public DeletaNotaController() {
		notaDao = new NotaDao();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//notaDao.deletar(id);
		
		//Pegando o id passado por get
		String idText = req.getParameter("id");
		
		//Conversoes
		Long id = Long.parseLong(idText);
		
		notaDao.deletar(id);
		
		//Redirecionar para a lista
		res.sendRedirect("/servlet-0.3/notas"); 
	}

}