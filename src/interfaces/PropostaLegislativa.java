package interfaces;

import entities.Pessoa;
import enums.StatusGovernista;
import enums.TipoProjeto;

import java.util.List;

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

    /**
     * Retorna o código do projeto
     *
     * @return String com o código do projeto
     */
    String getCodigo();

    /**
     * Retorna sequencia de votações de uma proposta legislativa
     * em ordem cronológica.
     *
     * @return lista contendo arrays String da sequência de votações
     * de uma proposta.
     */
    List<String[]> getVotacoes();

    /**
     * Retorna o ano em que foi criada a proposta legislativa.
     *
     * @return ano de criação da Proposta Legislativa.
     */
    int getAno();

    /**
     * Retornao tipo da proposta legislativa, se é PL, PLP ou PEC.
     *
     * @return tipo da proposta legislativa
     */
    TipoProjeto getTipoDoProjeto();

    /**
     * Retorna o local de votação atual da proposta.
     *
     * @return local de votação atual da proposta
     */
    String getLocalDeVotacao();

    /**
     * Define o novo local de votação da proposta.
     *
     * @param localDeVotacao String contendo o novo local de votação da proposta
     */
    void setNovoLocalDeVotacao(String localDeVotacao);

    /**
     * Retorna uma String contendo os interesses da proposta legislativa. Os interesses retornados
     * na String são separados por ", ".
     *
     * @return String contendo os interesses da proposta legislativa
     */
    String getInteresses();

    /**
     * Retorna String contendo a situação atual da proposta legislativa no Congresso.
     *
     * @return String contendo a situação atual da proposta legislativa no Congresso
     */
    String getSituacaoAtual();

    /**
     * Retorna uma String com o dni do Autor da proposta legislativa.
     *
     * @return String contendo o dni do autor da proposta legislativa
     */
    String getAutor();

    /**
     * Retorna o número sequencial do cadastro de um projeto.
     * @return inteiro equivalente a ordem em que o projeto foi cadastrado
     */
    int getNumCriacaoProjeto();

    /**
     * Esse método retorna a representaçao em String de uma proposta legislativa.
     *
     * @return String contendo a representação em String do objeto.
     */
    String toString();

    /**
     * Esse método realiza a votação de uma comissão de acordo com a quantidade de políticos a favor e a quantidade total de políticos da comissão.
     *
     * @param qntDePoliticosDaComissao a quantidade de políticos da comissão.
     * @param qntPoliticosFavoraveis   a quantidade de políticos a favor.
     * @param status                   o status da votação.
     */
    boolean votarComissao(int qntPoliticosFavoraveis, int qntDePoliticosDaComissao, StatusGovernista status);

    /**
     * Esse método altera o próximo local de votação da comissão. Fazendo a análise para caso o mesmo vá para o plenário e o encaminha para o primeiro turno.
     *
     * @param proximoLocal o próximo local de votação.
     */
    void alteraNovoLocal(String proximoLocal);

    /**
     * Esse método verifica se existe o quórum mínimo para que seja possível realizar a votação. Possuindo diferentes cálculos para os tipos de projeto.
     *
     * @param qntDeputadosPresentes a quantidade de deputados presentes.
     * @param qntTotalDeputado      a quantidade total de deputados cadastrados.
     */
    void verificaQuorumMinimo(int qntDeputadosPresentes, int qntTotalDeputado);

    /**
     * Esse método realiza a votação do plenário de acordo com o tipo de proposta desejada.
     *
     * @param qntPoliticosFavoraveis a quantidade de políticos favoráveis a votação.
     * @param qntPoliticosPresentes  a quantidade políticos presentes na votação.
     * @param status                 o status da votação.
     */
    boolean votarPlenario(int qntPoliticosFavoraveis, int qntPoliticosPresentes, StatusGovernista status);

    /**
     * Esse método avalia o resultado da votação da Comissão. Sendo possível encerrá-la ou aprová-la e aumentando
     * a quantidade de leis criadas pelo autor.
     *
     * @param proximoLocal    o próximo local de votação.
     * @param resultado       o resultado da votação.
     * @param autorDaProposta o deputado autor da proposta.
     */
    void avaliaResultado(String proximoLocal, boolean resultado, Pessoa autorDaProposta);

    /**
     * Esse método avalia o resultado da votação do Plenário. Designando para o respectivo tipo de proposta a avaliação.
     *
     * @param resultado       o resultado da votação.
     * @param autorDaProposta o deputado autor da proposta.
     */
    void avaliaResultado(boolean resultado, Pessoa autorDaProposta);

    /**
     * Esse método exibe toda a tramitação de um projeto.
     */
    String exibirTramitacao();
}