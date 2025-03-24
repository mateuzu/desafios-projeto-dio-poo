package models;

import repository.Ipod;
import repository.NavegadorInternet;
import repository.Telefone;

public class Iphone implements Ipod, Telefone, NavegadorInternet{
	
	private String modelo;
	
	public Iphone(String modelo) {
		this.modelo = modelo;
	}
	
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public void exibirPagina(String url) {
		System.out.println(this.modelo +  " Exibindo página " + url);
	}

	@Override
	public void adicionarNovaAba() {
		int abas = 0;
		abas++;
		System.out.println(this.modelo +  " Nova aba adicionada!");
		System.out.println(this.modelo +  " Total de abas abertas: " + abas);
	}

	@Override
	public void atualizarPagina() {
		System.out.println(this.modelo +  " Página atualizada");
	}

	@Override
	public void ligar(String numero) {
		System.out.println(this.modelo +  " Realizando ligação para " + numero);
		
		for(int i = 0; i < 2; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(this.modelo + " Tuuuuum...");
		}
		
	}

	@Override
	public void atender() {
		System.out.println(this.modelo + " Ligação atendida");
	}

	@Override
	public void iniciarCorreioVoz() {
		System.out.println(this.modelo + " Correio de voz iniciado");
	}

	@Override
	public void selecionarMusica(String musica) {
		System.out.println(this.modelo + " Musica selecionada " + musica);
	}

	@Override
	public void tocarMusica() {
		System.out.println(this.modelo + " Musica tocando...");
	}

	@Override
	public void pausarMusica() {
		System.out.println(this.modelo + " Música pausda");
	}

}
