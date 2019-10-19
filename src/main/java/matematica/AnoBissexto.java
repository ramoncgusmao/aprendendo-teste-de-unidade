package matematica;

public class AnoBissexto {

	public boolean ehBissexto(int valor) {

		
		if (valor % 400 == 0)
			return true;
		if (valor % 100 == 0)
			return false;
		else if (valor % 4 == 0)
			return true;

		return false;
	}

}
