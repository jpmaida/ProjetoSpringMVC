package br.com.oficinaivan.model.dao;

import org.springframework.stereotype.Repository;

import br.com.oficinaivan.model.Servico;

@Repository("servicoDAO")
public class ServicoDAOImpl extends GenericDAOImpl<Servico> implements ServicoDAO {

	@Override
	protected Class<Servico> getClazz() {
		return Servico.class;
	}

}
