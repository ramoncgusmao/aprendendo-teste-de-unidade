package leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import leilao.builder.ConstrutorDeLeilao;
import leilao.dominio.Lance;
import leilao.dominio.Leilao;
import leilao.dominio.Usuario;

public class TesteDoAvaliador {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@BeforeEach
	private void builder() {
		this.leiloeiro = new Avaliador();

		this.joao = new Usuario("Joao");
		this.jose = new Usuario("Jose");
		this.maria = new Usuario("Maria");

	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		Leilao leilao = new ConstrutorDeLeilao().para("Play 3").lance(maria, 300.0).lance(joao, 400.0)
				.lance(maria, 450.0).builder();

		leiloeiro.avalia(leilao);
		double maiorEsperado = 450;
		double menorEsperado = 300;

		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.0001);
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);

	}

	@Test
	public void deveDarAMediaDosValores() {

		Leilao leilao = new ConstrutorDeLeilao().para("Play 3").lance(maria, 300.0).lance(jose, 400.0)
				.lance(maria, 450.0).builder();

		leiloeiro.avalia(leilao);
		double media = 383.3334;

		assertEquals(media, leiloeiro.getValorMedio(), 0.0001);

	}

	@Test
	public void testaMediaDeZeroLance() {

		Leilao leilao = new ConstrutorDeLeilao().para("Play 3").builder();

		leiloeiro.avalia(leilao);

		assertEquals(0, leiloeiro.getValorMedio(), 0.0001);

	}

	@Test
	public void deveEncontrarOsTresMaioresLance() {

		Leilao leilao = new ConstrutorDeLeilao().para("Play 3").lance(joao, 400.0).lance(joao, 400.0).lance(jose, 300.0)
				.lance(maria, 200.0).lance(jose, 400.0).lance(maria, 600.0).lance(jose, 300.0).lance(maria, 900.0)
				.lance(jose, 1000.0).lance(maria, 800.0).builder();

		leiloeiro.avalia(leilao);

		// validacao
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());

		assertEquals(1000, maiores.get(0).getValor(), 0.00001);
		assertEquals(900, maiores.get(1).getValor(), 0.00001);
		assertEquals(800, maiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void deveEncontrarEmOrdemDescrescente() {

		Leilao leilao = new ConstrutorDeLeilao().para("Play 3").lance(joao, 400.0).lance(jose, 300.0)
				.lance(maria, 200.0).lance(jose, 100.0).builder();

		leiloeiro.avalia(leilao);

		// validacao
		assertEquals(400, leiloeiro.getMaiorDeTodos(), 0.0001);
		assertEquals(100, leiloeiro.getMenorDeTodos(), 0.00001);
	}
	


}
