package leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import leilao.dominio.Lance;
import leilao.dominio.Leilao;
import leilao.dominio.Usuario;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Avaliador leiloeiro = builder();
		
		double maiorEsperado = 450;
		double menorEsperado = 300;
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.0001); 
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001); 
	
	}

	@Test
	public void deveDarAMediaDosValores() {
		Avaliador leiloeiro = builder();
		
		double media = 383.3334;

		assertEquals(media, leiloeiro.getValorMedio(), 0.0001); 

	}
	
	private Avaliador builder() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Play 3");
		
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(maria, 450.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		return leiloeiro;
	}
	
	@Test
    public void testaMediaDeZeroLance(){

        // cenario
        Usuario ewertom = new Usuario("Ewertom");

        // acao
        Leilao leilao = new Leilao("Iphone 7");

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        //validacao
        assertEquals(0, avaliador.getValorMedio(), 0.0001);

    }
	

	
	
	@Test
	public void deveEncontrarOsTresMaioresLance() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Play 3");
		
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(maria, 600.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 900.0));
		leilao.propoe(new Lance(jose, 1000.0));
		leilao.propoe(new Lance(maria, 800.0));
		
	
		
		Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        //validacao
        List<Lance> maiores = avaliador.getTresMaiores();
        assertEquals(3, maiores.size());
        
        assertEquals(1000, maiores.get(0).getValor(), 0.00001);
        assertEquals(900, maiores.get(1).getValor(), 0.00001);
        assertEquals(800, maiores.get(2).getValor(), 0.00001);
	}
	
	
	@Test
	public void deveEncontrarEmOrdemDescrescente() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Play 3");
		
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(jose, 100.0));
		
		Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        //validacao
		assertEquals(400, avaliador.getMaiorDeTodos(), 0.0001); 
		assertEquals(100, avaliador.getMenorDeTodos(), 0.00001); 
	}
	
}
