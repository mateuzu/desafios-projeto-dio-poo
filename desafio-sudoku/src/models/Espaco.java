package models;

public class Espaco {

	private Integer numeroAtual;
	private final int numeroEsperado;
	private final boolean posicaoFixa;
	
	public Espaco(int numeroEsperado, boolean posicaoFixa) {
		this.numeroEsperado = numeroEsperado;
		this.posicaoFixa = posicaoFixa;
		if(posicaoFixa) {
			numeroAtual = numeroEsperado;
		}
	}

	public Integer getNumeroAtual() {
		return numeroAtual;
	}

	public void setNumeroAtual(Integer numeroAtual) {
		if(posicaoFixa) return;
		this.numeroAtual = numeroAtual;
	}
	
	public void limparEspaco() {
		setNumeroAtual(null);
	}

	public int getNumeroEsperado() {
		return numeroEsperado;
	}

	public boolean isPosicaoFixa() {
		return posicaoFixa;
	}
	
}
