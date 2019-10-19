package leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<>();
	}
	
	public void propoe(Lance lance) {
		int total = 0;
		total = quantidadeLanceUsuario(lance, total);
		
		if(lances.isEmpty() || validaTeste(lance, total)) {
			lances.add(lance);
		}
	}

	private boolean validaTeste(Lance lance, int total) {
		return !usuarioIgual(lance) && total <5;
	}

	private int quantidadeLanceUsuario(Lance lance, int total) {
		for (Lance lanceUnico : lances) {
			if(lance.getUsuario().equals(lanceUnico.getUsuario())) {
				total++;
			}
		}
		return total;
	}

	private boolean usuarioIgual(Lance lance) {
		return ultimoLanceDado().getUsuario().equals(lance.getUsuario());
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size() -1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void dobraLance(Usuario usuario) {
		double valor = 0;
		valor = ultimoLanceDo(usuario, valor);
		
		propoe(new Lance(usuario, valor*2));
		
	}

	private double ultimoLanceDo(Usuario usuario, double valor) {
		for (Lance lance : lances) {
			if(lance.getUsuario().equals(usuario)) {
				valor = lance.getValor();
			}
		}
		return valor;
	}

	
	
}
