package br.com.oficinaivan.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.oficinaivan.model.Carro;
import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.Servico;
import br.com.oficinaivan.model.exception.DaoException;

@Repository("carroDAO")
public class CarroDAOImpl extends GenericDAOImpl<Carro> implements CarroDAO {
	
	public boolean verificarPlacaDuplicada(String placa) throws DaoException {
		return (Long) super.getSession().getNamedQuery("verificaPlacaDuplicada").setString("placa", placa).uniqueResult() != 0 ? true : false;
	}
	
	public boolean verificarPlacaDuplicadaAlteracao(String placa, Long id) throws DaoException {
		return (Long) super.getSession().getNamedQuery("verificaPlacaDuplicadaAlteracao").setString("placa", placa).setLong("id", id).uniqueResult() != 0 ? true : false;
	}

	@Override
	protected Class<Carro> getClazz() {
		return Carro.class;
	}

	public Cliente obterClientePorCarro(Long idCarro) throws DaoException {
		String hql = "select cliente from Carro as carro inner join carro.cliente as cliente where carro.id = :idCarro";
		return (Cliente) super.getSession().createQuery(hql).setLong("idCarro", idCarro).uniqueResult();
	}
	
	public List<Carro> listarCarrosComServico() throws DaoException{
		String hql = "select distinct carro from Carro as carro inner join carro.listaServico as servico";
		return super.getSession().createQuery(hql).list();
	}
	
	public List<Servico> listaServicosPorCarro(String placa) throws DaoException{
		String hql = "select servicos from Carro as carro inner join carro.listaServico as servicos where carro.placa = :placa order by servicos.data asc";
		return super.getSession().createQuery(hql).setString("placa", placa).list();
	}	
}
