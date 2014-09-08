package br.com.oficinaivan.model.dao;

import org.springframework.stereotype.Repository;

import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.exception.DaoException;

@Repository("clienteDAO")
public class ClienteDAOImpl extends GenericDAOImpl<Cliente> implements ClienteDAO {

	@Override
	protected Class<Cliente> getClazz() {
		return Cliente.class;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.oficinaivan.model.dao.ClienteDAO#verificarClienteNomeIgual(br.com.oficinaivan.model.Cliente)
	 */
    public boolean verificarClienteNomeIgual(String nome) throws DaoException{
        return (Long) super.getSession().getNamedQuery("verificaClienteNomeIgual").setString("nome", nome).uniqueResult() != 0 ? true : false;
    }
    
    public boolean verificarClienteNomeIgualAlteracao(String nome, Long id) throws DaoException{
        return (Long) super.getSession().getNamedQuery("verificaClienteNomeIgualAlteracao").setString("nome", nome).setLong("id", id).uniqueResult() != 0 ? true : false;
    }
}