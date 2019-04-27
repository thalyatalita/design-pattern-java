package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import factory.ConnectionFactory;
import model.Adulto;

public class AdultoDAO {
	//instancia de conexao
	private Connection con;
	//instancia da conexao com o banco
	//para salvar o banco
	public AdultoDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
	//metodo de inserção
	public void adiciona(Adulto adulto) {
		//query para inserir os dados 
		String sql = "INSERT INTO pacienteAdulto (nome, endereco, idade, cpf, cartãoDoSus) VALUES(?,?,?,?,?)";
		try {
			//criando o statement
			PreparedStatement stmt = this.con.prepareStatement(sql);
			//salvando os dados passados na classe principal
			stmt.setString(1, adulto.getNome());
			stmt.setString(2, adulto.getEndereco());
			stmt.setString(3, adulto.getIdade());
			stmt.setString(4, adulto.getCpf());
			stmt.setString(5, adulto.getNumeroCartaoDoSUS());
			//executando
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Paciente cadastrado");
			//fechando
			stmt.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//metodo de listar
	public List<Adulto> getList(){
		try {
			//lista de usuarios criada
			List<Adulto> adulto = new ArrayList<Adulto>();
			//criando o statement e a query 
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM pacienteAdulto ");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				//criando o usuario pra guardar as informações do banco
				Adulto user = new Adulto();
				//atribuindo cada dado ao usuario
				user.setId(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setEndereco(rs.getString("endereco"));
				user.setIdade(rs.getString("idade"));
				user.setCpf(rs.getString("cpf"));
				user.setNumeroCartaoDoSUS(rs.getString("cartãoDoSus"));
				//salvando na lista os usuario no banco
				adulto.add(user);
			}
			//fechando o resulset
			rs.close();
			//fechando a conexão com o banco
			stmt.close();
			//retornando o usuarios do banco
			return adulto;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//metodo de Delete
	public void remove(Adulto adulto) throws SQLException{
		String sql = "Delete from pacienteAdulto  where id=?";
		//criando o statement e a query
		PreparedStatement stmt = this.con.prepareStatement(sql);
		//definindo pro statement procurar pelo ID que é um inteiro
		stmt.setInt(1, adulto.getId());
		//executando 
		stmt.execute();
		JOptionPane.showMessageDialog(null, "Paciente adulto removido");
		//fechando
		stmt.close();
	}
	//metodo de Editar
	public boolean edita(Adulto adulto) {
		//query de update na tabela usuario 
		String sql = "update pacienteAdulto set nome=?, endereco=?, idade=?, cpf=?, cartãoDoSus=? where id=?";
		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			//pegando os dados que serao editados;
			stmt.setString(1, adulto.getNome());
			stmt.setString(2, adulto.getEndereco());
			stmt.setString(3, adulto.getIdade());
			stmt.setString(4, adulto.getCpf());
			stmt.setString(5, adulto.getNumeroCartaoDoSUS());
			stmt.setInt(6, adulto.getId());
			//executando a query
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Paciente editado");
			//fechanco a conexao
			stmt.close();
			return true;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}