package br.com.issue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.issue.connection.ConnectionFactory;
import br.com.issue.model.User;

public class UserDAO {

	private Connection connection;
	
	public UserDAO()
	{
		try {
			new ConnectionFactory();
			this.connection = ConnectionFactory.obtemConexao();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void adicionar(User user)
	{
		try
		{
			String sql = "INSERT INTO user (nome, email, login, senha) VALUES (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getLogin());
			ps.setString(4, user.getSenha());
			ps.execute();
			try
			{
				String sqlSelect = "SELECT LAST_INSERT_ID()";
				PreparedStatement ps2 = connection.prepareStatement(sqlSelect);
				ResultSet rs = ps2.executeQuery();
				
				while(rs.next())
				{
					user.setId(rs.getInt(1));
				}
				
				ps.close();
				rs.close();
			} catch(SQLException e)
			{
				throw new RuntimeException(e);
			}
			
			ps.close();

		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void alterar(User user)
	{
		String sql = "UPDATE user SET nome = ?, email=?, login=?, senha=? WHERE id = ?";
		try
		{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getLogin());
			ps.setString(4, user.getSenha());
			ps.setInt(5, user.getId());
			
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void excluir(User user)
	{
		String sql = "DELETE FROM user WHERE id = ?";
		PreparedStatement ps;
		try
		{
			ps = connection.prepareStatement(sql);
			ps.setInt(1, user.getId());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}
	
	public List<User> procurar(String chave)
	{
		String sql = "SELECT * FROM user WHERE upper(nome) like ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + chave.toUpperCase() + "%");
			ResultSet rs = ps.executeQuery();
			List<User> lista = new ArrayList<User>();

			while(rs.next())
			{
				User usuario = new User();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				lista.add(usuario);
			}
			
			ps.close();
			rs.close();
			
			return lista;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<User> lista()
	{
		String sql = "SELECT * FROM user";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<User> listar = new ArrayList<User>();
			
			while(rs.next())
			{
				User user = new User();
				
				user.setId(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setEmail(rs.getString("email"));
				user.setLogin(rs.getString("login"));
				user.setSenha(rs.getString("senha"));
				
				listar.add(user);
			}
	
			ps.close();
			rs.close();

			return listar;
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
