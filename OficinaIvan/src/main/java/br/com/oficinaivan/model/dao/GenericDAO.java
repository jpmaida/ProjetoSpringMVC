package br.com.oficinaivan.model.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	List<T> list();
	
	void persistir(T objeto);
	
	void excluir(T objeto);
	
	T get(Long id);
	
}
