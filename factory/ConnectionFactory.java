package factory;

//importa as classes necessarias para o funcionamento
import java.sql.Connection;
//conexao SQL para java
import java.sql.DriverManager;
//driver de conexao SQL para java
import java.sql.SQLException;
//classe criar as conexões
public class ConnectionFactory {
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/clinica","root","123");
		}catch(SQLException excecao) {
			throw new RuntimeException(excecao);
		}
	}
}