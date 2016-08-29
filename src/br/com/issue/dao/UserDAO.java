package br.com.issue.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import br.com.issue.connection.ConnectionFactory;
import br.com.issue.model.User;

public class UserDAO {

	private DBCollection collection;

	public UserDAO() throws UnknownHostException
	{
		this.collection = ConnectionFactory.getConnection("usuario");
	}

	public boolean adicionar(User user)
	{
		
		BasicDBObject documento = new BasicDBObject();
		documento.put("nome" , user.getNome());
		documento.put("email", user.getEmail());
		documento.put("login", user.getLogin());
		documento.put("senha", user.getSenha());
		
		if(collection.insert(documento) != null)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean autenticar(User user)
	{
		BasicDBObject login = new BasicDBObject("login", user.getLogin());   
	    BasicDBObject senha = new BasicDBObject("senha", user.getSenha());
	    
	    BasicDBList andList = new BasicDBList();
	    andList.add(login);
	    andList.add(senha);
	    
	    BasicDBObject query = new BasicDBObject();
	    query.put("$and", andList);

        DBCursor cursor = collection.find(query);

        while (cursor.hasNext()) {
        	
        	BasicDBObject contatos1 = (BasicDBObject) cursor.next();
        	     	
        	if(contatos1.getString("login").equals(user.getLogin()) && contatos1.getString("senha").equals(user.getSenha()))
        	{
        		return true;
        	}
        
        }

        return false;
	}
	
	public List<User> getNome(User user)
	{
		List<User> objeto = new ArrayList<User>();
		BasicDBObject login = new BasicDBObject("login", user.getLogin());   
	    BasicDBObject senha = new BasicDBObject("senha", user.getSenha());
	    
	    BasicDBList andList = new BasicDBList();
	    andList.add(login);
	    andList.add(senha);
	    
	    BasicDBObject query = new BasicDBObject();
	    query.put("$and", andList);

        DBCursor cursor = collection.find(query);

        while (cursor.hasNext()) {
        	
        	BasicDBObject contatos1 = (BasicDBObject) cursor.next();
        	     	
        	if(contatos1.getString("login").equals(user.getLogin()) && contatos1.getString("senha").equals(user.getSenha()))
        	{
        		User usuario = new User();
        		usuario.setNome(contatos1.getString("nome"));
        		objeto.add(usuario);
        	}
        
        }

        return objeto;
	}
}
