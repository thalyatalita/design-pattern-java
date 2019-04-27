package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import factory.ConnectionFactory;
import model.Usuario;

public class UsuarioDAO {
	//instancia de conexao
	private Connection con;
	//instancia da conexao com o banco
	//para salvar o banco
	public UsuarioDAO() {
		this.con = new ConnectionFactory().getConnection();
	}
	//metodo de insersao
	public void adiciona(Usuario usuario) {
		//query para inserir os dados 
		String sql = "INSERT INTO usuario(nome, cpf, email, senha) VALUES(?,?,?,?)";
		try {
			//criando o statement
			PreparedStatement stmt = this.con.prepareStatement(sql);
			//salvando os dados passados na classe principal
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getCpf());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getSenha());
			//executando
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Usuario cadastrado");
			//fechando
			stmt.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//metodo de listar
	public List<Usuario> getList(){
		try {
			//lista de usuarios criada
			List<Usuario> usuarios = new ArrayList<Usuario>();
			//criando o statement e a query 
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM usuario");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				//criando o usuario pra guardar as informações do banco
				Usuario user = new Usuario();
				//atribuindo cada dado ao usuario
				user.setId(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setCpf(rs.getString("cpf"));
				user.setEmail(rs.getString("email"));
				//salvando na lista os usuario no banco
				usuarios.add(user);
			}
			//fechando o resulset
			rs.close();
			//fechando a conexão com o banco
			stmt.close();
			//retornando o usuarios do banco
			return usuarios;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//metodo de Delete
	public void remove(Usuario usuario) throws SQLException{
		String sql = "Delete from usuario where id=?";
		//criando o statement e a query
		PreparedStatement stmt = this.con.prepareStatement(sql);
		//definindo pro statement procurar pelo ID que é um inteiro
		stmt.setInt(1, usuario.getId());
		//executando 
		stmt.execute();
		JOptionPane.showMessageDialog(null, "Usuario removido");
		//fechando
		stmt.close();
	}
	public boolean edita(Usuario usuario) {
		//query de update na tabela usuario 
		String sql = "update usuario set nome=?, cpf=?, email=? where id=?";
		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			//pegando os dados que serao editados;
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getCpf());
			stmt.setString(3, usuario.getEmail());
			stmt.setInt(4, usuario.getId());
			//executando a query
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Usuario editado");
			//fechanco a conexao
			stmt.close();
			return true;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//metodo para logar 
	//parametros passado (nome e senha) do usuario
	public Usuario login(String nome, String senha) throws SQLException{
		Usuario usuario = null;
		//query de comparação
		String sql = "SELECT * FROM usuario WHERE nome=? AND senha=?";
		//statement preparado 
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, nome);
		stmt.setString(2, senha);
		//executandoo a busca com o resultset
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			usuario = new Usuario();
			usuario.setNome(rs.getString("nome"));
			usuario.setCpf(rs.getString("senha"));
		}
		//retorna o usuario
		return usuario;
	}
}