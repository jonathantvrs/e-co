package entities;

import enums.SituacaoVotacao;
import enums.StatusGovernistas;
import enums.TipoDeProjetos;
import interfaces.PropostaLegislativa;

import java.util.ArrayList;
import java.util.List;

/**
 * Essa classe representa XXXX.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public abstract class Projeto implements PropostaLegislativa {

    protected TipoDeProjetos tipoDoProjeto;
    private String codigo;
    private String dniAutor;
    private int ano;
    private String ementa;
    private String interesses;
    private String endereco;
    private List<String[]> votacoes; // Local e Situação

    public Projeto(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco) {
        super();
        this.codigo = codigo;
        this.dniAutor = dniAutor;
        this.ano = ano;
        this.ementa = ementa;
        this.interesses = interesses;
        this.endereco = endereco;
        this.votacoes = new ArrayList<>();
        this.votacoes.add(new String[] {"CCJC", SituacaoVotacao.EM_VOTACAO.toString()});
    }

    public TipoDeProjetos getTipoDoProjeto() {
        return this.tipoDoProjeto;
    }

    public String exibeSituacaoAtual() {
        if (this.getSituacaoAtual().equals(SituacaoVotacao.ARQUIVADO.toString()))
            return "ARQUIVADO";

        else if (this.getSituacaoAtual().equals(SituacaoVotacao.APROVADO.toString()))
            return "APROVADO";

        StringBuilder situacaoAtual = new StringBuilder(this.getSituacaoAtual() + " (" + this.getLocalDeVotacao() + ")");
        return situacaoAtual.toString();
    }

    public int getAno() {
        return this.ano;
    }

    public String getLocalDeVotacao(){
        return this.votacoes.get(this.votacoes.size() - 1)[0];
    }

    public void setNovoLocalDeVotacao(String novoLocalDeVotacao){
        this.votacoes.add(new String[] {novoLocalDeVotacao, SituacaoVotacao.EM_VOTACAO.toString()});
    }

    public String getSituacaoAtual(){
        return this.votacoes.get(this.votacoes.size() - 1)[1].replace("_", " ");
    }

    public void alteraSituacaoDoLocalAnterior(SituacaoVotacao situacao) {
        this.votacoes.get(this.votacoes.size() - 2)[1] = situacao.toString();
    }

    public String getInteresses() {
        return this.interesses;
    }

    public void encerraVotacao() {
        this.votacoes.add(new String[] {"", SituacaoVotacao.ARQUIVADO.toString()});
    }

    public void aprovaVotacao(){
        this.votacoes.add(new String[] {"", SituacaoVotacao.APROVADO.toString()});
    }

    public String getAutor(){
        return this.dniAutor;
    }

    public boolean votaComissao(int qntPoliticosFavoraveis, int qntDePoliticosDaComissao, StatusGovernistas status) {
        boolean resultado = false;

        if (qntPoliticosFavoraveis >= qntDePoliticosDaComissao / 2 + 1)
            resultado = true;

        if (status == StatusGovernistas.OPOSICAO)
            resultado = !resultado;

        return resultado;
    }

    public void alteraNovoLocal(String proximoLocal, PropostaLegislativa proposta) {
        if (proximoLocal.equals("plenario")) {
            this.setNovoLocalDeVotacao("Plenario - 1o turno");
        } else {
            this.setNovoLocalDeVotacao(proximoLocal);
        }
    }


    @Override
    public String toString() {
        return this.codigo + " - " + this.dniAutor + " - " + this.ementa;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Projeto other = (Projeto) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }
}
