package matematica;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class AnoBissextoTest {

	@Test
	public void ehMultiploDe4() {
		
		AnoBissexto  ano = new AnoBissexto();
		int valor = 2016;
		
		assertEquals(true, ano.ehBissexto(valor));
		assertEquals(false, ano.ehBissexto(valor + 1));
		
	}
	
	@Test
	public void ehMultiploDe100() {
		
		AnoBissexto  ano = new AnoBissexto();
		int valor = 100;
		
		assertEquals(false, ano.ehBissexto(valor));
		assertEquals(false, ano.ehBissexto(valor + 1));
		
	}
	
	@Test
	public void ehMultiploDe400() {
		
		AnoBissexto  ano = new AnoBissexto();
		int valor = 800;
		
		assertEquals(true, ano.ehBissexto(valor));
		assertEquals(false, ano.ehBissexto(valor + 1));
		
	}
}
