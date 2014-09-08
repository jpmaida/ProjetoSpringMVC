package br.com.oficinaivan.model.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.oficinaivan.model.Carro;
import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.Servico;
import br.com.oficinaivan.model.dao.CarroDAO;
import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.exception.DaoException;

@Service("carroService")
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class CarroServiceImpl extends GenericServiceImpl<Carro> implements CarroService {

	private CarroDAO carroDAO;
	
	@Autowired
	public CarroServiceImpl(CarroDAO carroDAO) {
		this.carroDAO = carroDAO;
		super.setGenericDAO(carroDAO);
	}
	
	public void verificarPlacaDuplicada(Carro carro) throws BusinessException {
		try {
			boolean isAlterar = false;
			if(carro.getId() != null){
				isAlterar = true;
			}
			boolean isPlacaDuplicada = isAlterar ? carroDAO.verificarPlacaDuplicadaAlteracao(carro.getPlaca(), carro.getId()) : carroDAO.verificarPlacaDuplicada(carro.getPlaca());
			if(isPlacaDuplicada){
				throw new BusinessException("placa.duplicada");
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	public void setCarroDAO(CarroDAO carroDAO) {
		this.carroDAO = carroDAO;
	}

	public Cliente obterClientePorCarro(Long idCarro) throws BusinessException {
		try {
			return carroDAO.obterClientePorCarro(idCarro);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Carro> listarCarroComServico() throws BusinessException{
		try {
			return carroDAO.listarCarrosComServico();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Servico> listaServicosPorCarro(String placa) throws BusinessException{
		try {
			return carroDAO.listaServicosPorCarro(placa);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Carro obter(Long id) throws BusinessException {
		Carro carro = super.obter(id);
		Hibernate.initialize(carro.getMarca());
		return carro;
	}
}
