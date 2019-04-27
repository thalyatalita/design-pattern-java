package model;

//classe modelo de usuario
public class Usuario {
	private int id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	//construtor vazio
	public Usuario() {}
	//getters e setters
	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}