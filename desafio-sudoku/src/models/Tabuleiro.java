package models;

import java.util.Collection;
import java.util.List;

import models.enums.StatusJogo;

public class Tabuleiro {

	private final List<List<Espaco>> espacos;

	public Tabuleiro(List<List<Espaco>> espacos) {
		this.espacos = espacos;
	}

	public List<List<Espaco>> getEspacos() {
		return espacos;
	}

	public StatusJogo getStatusJogo() {
		if(espacos.stream().flatMap(Collection::stream).noneMatch(e -> !e.isPosicaoFixa() && e.getNumeroAtual() != null)) {
			return StatusJogo.NAO_INICIADO;
		}
		
		return espacos.stream().flatMap(Collection::stream).anyMatch(e -> e.getNumeroAtual() == null) ? StatusJogo.IMCOMPLETO : StatusJogo.COMPLETO;
	}
	
	public boolean possuiErros() {
		if(getStatusJogo() == StatusJogo.NAO_INICIADO) {
			return false;
		}
		
		return espacos.stream().flatMap(Collection::stream)
				.anyMatch(e -> e.getNumeroAtual() != null && !e.getNumeroAtual().equals(e.getNumeroEsperado()));
	}
	
	public boolean mudarValor(final int coluna, final int linha, final Integer valor) {
		var espaco = espacos.get(coluna).get(linha);
		
		if(espaco.isPosicaoFixa()) {
			return false;
		}
		
		espaco.setNumeroAtual(valor);
		return true;
	}
	
	public boolean limparValor(final int coluna, final int linha) {
		var espaco = espacos.get(coluna).get(linha);
		
		if(espaco.isPosicaoFixa()) {
			return false;
		}
		
		espaco.limparEspaco();
		return true;
	}
	
	public void reset() {
		espacos.forEach(coluna -> coluna.forEach(Espaco::limparEspaco));
	}

	public boolean jogoFoiFinalizado() {
		return !possuiErros() && getStatusJogo() == StatusJogo.COMPLETO;
	}
	
}
