package entities;

import enums.SituacaoVotacao;
import enums.StatusGovernista;
import enums.TipoProjeto;
import interfaces.PropostaLegislativa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Essa classe representa a abstração de uma Proposta Legislativa que tramita no Sistema.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public abstract class Projeto implements PropostaLegislativa, Serializable {

    /**
     * Armazena Id de Serialização do objeto
     */
    private static final long serialVersionUID = 100476511324712155L;
    
    /**
     * Número de projetos já cadastrados.
     */
    private static int contagemDeProjetos = 0;
    
    /**
     * Armazena um enum do que cartacteriza o tipo do projeto em analise,
     * pode ser do tipo PL, PLP ou PEC.
     */
    private TipoProjeto tipoDoProjeto;
    /**
     * Armazena uma String codigo do projeto.
     * Atributo responsavel por identificar o projeto. Eh composto pela sequencia cronologica
     * de projetos do mesmo tipo naquele ano seguida por este ano.
     */
    private String codigo;
    /**
     * Armazena a Dni do autor do projeto.
     */
    private String dniAutor;
    /**
     * Armazena ano no qual o projeto foi criado.
     */
    private int ano;
    /**
     * Armazena uma String contendo uma breve descricao do projeto.
     */
    private String ementa;
    /**
     * Armazena os interesses realcionados ao projeto.
     */
    private String interesses;
    /**
     * Armazena uma String com a url de endereço do projeto.
     */
    private String endereco;
    /**
     * Armazena uma lista com os locais de votação por onde o projeto passou.
     */
    private List<String[]> votacoes; // Local e Situação

    /**
     * Número de identificação sequencial do projeto.
     */
    private int numCriacaoProjeto;
    
    /**
     * Constrói um projeto inicializando a lista com os locais de votação em CCJC e a situaçao em votação.
     */
    public Projeto(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco) {
        super();
        Projeto.contagemDeProjetos++;
        this.numCriacaoProjeto = Projeto.contagemDeProjetos;
        this.codigo = codigo;
        this.dniAutor = dniAutor;
        this.ano = ano;
        this.ementa = ementa;
        this.interesses = interesses;
        this.endereco = endereco;
        this.votacoes = new ArrayList<>();
        this.votacoes.add(new String[]{"CCJC", SituacaoVotacao.EM_VOTACAO.toString()});
        
    }

    /**
     * Método que retorna o tipo do projeto.
     *
     * @return o tipo do projeto.
     */
    public TipoProjeto getTipoDoProjeto() {
        return this.tipoDoProjeto;
    }

    /**
     * Esse método altera o tipo do projeto
     *
     * @param tipoDoProjeto tipo do projeto
     */
    public void setTipoDoProjeto(TipoProjeto tipoDoProjeto) {
        this.tipoDoProjeto = tipoDoProjeto;
    }

    /**
     * Esse método retorna uma string contendo informaçoes acerca do estado atual do projeto.
     *
     * @return string no formato Situaçao do projeto seguida pelo local onde o projeto foi votado.
     */
    protected String exibeSituacaoAtual() {
        if (this.getSituacaoAtual().equals(SituacaoVotacao.REJEITADO.toString()))
            return "REJEITADO";

        else if (this.getSituacaoAtual().equals(SituacaoVotacao.APROVADO.toString()))
            return "APROVADO";

        return this.getSituacaoAtual() + " (" + this.getLocalDeVotacao() + ")";
    }

    /**
     * Retorna a sequência de votações pela qual o projeto já passou.
     *
     * @return lista com todas as votações pela qual o projeto já passou, em ordem cronológica
     */
    public List<String[]> getVotacoes() {
        return this.votacoes;
    }

    /**
     * Retorna o código do projeto.
     *
     * @return String com o código do projeto.
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Retorna o ano de criaçao do projeto.
     *
     * @return inteiro que representa o ano de criacao do projeto.
     */
    public int getAno() {
        return this.ano;
    }

    /**
     * Retorna o local onde o projeto foi votado.
     *
     * @return uma String contendo o endereço onde o projeto votado.
     */
    public String getLocalDeVotacao() {
        return this.votacoes.get(this.votacoes.size() - 1)[0];
    }

    /**
     * Altera o local onde o projeto sera votado.
     */
    public void setNovoLocalDeVotacao(String novoLocalDeVotacao) {
        this.votacoes.add(new String[]{novoLocalDeVotacao, SituacaoVotacao.EM_VOTACAO.toString()});
    }

    /**
     * Retorna uma String com a situacao do projeto.
     *
     * @return String contendo a situacao atual do projeto,
     * que pode ser em votação, aprovado, rejeitado ou arquivado.
     */
    public String getSituacaoAtual() {
        return this.votacoes.get(this.votacoes.size() - 1)[1].replace("_", " ");
    }

    /**
     * Retorna o número sequencial(ordinal) de cadastro do projeto.
     */
    public int getNumCriacaoProjeto() {
    	return this.numCriacaoProjeto;
    }

    /**
     * Método que altera o resultado da votação no último local onde ela foi votada.
     */
    public void alteraSituacaoDoUltimoLocal(SituacaoVotacao situacao) {
        this.votacoes.get(this.votacoes.size() - 1)[1] = situacao.toString();
    }

    /**
     * Esse método retorna os interesses referentes ao projeto.
     *
     * @return string dos interesses referentes ao projeto.
     */
    public String getInteresses() {
        return this.interesses;
    }

    /**
     * Altera o estado da votação para rejeitado.
     */
    protected void encerraVotacao() {
        if (this.getSituacaoAtual().equals("EM VOTACAO"))
            this.alteraSituacaoDoUltimoLocal(SituacaoVotacao.REJEITADO);
    }

    /**
     * Altera o estado da votação para aprovado.
     */
    protected void aprovaVotacao() {
        if (this.getSituacaoAtual().equals("EM VOTACAO"))
            this.alteraSituacaoDoUltimoLocal(SituacaoVotacao.APROVADO);
    }

    /**
     * Retorna a dni do autor do projeto.
     *
     * @return string contendo a dni do autor do projeto.
     */
    public String getAutor() {
        return this.dniAutor;
    }

    /**
     * Esse método realiza a votação de uma comissão de acordo com a quantidade
     * de políticos a favor e a quantidade total de políticos da comissão.
     *
     * @param qntDePoliticosDaComissao a quantidade de políticos da comissão.
     * @param qntPoliticosFavoraveis   a quantidade de políticos a favor.
     * @param status                   o status da votação.
     */
    public boolean votarComissao(int qntPoliticosFavoraveis, int qntDePoliticosDaComissao, StatusGovernista status) {
        boolean resultado = false;

        if (qntPoliticosFavoraveis >= qntDePoliticosDaComissao / 2 + 1)
            resultado = true;

        return (status == StatusGovernista.OPOSICAO) != resultado;
    }

    /**
     * Esse método altera o próximo local de votação da comissão. Fazendo a análise para
     * caso o mesmo vá para o plenário e o encaminha para o primeiro turno.
     *
     * @param proximoLocal o próximo local de votação.
     */
    public abstract void alteraNovoLocal(String proximoLocal);

    /**
     * Esse método verifica se existe o quórum mínimo para que seja possível realizar a votação.
     * Possuindo diferentes cálculos para os tipos de projeto.
     *
     * @param qntDeputadosPresentes a quantidade de deputados presentes.
     * @param qntTotalDeputado      a quantidade total de deputados cadastrados.
     */
    public abstract void verificaQuorumMinimo(int qntDeputadosPresentes, int qntTotalDeputado);

    /**
     * Esse método realiza a votação do plenário de acordo com o tipo de proposta desejada.
     *
     * @param qntPoliticosFavoraveis a quantidade de políticos favoráveis a votação.
     * @param qntPoliticosPresentes  a quantidade políticos presentes na votação.
     * @param status                 o status da votação.
     */
    public abstract boolean votarPlenario(int qntPoliticosFavoraveis, int qntPoliticosPresentes, StatusGovernista status);

    /**
     * Esse método avalia o resultado da votação da Comissão. Sendo possível encerrá-la ou aprová-la e aumentando
     * a quantidade de leis criadas pelo autor.
     *
     * @param proximoLocal    o próximo local de votação.
     * @param resultado       o resultado da votação.
     * @param autorDaProposta o deputado autor da proposta.
     */
    public void avaliaResultado(String proximoLocal, boolean resultado, Pessoa autorDaProposta) {
        if (proximoLocal.equals("-")) {
            if (resultado) {
                this.aprovaVotacao();

                autorDaProposta.aumentaLeis();
            } else {
                this.encerraVotacao();
            }
        }

        if (resultado)
            this.alteraSituacaoDoUltimoLocal(SituacaoVotacao.APROVADO);
        else
            this.alteraSituacaoDoUltimoLocal(SituacaoVotacao.REJEITADO);
    }

    /**
     * Esse método avalia o resultado da votação do Plenário.
     * Designando para o respectivo tipo de proposta a avaliação.
     *
     * @param resultado       o resultado da votação.
     * @param autorDaProposta o deputado autor da proposta.
     */
    public abstract void avaliaResultado(boolean resultado, Pessoa autorDaProposta);

    /**
     * Esse método exibe a tramitação de um projeto.
     */
    public String exibirTramitacao() {
        StringBuilder tramitacao = new StringBuilder();

        for (String[] tramite : this.votacoes) {
            tramitacao.append(tramite[1].replace("_", " ")).append(" (");
            if (tramite[0].equals("plenario"))
                tramitacao.append("Plenario");
            else
                tramitacao.append(tramite[0]);
            tramitacao.append("), ");
        }

        return tramitacao.toString().trim().substring(0, tramitacao.length() - 2);
    }

    /**
     * Retorna uma representaçao em String do projeto.
     *
     * @return string no formato codigo - dni do autor do projeto - ementa.
     */
    @Override
    public String toString() {
        return this.codigo + " - " + this.dniAutor + " - " + this.ementa;
    }

    /**
     * Esse método recupera o hash do objeto partido baseado no seu codigo.
     *
     * @return inteiro que representa hash do projeto.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    /**
     * Esse método compara um objeto projeto com outro objeto qualquer e verifica se
     * são iguais.
     *
     * @return true se os objetos comparados são iguais.
     */
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
            return other.codigo == null;
        } else return codigo.equals(other.codigo);
    }
}