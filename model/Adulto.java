package model;

public class Adulto extends Cliente {
	private String numeroCartaoDoSUS;
	
	public Adulto() {}
	
	public String getNumeroCartaoDoSUS() {
		return numeroCartaoDoSUS;
	}
	public void setNumeroCartaoDoSUS(String numeroCartaoDoSUS) {
		this.numeroCartaoDoSUS = numeroCartaoDoSUS;
	}
}