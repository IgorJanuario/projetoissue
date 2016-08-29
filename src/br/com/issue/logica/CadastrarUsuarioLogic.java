package br.com.issue.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.issue.dao.UserDAO;
import br.com.issue.model.User;

public class CadastrarUsuarioLogic implements Logica{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String nome  = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		User user = new User();
		user.setNome(nome);
		user.setEmail(email);
		user.setLogin(login);
		user.setSenha(senha);
		
		UserDAO dao = new UserDAO();
		
		if(dao.adicionar(user) == true)
		{
			return "index.jsp";
		}
		else
		{
			return "cadastrarLogin.jsp";
		}
	}
}
