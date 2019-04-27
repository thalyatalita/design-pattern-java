package model;

public class Crianca extends Cliente {
	private String numeroCartaoDeVacina;

	public Crianca() {}
	
	public String getNumeroCartaoDeVacina() {
		return numeroCartaoDeVacina;
	}
	public void setNumeroCartaoDeVacina(String numeroCartaoDeVacina) {
		this.numeroCartaoDeVacina = numeroCartaoDeVacina;
	}
}