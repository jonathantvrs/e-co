package controllers;

import entities.PEC;
import entities.PL;
import entities.PLP;
import interfaces.PropostaLegislativa;
import util.Projetos;
import util.Validador;

import java.util.HashMap;
import java.util.Map;

public class ProjetoController {

	private Map<String, PropostaLegislativa> propostas;

	public ProjetoController() {
		this.propostas = new HashMap<>();
	}

	private int contaProjetoEmAno(Projetos tipoProjeto, int ano) {
		int qntProjetosNoAno = 0;

		for (PropostaLegislativa proposta : this.propostas.values())
			if (proposta.getTipoDoProjeto().equals(tipoProjeto) && proposta.getAno() == ano)
				qntProjetosNoAno++;

		return qntProjetosNoAno;
	}

	private String criaCodigo(Projetos tipoProjeto, int ano) {
		int qntProjetosNoAno = contaProjetoEmAno(tipoProjeto, ano);

		qntProjetosNoAno++;

		StringBuilder codigo = new StringBuilder(tipoProjeto.toString() + " " + qntProjetosNoAno + "/" + ano);
		return codigo.toString();
	}

	public void cadastraPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		Validador v = new Validador();
		v.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		v.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		v.validaAno(ano);
		v.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		v.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		v.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		v.validaNull(conclusivo, "Erro ao cadastrar projeto: conclusivo nao pode ser nula");

		String codigo = criaCodigo(Projetos.PL, ano);
		this.propostas.put(codigo, new PL(dni, ano, ementa, interesses, url, conclusivo));
	}

	public void cadastraPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		Validador v = new Validador();
		v.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		v.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		v.validaAno(ano);
		v.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		v.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		v.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		v.validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");

		String codigo = criaCodigo(Projetos.PLP, ano);
		this.propostas.put(codigo, new PLP(dni, ano, ementa, interesses, url, artigos));
	}

	public void cadastraPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		Validador v = new Validador();
		v.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		v.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		v.validaAno(ano);
		v.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		v.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		v.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		v.validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");

		String codigo = criaCodigo(Projetos.PEC, ano);
		this.propostas.put(codigo, new PEC(dni, ano, ementa, interesses, url, artigos));
	}

	public String exibirProjeto(String codigo) {
		if (!(this.propostas.containsKey(codigo)))
			throw new NullPointerException("Erro ao exibir projeto: codigo nao cadastrado");

		return this.propostas.get(codigo).toString();
	}
}