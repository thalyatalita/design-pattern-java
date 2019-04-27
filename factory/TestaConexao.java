package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
	//testa a conexao com o banco 
	public static void main(String[] args) throws SQLException {
		Connection con = new ConnectionFactory().getConnection();
		System.out.println("Conexao aberta");
		con.close();
	}
}