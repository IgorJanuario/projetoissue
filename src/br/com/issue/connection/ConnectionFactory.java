package br.com.issue.connection;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class ConnectionFactory {
	
	public static DBCollection getConnection(String nomeCollection) throws
	UnknownHostException
	{  
		// Conecta ao servidor mongodb
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // Conecta ao banco de dados test
        DB db = mongoClient.getDB( "test" );    
        DBCollection coll = db.getCollection(nomeCollection);

        return coll;
	}
}
