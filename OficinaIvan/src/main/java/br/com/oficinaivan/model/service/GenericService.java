package br.com.oficinaivan.model.service;

import br.com.oficinaivan.model.exception.BusinessException;
import java.util.List;

/**
 * Interface com métodos básicos para camada de negócio.
 */
public interface GenericService<T> {
    
    /**
     * Assinatura de método que realiza uma inserção no banco.
     * @param object - Representa o objeto a ser inserido.
     * @throws BusinessException Representa um erro na camada de negócio.
     */
    void persistir(T object) throws BusinessException;
    
    /**
     * Assinatura de método que realiza a exclusão de um registro no banco.
     * @param object - Representa o objeto a ser excluído.
     * @throws BusinessException Representa um erro na camada de negócio.
     */
    void excluir(T object) throws BusinessException;
    
    /**
     * Assinatura de método que realiza a obtenção de um registro no banco.
     * @param object - Representa o objeto usado como filtro na pesquisa.
     * @return Object - Representa o objeto retornado pela pesquisa.
     * @throws BusinessException Representa um erro na camada de negócio.
     */
    T obter(Long id) throws BusinessException;
    
    /**
     * Assinatura de método que lista os registros através de um filtro.
     * @param object - Representa o objeto usado como filtro na pesquisa.
     * @return List<Object> - Representa o resultado da pesquisa.
     * @throws BusinessException Representa um erro na camada de negócio.
     */
    List<T> listar() throws BusinessException;
}