package leilao.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LeilaoTest {

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = macBook();
		
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve jobs"), 2000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.000001);
	}

	private Leilao macBook() {
		return new Leilao("Macbook pro 15");
	}
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = macBook();

		
		leilao.propoe(new Lance(new Usuario("Steve jobs"), 2000));
		leilao.propoe(new Lance(new Usuario("Ramon"), 3000));
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.000001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.000001);
		
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
	
		Leilao leilao = macBook();
		
		Usuario steveJobs = new Usuario("Steve jobs");
		
		leilao.propoe(new Lance(steveJobs, 2000.0));
		leilao.propoe(new Lance(steveJobs, 3000.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.000001);
	}
	
	
	@Test
	public void naoDeveAceitar5LancesDoMesmoUsuario() {
	
		Leilao leilao = macBook();
		
		Usuario steveJobs = new Usuario("Steve jobs");
		Usuario billGates = new Usuario("Bill Gates");
		
		leilao.propoe(new Lance(steveJobs, 2200.0));
		leilao.propoe(new Lance(billGates, 3300.0));
		leilao.propoe(new Lance(steveJobs, 2400.0));
		leilao.propoe(new Lance(billGates, 3500.0));		
		leilao.propoe(new Lance(steveJobs, 2600.0));
		leilao.propoe(new Lance(billGates, 3700.0));		
		leilao.propoe(new Lance(steveJobs, 2800.0));
		leilao.propoe(new Lance(billGates, 3900.0));
		leilao.propoe(new Lance(steveJobs, 3000.0));
		leilao.propoe(new Lance(billGates, 4000.0));
		leilao.propoe(new Lance(steveJobs, 5000.0));

		assertEquals(10, leilao.getLances().size());
	
	}
	
	@Test
	public void testeDobraLanceUsuarioRepetido() {
	
		Leilao leilao = macBook();
		
		Usuario steveJobs = new Usuario("Steve jobs");
		leilao.propoe(new Lance(steveJobs, 2200.0));
		
		leilao.dobraLance(steveJobs);
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2200.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	public void testeDobraLance() {
		Leilao leilao = macBook();
		
		Usuario steveJobs = new Usuario("Steve jobs");
		Usuario billGates = new Usuario("Bill Gates");
		
		leilao.propoe(new Lance(steveJobs, 2200.0));
		leilao.propoe(new Lance(billGates, 3300.0));
		
		leilao.dobraLance(steveJobs);
		
		assertEquals(3, leilao.getLances().size());
		assertEquals(4400.0, leilao.getLances().get(2).getValor(), 0.00001);
	}
	
	public void testeDobrarPrimeiroLance() {
		Leilao leilao = macBook();
		
		Usuario steveJobs = new Usuario("Steve jobs");
		Usuario billGates = new Usuario("Bill Gates");
		
		leilao.propoe(new Lance(steveJobs, 2200.0));

		
		leilao.dobraLance(billGates);
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2200.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
}
