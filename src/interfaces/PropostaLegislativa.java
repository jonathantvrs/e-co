package interfaces;

import util.Projetos;

/**
 * Essa interface representa as possibilidades de propostas legislativas 
 * consideradas no sistema.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */

public interface PropostaLegislativa {

	public String exibeSituacaoAtual();
	
	/**
	 * Esse método retorna a representaçao em String de um projeto de lei
	 */
	public String toString();
	
	public int getAno();
	
	public Projetos getTipoDoProjeto();
}