package leilao.servico;

import java.util.List;
import java.util.stream.Collectors;

import leilao.dominio.Lance;
import leilao.dominio.Leilao;

public class Avaliador {

	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double valorMedio;
	private List<Lance> maiores;
	public void avalia(Leilao leilao) {
		double valor = 0;
		for (Lance lance : leilao.getLances()) {
			if(lance.getValor() > getMaiorDeTodos()) maiorDeTodos = lance.getValor();
			if(lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
			valor += lance.getValor();
			
		}
		
		if(valor == 0) {
			valorMedio = 0;
		}else {
			
			valorMedio = valor/leilao.getLances().size();
		}
		
		// Comparator.reverseOrder()
		maiores = leilao.getLances().stream().sorted().collect(Collectors.toList());
		
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
		
		
		
	}

	public double getMaiorDeTodos() {
		return maiorDeTodos;
	}


	public double getMenorDeTodos() {
		return menorDeTodos;
	}
	
	public double getValorMedio() {
		return valorMedio;
	}

	public List<Lance> getTresMaiores() {
		// TODO Auto-generated method stub
		return maiores;
	}
	
}
