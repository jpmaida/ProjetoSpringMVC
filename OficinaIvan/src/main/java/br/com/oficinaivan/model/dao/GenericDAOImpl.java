package br.com.oficinaivan.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected abstract Class getClazz();
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
     * ImplementaÃ§Ã£o de mÃ©todo que retorna um conector condicional Ã  query.
     * @param sql - Representa a query.
     * @return String - Representa o conector.
     */
    protected String addConditionalConnector(String sql){
        if(sql.indexOf("WHERE") == -1){
            return " WHERE ";
        } else {
            return " AND ";
        }
    }       
    
    public void persistir(T objeto) {
		this.getSession().saveOrUpdate(objeto);
	}
    
    public void excluir(T objeto) {
		this.getSession().delete(objeto);
	}
    
    public T get(Long id) {
		return (T) this.getSession().get(getClazz(), id);
	}
    
    @SuppressWarnings("unchecked")
	public List<T> list () {
		return (List<T>) this.getSession().createCriteria(getClazz()).list();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
