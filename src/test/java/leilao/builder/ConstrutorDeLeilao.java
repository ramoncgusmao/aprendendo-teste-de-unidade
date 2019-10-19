package leilao.builder;

import leilao.dominio.Lance;
import leilao.dominio.Leilao;
import leilao.dominio.Usuario;

public class ConstrutorDeLeilao {

	private Leilao leilao;

	public ConstrutorDeLeilao para(String produto) {
		// TODO Auto-generated method stub
		
		 this.leilao = new Leilao(produto);
		return this;
	}

	public ConstrutorDeLeilao lance(Usuario usuario, double valor) {
		
		 this.leilao.propoe( new Lance(usuario, valor));
		return this;
	}

	public Leilao builder() {
		// TODO Auto-generated method stub
		return this.leilao;
	}

}
