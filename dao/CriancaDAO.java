package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import factory.ConnectionFactory;
import model.Crianca;


public class CriancaDAO {
	//instancia de conexao
	private Connection con;
	//instancia da conexao com o banco
	//para salvar o banco
	public CriancaDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
	//metodo de inserção
	public void adiciona(Crianca crianca) {
		//query para inserir os dados 
		String sql = "INSERT INTO pacienteCrianca(nome, endereco, idade, cpf, cartãoDeVacina) VALUES(?,?,?,?,?)";
		try {
			//criando o statement
			PreparedStatement stmt = this.con.prepareStatement(sql);
			//salvando os dados passados na classe principal
			stmt.setString(1, crianca.getNome());
			stmt.setString(2, crianca.getEndereco());
			stmt.setString(3, crianca.getIdade());
			stmt.setString(4, crianca.getCpf());
			stmt.setString(5, crianca.getNumeroCartaoDeVacina());
			//executando
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Paciente crianca cadastrado");
			//fechando
			stmt.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//metodo de listar
	public List<Crianca> getList(){
		try {
			//lista de usuarios criada
			List<Crianca> crianca = new ArrayList<Crianca>();
			//criando o statement e a query 
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM pacienteCrianca");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				//criando o usuario pra guardar as informações do banco
				Crianca user = new Crianca();
				//atribuindo cada dado ao usuario
				user.setId(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setEndereco(rs.getString("endereco"));
				user.setIdade(rs.getString("idade"));
				user.setCpf(rs.getString("cpf"));
				user.setNumeroCartaoDeVacina(rs.getString("cartãoDeVacina"));
				//salvando na lista os usuario no banco
				crianca.add(user);
			}
			//fechando o resulset
			rs.close();
			//fechando a conexão com o banco
			stmt.close();
			//retornando o usuarios do banco
			return crianca;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//metodo de Delete
	public void remove(Crianca crianca) throws SQLException{
		String sql = "Delete from pacienteCrianca where id=?";
		//criando o statement e a query
		PreparedStatement stmt = this.con.prepareStatement(sql);
		//definindo pro statement procurar pelo ID que é um inteiro
		stmt.setInt(1, crianca.getId());
		//executando 
		stmt.execute();
		JOptionPane.showMessageDialog(null, "Paciente crianca removido");
		//fechando
		stmt.close();
	}
	//metodo de Editar
	public boolean edita(Crianca crianca) {
		//query de update na tabela usuario 
		String sql = "update pacienteCrianca set nome=?, endereco=?, idade=?, cpf=?, cartãoDeVacina=? where id=?";
		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			//pegando os dados que serao editados;
			stmt.setString(1, crianca.getNome());
			stmt.setString(2, crianca.getEndereco());
			stmt.setString(3, crianca.getIdade());
			stmt.setString(4, crianca.getCpf());
			stmt.setString(5, crianca.getNumeroCartaoDeVacina());
			stmt.setInt(6, crianca.getId());

			//executando a query
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Paciente crianca editado");
			//fechanco a conexao
			stmt.close();
			return true;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}