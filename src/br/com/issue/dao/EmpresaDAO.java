package br.com.issue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.issue.connection.ConnectionFactory;
import br.com.issue.model.Empresa;

public class EmpresaDAO {

	private Connection connection;
	
	public EmpresaDAO()
	{
		new ConnectionFactory();
		try {
			this.connection = ConnectionFactory.obtemConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(Empresa empresa)
	{
		String sql = "INSERT INTO empresa (nome) VALUES (?)";
		try
		{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, empresa.getNome());
		
			try
			{
				String sqlSelect = "SELECT LAST_INSERT_ID()";
				PreparedStatement ps2 = connection.prepareStatement(sqlSelect);
				ResultSet rs = ps2.executeQuery();
				
				if(rs.next())
				{
					empresa.setId(rs.getInt(1));
				}
				
				ps2.close();
				rs.close();
			} catch (SQLException e)
			{
				throw new RuntimeException(e);
			}
			
			ps.close();
		}catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void alterar(Empresa empresa)
	{
		String sql = "UPDATE empresa SET nome = ? WHERE id = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, empresa.getNome());
			ps.setInt(2, empresa.getId());

			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void excluir(Empresa empresa)
	{
		String sql = "DELETE FROM empresa WHERE id=?";
		
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, empresa.getId());
			
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Empresa> listar()
	{
		String sql = "SELECT * FROM empresa";
		
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Empresa> lista = new ArrayList<Empresa>();
			
			while(rs.next())
			{
				Empresa empresa = new Empresa();
				
				empresa.setId(rs.getInt("id"));
				empresa.setNome(rs.getString("nome"));
			
				lista.add(empresa);
			}
			
			ps.close();
			rs.close();
			
			return lista;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Empresa> procurar(String chave)
	{
		String sql = "SELECT * FROM empresa WHERE upper(nome) like ?";
		
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + chave.toUpperCase() + "%");
			ResultSet rs = ps.executeQuery();
			List<Empresa> lista = new ArrayList<Empresa>();

			while(rs.next())
			{
				Empresa empresa = new Empresa();
				empresa.setId(rs.getInt("id"));
				empresa.setNome(rs.getString("nome"));
				
				lista.add(empresa);
			}
			
			ps.close();
			rs.close();

			return lista;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
