package br.com.issue.model;

import com.mongodb.DBObject;

public class User {

	private int id;
	private String nome;
	private String email;
	private String login;
	private String senha;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public static User converterToUser(DBObject dbo) {
        User user = new User();

        user.setId((int) dbo.get("_id"));
        user.setNome((String) dbo.get("nome"));
        user.setLogin((String) dbo.get("login"));
        user.setSenha((String) dbo.get("senha"));

        return user;
    }
}
