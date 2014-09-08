package br.com.oficinaivan.model.facade;

import java.util.List;

import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.service.GenericService;

public abstract class GenericFacade<T> {
	
	private GenericService<T> genericService;

	public void setGenericService(GenericService<T> genericService) {
		this.genericService = genericService;
	}
	
	public void persistir(T object) throws BusinessException {
		genericService.persistir(object);
	}

	public List<T> listar() throws BusinessException{
		return genericService.listar();
	}
	
	public void excluir(T object) throws BusinessException{
		genericService.excluir(object);
	}
	
	public T obter(Long id) throws BusinessException{
		return genericService.obter(id);
	}
}
