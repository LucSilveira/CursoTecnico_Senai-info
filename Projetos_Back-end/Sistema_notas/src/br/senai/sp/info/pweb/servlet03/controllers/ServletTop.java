package br.senai.sp.info.pweb.servlet03.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletTop extends HttpServlet{
	
	public void forward(String view,
									  HttpServletRequest req,
									  HttpServletResponse res) throws ServletException, IOException{
	
	//objeto despachador para abrir as views do WEB-INF
	getServletContext()
			.getRequestDispatcher("/WEB-INF/views/"+ view + ".jsp")
			.forward(req, res);
	}
}
