package app;

import models.Cliente;
import models.impl.Conta;
import models.impl.ContaCorrente;
import models.impl.ContaPoupanca;

public class Programa {

	public static void main(String[] args) {
		Cliente venilton = new Cliente();
		venilton.setNome("Venilton");
		
		Conta cc = new ContaCorrente(venilton);
		Conta poupanca = new ContaPoupanca(venilton);

		cc.depositar(100);
		cc.transferir(100, poupanca);
		
		cc.imprimirExtrato();
		System.out.println();
		poupanca.imprimirExtrato();
	
	}

}
