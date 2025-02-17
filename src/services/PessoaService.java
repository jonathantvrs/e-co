package services;

import controllers.PessoaController;
import entities.Pessoa;
import enums.CargosPoliticos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

/**
 * Classe que segue o padrão Service e é responsável por retornar as informações
 * necessárias sobre objetos Pessoa já cadastrados no sistema.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PessoaService implements Serializable {
    /**
     * Armazena Id de serialização do objeto PessoaService
     */
    private static final long serialVersionUID = 3322829397196919614L;
    /**
     * Armazena um controlador de Pessoas.
     */
    private PessoaController pessoas;

    /**
     * Constroi uma classe Service a partir de um Controlador de Pessoas.
     *
     * @param pessoas controlador de pessoas
     */
    public PessoaService(PessoaController pessoas) {
        this.pessoas = pessoas;
    }

    /**
     * Retorna um Set de objetos Pessoa. Obtém um grupo de todas as Pessoas
     * cadastradas no sistema.
     *
     * @return Set de Pessoa contendo todas as pessoas que foram cadastradas no
     * sistema até o momento
     */
    public HashSet<Pessoa> getPessoas() {
        return new HashSet<>(this.pessoas.getPessoas());
    }

    /**
     * Retorna booleano sobre o fato de uma Pessoa estar ou não cadastrada no
     * sistema.
     *
     * @param dni String com o dni buscado
     * @return true para uma pessoa cadastrada, false caso contrário.
     */
    public boolean ehPessoaCadastrada(String dni) {
        for (Pessoa pessoa : getPessoas())
            if (pessoa.getDni().equals(dni))
                return true;
        return false;
    }

    /**
     * Retorna um objeto Pessoa a partir de uma busca pelo seu dni.
     *
     * @param dni String contendo o dni da Pessoa que se quer obter
     * @return Pessoa dona do dni passado como parâmetro caso exista, null caso
     * contrário
     */
    public Pessoa getPessoaPeloDni(String dni) {
        for (Pessoa pessoa : getPessoas())
            if (pessoa.getDni().equals(dni))
                return pessoa;
        return null;
    }

    /**
     * Retorna um booleano sobre o fato de uma Pessoa com o dni passado possuir ou
     * não o cargo de deputado.
     *
     * @param dni String contendo o dni da Pessoa que se quer consultada
     * @return true para quando a pessoa com o dni passado possuir o cargo de
     * Deputado, false caso contrário
     */
    public boolean ehDeputado(String dni) {
        return ehPessoaCadastrada(dni) && CargosPoliticos.DEPUTADO.equals(getPessoaPeloDni(dni).getCargoPolitico());
    }

    /**
     * Retorna o número de deputados já cadastrados no sistema.
     *
     * @return número de deputados cadastrados no sistema
     */
    public int contaDeputados() {
        int qntDeputados = 0;

        for (Pessoa pessoa : this.getPessoas())
            if (this.ehDeputado(pessoa.getDni()))
                qntDeputados++;

        return qntDeputados;
    }

    /**
     * Esse método serve para carregar o mapa de pessoas
     * com o conjunto de pessoas do arquivo.
     *
     * @param mapaPessoas mapa de pessoas
     */
    public void setPessoas(Map<String, Pessoa> mapaPessoas) {
        this.pessoas.setPessoas(mapaPessoas);
    }
}