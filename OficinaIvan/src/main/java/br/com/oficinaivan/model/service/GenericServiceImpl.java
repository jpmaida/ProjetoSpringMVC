package br.com.oficinaivan.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.oficinaivan.model.dao.GenericDAO;
import br.com.oficinaivan.model.exception.BusinessException;

@Service("genericService")
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
public abstract class GenericServiceImpl<T> implements GenericService<T> {

	private GenericDAO<T> genericDAO;
	
	@Autowired
	@Qualifier("environmentSource")
	private MessageSource environmentSource;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly=false, rollbackFor=Exception.class, isolation=Isolation.READ_COMMITTED)
	public void persistir(T object) throws BusinessException{
		genericDAO.persistir(object);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly=false, rollbackFor=Exception.class, isolation=Isolation.READ_COMMITTED)
	public void excluir(T object) throws BusinessException{
		genericDAO.excluir(object);
	}
	
	public T obter(Long id) throws BusinessException{
		return (T) genericDAO.get(id.longValue());
	}
	
	public List<T> listar() throws BusinessException{
		return genericDAO.list();
	}
	
	public void setGenericDAO(GenericDAO<T> genericDAO) {
		this.genericDAO = genericDAO;
	}

	public MessageSource getEnvironmentSource() {
		return environmentSource;
	}

	public void setEnvironmentSource(MessageSource environmentSource) {
		this.environmentSource = environmentSource;
	}

}
