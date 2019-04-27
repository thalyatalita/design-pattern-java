package controller;

import dao.AdultoDAO;
import dao.CriancaDAO;
import model.Adulto;
import model.Cliente;
import model.Crianca;

public class ClienteFactoryMethod {
	public ClienteFactoryMethod() {}
	
	public Cliente getCliente(int tipo, String nome, String endereco, String idade, String cpf, String cartao) {
		if(tipo == 1) {
			AdultoDAO daoAdulto = new AdultoDAO();
			Adulto adulto = new Adulto();
			adulto.setNome(nome);
			adulto.setEndereco(endereco);
			adulto.setIdade(idade);
			adulto.setCpf(cpf);
			adulto.setNumeroCartaoDoSUS(cartao);
			daoAdulto.adiciona(adulto);
			return adulto;
		}
		else if(tipo == 2){
			CriancaDAO daoCrianca = new CriancaDAO();
			Crianca crianca = new Crianca();
			crianca.setNome(nome);
			crianca.setEndereco(endereco);
			crianca.setIdade(idade);
			crianca.setCpf(cpf);
			crianca.setNumeroCartaoDeVacina(cartao);
			daoCrianca.adiciona(crianca);
			return crianca;
		}
		else {
			return null;
		}
	}
}