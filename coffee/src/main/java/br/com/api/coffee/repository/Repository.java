package br.com.api.coffee.repository;

import java.sql.Connection;
import java.sql.SQLException;


public class Repository {

	protected static Connection connection;
	
	public Repository() {super();getConnection();}
	
	public static Connection getConnection() {
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			return connection;
		}catch(Exception e) {
			System.out.println("Erro nos parametros de conexão com o banco de dados" + e.getMessage());
		}
		return null;
	}
	
	public static void closeConnection() {
		try {
			if(!connection.isClosed()) {
				connection.close();
			}
		}catch(SQLException ex) {
			System.out.println("Erro ao encerrar a conexão" + "\n" + ex.getMessage());
		}
	}
}
