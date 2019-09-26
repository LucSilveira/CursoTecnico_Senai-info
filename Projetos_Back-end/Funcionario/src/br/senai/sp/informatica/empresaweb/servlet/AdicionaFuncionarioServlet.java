package br.senai.sp.informatica.empresaweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sp.informatica.empresaweb.dao.FuncionarioDao;
import br.senai.sp.informatica.empresaweb.model.Funcionario;

/**
 * Servlet implementation class AdicionaFuncionarioServlet
 */
@WebServlet("/adicionaFuncionario")
public class AdicionaFuncionarioServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//obtem um printWriter, esse objeto nos
		//permitira escrever mansagens no response
		PrintWriter out = response.getWriter();
		
		//pega os parametros da requisicao
		//por meio dos names no form
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String cpf = request.getParameter("cpf");
		String senha = request.getParameter("senha");
		
		FuncionarioDao dao = new FuncionarioDao();
		
		Funcionario funcionario = new Funcionario();
		
		//define os atributos do contato
		funcionario.setNome(nome);
		funcionario.setEmail(email);
		funcionario.setCpf(cpf);
		funcionario.setSenha(senha);
		
		//salva o contato
		dao.salva(funcionario);
		
		//obtem uma resposta ao usuario
		out.println("<html>");
		out.println("<body>");
		out.println("Funcionario " + funcionario.getNome() + " salvo com sucesso");
		out.println("</body>");
		out.println("</html>");
	}
}