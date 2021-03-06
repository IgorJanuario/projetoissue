package br.com.issue.logica;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.issue.dao.UserDAO;
import br.com.issue.model.User;

public class EfetuarLoginLogic implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		User user = new User();
		
		user.setLogin(login);
		user.setSenha(senha);
		
		UserDAO dao = new UserDAO();
		
		if (dao.autenticar(user) == true)
		{
			List<User> users = new ArrayList<User>();
			users.add(user);
			request.setAttribute("users", users);
			return "entrou.jsp";
		}
		else
		{
			return "index.jsp";
		}

	}

}
