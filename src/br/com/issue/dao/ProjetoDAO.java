package br.com.issue.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.issue.connection.ConnectionFactory;
import br.com.issue.model.Projeto;

public class ProjetoDAO {

	private Connection connection;
	
	public ProjetoDAO()
	{
		new ConnectionFactory();
		try {
			this.connection = ConnectionFactory.obtemConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(Projeto projeto)
	{
		String sql = "INSERT INTO projeto (nome, dataInicio,dataFim,orcamento,descricao,idEmpresa) VALUES ( ?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, projeto.getNome());
			ps.setDate(2, new Date( projeto.getDataIncio().getTime()));
			ps.setDate(3, new Date (projeto.getDataFim().getTime()));
			ps.setDouble(4, projeto.getOrcamento());
			ps.setString(5, projeto.getDescricao());
			ps.setInt(6, projeto.getIdEmpresa());
			ps.execute();
			
			try
			{
				String sqlSelect = "SELECT LAST_INSERT_ID()";
				PreparedStatement ps2 = connection.prepareStatement(sqlSelect);
				ResultSet rs = ps2.executeQuery();
				
				while(rs.next())
				{
					projeto.setId(rs.getInt(1));
				}
				
				ps2.close();
				rs.close();

			} catch(SQLException e)
			{
				throw new RuntimeException(e);
			}
			
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(Projeto projeto)
	{
		String sql = "UPDATE projeto SET nome = ?, dataFim = ?, orcamento = ?, descricao = ?, idEmpresa = ? WHERE id = ?";
		
		try
		{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, projeto.getNome());
			ps.setDate(2, new Date(projeto.getDataFim().getTime()));
			ps.setDouble(3, projeto.getOrcamento());
			ps.setString(4, projeto.getDescricao());
			ps.setInt(5, projeto.getIdEmpresa());
			ps.setInt(6, projeto.getId());
			
			ps.execute();
			ps.close();

		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void deleter(Projeto projeto)
	{
		String sql = "DELETE FROM projeto WHERE id = ?";
		try
		{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, projeto.getId());
			
			ps.execute();
			ps.close();

		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public List<Projeto> listar()
	{
		String sql = "SELECT * FROM projeto";
		
		try
		{
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Projeto> lista = new ArrayList<Projeto>();
			
			while(rs.next())
			{
				Projeto projeto = new Projeto();
				projeto.setId(rs.getInt("id"));
				projeto.setNome(rs.getString("nome"));
				projeto.setDataIncio(rs.getDate("dataIncio"));
				projeto.setDataFim(rs.getDate("dataFim"));
				projeto.setOrcamento(rs.getDouble("orcamento"));
				projeto.setDescricao(rs.getString("descricao"));
				projeto.setIdEmpresa(rs.getInt("idEmpresa"));
				
				lista.add(projeto);
			}

			ps.close();
			rs.close();
			
			return lista;

		}catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public List<Projeto> procurar(String chave)
	{
		String sql = "SELECT * FROM projeto WHERE upper(nome) like ?";
		
		try
		{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + chave + "%");
			ResultSet rs = ps.executeQuery();
			List<Projeto> lista = new ArrayList<Projeto>();

			while(rs.next())
			{
				Projeto projeto = new Projeto();
				projeto.setId(rs.getInt("id"));
				projeto.setNome(rs.getString("nome"));
				projeto.setDataIncio(rs.getDate("dataInicio"));
				projeto.setDataFim(rs.getDate("dataFim"));
				projeto.setOrcamento(rs.getDouble("orcamento"));
				projeto.setDescricao(rs.getString("descricao"));
				projeto.setIdEmpresa(rs.getInt("idEmpresa"));
				
				lista.add(projeto);
			}
			
			ps.close();
			rs.close();

			return lista;

		} catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
}
