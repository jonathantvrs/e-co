package entities;

import util.Projetos;

public class PLP extends Projeto{
	
	private String artigos;
	
	public PLP(String dniAutor, int ano, String ementa, String interesses, String endereco, String artigos) {
		super(dniAutor, ano, ementa, interesses, endereco);
		this.artigos = artigos;
		this.tipoDoProjeto = Projetos.PLP;
	}

	@Override
	public String toString() {
		StringBuilder representacaoDeProjeto = new StringBuilder("Projeto de Lei Complementar - " + super.toString() + " - " + this.getArtigos() + " - " + this.exibeSituacaoAtual());

		return representacaoDeProjeto.toString();
	}

	private String getArtigos() {
		return this.artigos.replace(",", ", ");
	}
}