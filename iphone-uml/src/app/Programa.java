package app;

import models.Iphone;
import repository.Ipod;

public class Programa {

	public static void main(String[] args) {
		Ipod ipod = new Iphone("Ipod");
		ipod.selecionarMusica("Count me out - Kendrick Lamar");
		ipod.tocarMusica();
		ipod.pausarMusica();
		
		System.out.println();
		Iphone iphoneXr = new Iphone("Iphone XR");
		iphoneXr.exibirPagina("Google.com");
		iphoneXr.adicionarNovaAba();
		iphoneXr.iniciarCorreioVoz();
		iphoneXr.atender();
	}

}
