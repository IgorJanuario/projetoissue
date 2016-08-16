package br.com.issue.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.issue.logica.Logica;

@WebServlet("/mvc")
public class ControllerServlet extends HttpServlet {
	
	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String parametro = request.getParameter("logica");
		String nomeDaClasse = "br.com.issue.logica." + parametro;
		
		try
		{
			Class classe = Class.forName(nomeDaClasse);
			
			Logica logica = (Logica) classe.newInstance();
			String pagina = logica.executa(request, response);
		
			request.getRequestDispatcher(pagina).forward(request, response);
		} catch (ClassNotFoundException e)
		{
			throw new ServletException("A logica de negocios causou uma exceção", e);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
