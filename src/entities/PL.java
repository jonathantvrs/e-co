package entities;

import util.Projetos;

/**
 * Essa classe representa um projeto de lei.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PL extends Projeto {
	
	private boolean conclusivo;
	
	public PL(String dniAutor, int ano, String ementa, String interesses, String endereco, boolean conclusivo) {
		super(dniAutor, ano, ementa, interesses, endereco);
		this.conclusivo = conclusivo;
		this.tipoDoProjeto = Projetos.PL;
	}

	@Override
	public String toString() {
		StringBuilder representacaoDeProjeto = new StringBuilder("Projeto de Lei - " + super.toString() + " - ");

		if (conclusivo)
			representacaoDeProjeto.append("Conclusiva");

		representacaoDeProjeto.append(" - " + this.exibeSituacaoAtual());

		return representacaoDeProjeto.toString();
	}
}