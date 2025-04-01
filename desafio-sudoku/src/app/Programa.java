package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import models.Espaco;
import models.Tabuleiro;
import utils.ModeloTabuleiro;

public class Programa {

	private final static Scanner SCANNER = new Scanner(System.in);
	private static Tabuleiro tabuleiro;
	private final static int LIMITE_TABULEIRO = 9;

	public static void main(String[] args) {
		final var posicoes = Stream.of(args)
				.collect(Collectors.toMap(chave -> chave.split(";")[0], valor -> valor.split(";")[1]));

		int opcao = -1;

		while (true) {
			System.out.println("Selecione uma das opções a seguir");
			System.out.println("1 - Iniciar um novo Jogo");
			System.out.println("2 - Colocar um novo número");
			System.out.println("3 - Remover um número");
			System.out.println("4 - Visualizar jogo atual");
			System.out.println("5 - Verificar status do jogo");
			System.out.println("6 - limpar jogo");
			System.out.println("7 - Finalizar jogo");
			System.out.println("8 - Sair");

			opcao = SCANNER.nextInt();

			switch (opcao) {
			case 1 -> comecarJogo(posicoes);
			case 2 -> colocarNumero();
			case 3 -> removerNumero();
			case 4 -> mostrarJogoAtual();
			case 5 -> mostrarStatusJogo();
			case 6 -> limparJogo();
			case 7 -> finalizarJogo();
			case 8 -> System.exit(0);
			default -> System.out.println("Opção inválida, selecione uma das opções do menu");
			}
		}
	}

	private static void comecarJogo(final Map<String, String> posicoes) {
		if (tabuleiro != null) {
			System.out.println("O jogo já foi iniciado");
			return;
		}

		List<List<Espaco>> espacos = new ArrayList<>();
		for (int i = 0; i < LIMITE_TABULEIRO; i++) {
			espacos.add(new ArrayList<>());
			for (int j = 0; j < LIMITE_TABULEIRO; j++) {
				var posicoesConfig = posicoes.get("%s,%s".formatted(i, j));
				var numeroEsperado = Integer.parseInt(posicoesConfig.split(",")[0]);
				var posicaoFixa = Boolean.parseBoolean(posicoesConfig.split(",")[1]);
				var espacoAtual = new Espaco(numeroEsperado, posicaoFixa);
				espacos.get(i).add(espacoAtual);
			}
		}

		tabuleiro = new Tabuleiro(espacos);
		System.out.println("O jogo está pronto para começar");
	}

	private static void colocarNumero() {
		if (tabuleiro == null) {
			System.out.println("O jogo ainda não foi iniciado iniciado");
			return;
		}

		System.out.println("Informe a coluna que em que o número será inserido");
		var coluna = obterNumeroValido(0, 8);
		System.out.println("Informe a linha que em que o número será inserido");
		var linha = obterNumeroValido(0, 8);
		System.out.printf("Informe o número que vai entrar na posição [%s,%s]\n", coluna, linha);
		var valor = obterNumeroValido(1, 9);
		if (!tabuleiro.mudarValor(coluna, linha, valor)) {
			System.out.printf("A posição [%s,%s] tem um valor fixo\n", coluna, linha);
		}
	}

	private static void removerNumero() {
		if (tabuleiro == null) {
			System.out.println("O jogo ainda não foi iniciado iniciado");
			return;
		}

		System.out.println("Informe a coluna que em que o número será inserido");
		var coluna = obterNumeroValido(0, 8);
		System.out.println("Informe a linha que em que o número será inserido");
		var linha = obterNumeroValido(0, 8);
		if (!tabuleiro.limparValor(coluna, linha)) {
			System.out.printf("A posição [%s,%s] tem um valor fixo\n", coluna, linha);
		}
	}

	private static void mostrarJogoAtual() {
		if (tabuleiro == null) {
			System.out.println("O jogo ainda não foi iniciado iniciado");
			return;
		}

		var args = new Object[81];
		var argPosicao = 0;
		for (int i = 0; i < LIMITE_TABULEIRO; i++) {
			for (var col : tabuleiro.getEspacos()) {
				args[argPosicao++] = " " + (col.get(i).getNumeroAtual() == null ? " " : col.get(i).getNumeroAtual());
			}
		}
		System.out.println("Seu jogo se encontra da seguinte forma");
		System.out.printf((ModeloTabuleiro.MODELO_TABULEIRO) + "\n", args);
	}

	private static void mostrarStatusJogo() {
		if (tabuleiro == null) {
			System.out.println("O jogo ainda não foi iniciado iniciado");
			return;
		}

		System.out.printf("O jogo atualmente se encontra no status %s\n", tabuleiro.getStatusJogo());
		if (tabuleiro.possuiErros()) {
			System.out.println("O jogo contém erros");
		} else {
			System.out.println("O jogo não contém erros");
		}
	}

	private static void limparJogo() {
		if (tabuleiro == null) {
			System.out.println("O jogo ainda não foi iniciado iniciado");
			return;
		}

		System.out.println("Tem certeza que deseja limpar seu jogo e perder todo seu progresso?");
		var confirma = SCANNER.next();
		while (!confirma.equalsIgnoreCase("sim") && !confirma.equalsIgnoreCase("não")) {
			System.out.println("Informe 'sim' ou 'não'");
			confirma = SCANNER.next();
		}

		if (confirma.equalsIgnoreCase("sim")) {
			tabuleiro.reset();
		}
	}

	private static void finalizarJogo() {
		if (tabuleiro == null) {
			System.out.println("O jogo ainda não foi iniciado iniciado");
			return;
		}

		if (tabuleiro.jogoFoiFinalizado()) {
			System.out.println("Parabéns você concluiu o jogo");
			mostrarJogoAtual();
			tabuleiro = null;
		} else if (tabuleiro.possuiErros()) {
			System.out.println("Seu jogo conté, erros, verifique seu Tabuleiro e ajuste-o");
		} else {
			System.out.println("Você ainda precisa preenhcer algum espaço");
		}
	}

	private static int obterNumeroValido(final int min, final int max) {
		var atual = SCANNER.nextInt();
		while (atual < min || atual > max) {
			System.out.printf("Informe um número entre %s e %s\n", min, max);
			atual = SCANNER.nextInt();
		}
		return atual;
	}

}
