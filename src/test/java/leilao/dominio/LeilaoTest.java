package leilao.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import leilao.builder.ConstrutorDeLeilao;

public class LeilaoTest {

	private Leilao leilao;
	private Usuario steveJobs;
	private Usuario billGates;
	private String produto;

	@BeforeEach
	public void inicializa() {
		produto = "MackBook pro 15";
		steveJobs = new Usuario("Steve jobs");
		billGates = new Usuario("Bill Gates");
	}
	
	@Test
	public void deveReceberUmLance() {
		leilao = new ConstrutorDeLeilao().para(produto).builder();
		assertEquals(0, leilao.getLances().size());
		
		leilao = new ConstrutorDeLeilao().para("Macbook pro 15")
										.lance(steveJobs, 2000)
										.builder();
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.000001);
	}


	@Test
	public void deveReceberVariosLances() {
		leilao = new ConstrutorDeLeilao().para(produto)
				.lance(steveJobs, 2000)
				.lance(billGates, 3000)
				.builder();
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.000001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.000001);
		
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
	
		leilao = new ConstrutorDeLeilao().para(produto)
				.lance(steveJobs, 2000.0)
				.lance(steveJobs, 3000.0)
				.builder();
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.000001);
	}
	
	
	@Test
	public void naoDeveAceitar5LancesDoMesmoUsuario() {
	
		leilao = new ConstrutorDeLeilao().para(produto)
				.lance(steveJobs, 2200.0)
				.lance(billGates, 3300.0)
				.lance(steveJobs, 2400.0)
				.lance(billGates, 3500.0)
				.lance(steveJobs, 2600.0)
				.lance(billGates, 3700.0)
				.lance(steveJobs, 2800.0)
				.lance(billGates, 3900.0)
				.lance(steveJobs, 3000.0)
				.lance(billGates, 4000.0)
				.lance(steveJobs, 5000.0)
				.builder();

		assertEquals(10, leilao.getLances().size());
	
	}
	
	@Test
	public void testeDobraLanceUsuarioRepetido() {
	
		leilao = new ConstrutorDeLeilao().para(produto).lance(steveJobs, 2200.0).builder();
		
		leilao.dobraLance(steveJobs);
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2200.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void testeDobraLance() {
	
		leilao = new ConstrutorDeLeilao().para(produto).lance(steveJobs, 2200.0).lance(billGates, 3300.0).builder();
		
		
		leilao.dobraLance(steveJobs);
		
		assertEquals(3, leilao.getLances().size());
		assertEquals(4400.0, leilao.getLances().get(2).getValor(), 0.00001);
	}
	
	@Test
	public void testeDobrarPrimeiroLance() {
	
		leilao = new ConstrutorDeLeilao().para(produto).lance(steveJobs, 2200.0).builder();
		
		
		leilao.dobraLance(billGates);
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2200.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void testeLanceNegativo() {
	
		
		assertThrows(IllegalArgumentException.class, () ->{
			leilao = new ConstrutorDeLeilao().para(produto).lance(steveJobs, -50.0).builder();
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			leilao = new ConstrutorDeLeilao().para(produto).lance(steveJobs, 0).builder();
		});
	}
	
	
}
