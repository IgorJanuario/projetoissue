package br.com.issue.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logica {

	String executa(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
