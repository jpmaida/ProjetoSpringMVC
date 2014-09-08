package br.com.oficinaivan.model.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.oficinaivan.model.Carro;
import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.Servico;
import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.service.CarroService;

@Component("carroFacade")
public class CarroFacade extends GenericFacade<Carro>{
	
	private CarroService carroService;
	
	@Autowired
	public CarroFacade(CarroService carroService) {
		this.carroService = carroService;
		super.setGenericService(carroService);
	}
	
	public void persistir(Carro carro) throws BusinessException {
		carroService.verificarPlacaDuplicada(carro);
		carroService.persistir(carro);
	}

	public void setCarroService(CarroService carroService) {
		this.carroService = carroService;
	}
	
	public Cliente obterClientePorCarro(Long idCarro) throws BusinessException {
		return carroService.obterClientePorCarro(idCarro);
	}
	
	public List<Carro> listarCarroComServico() throws BusinessException{
		return carroService.listarCarroComServico();
	}
	
	public List<Servico> listaServicosPorCarro(String placa) throws BusinessException{
		return carroService.listaServicosPorCarro(placa);
	}
	
	@Override
	public Carro obter(Long id) throws BusinessException {
		Carro carro = carroService.obter(id);
		carro.setCliente(this.obterClientePorCarro(carro.getId()));
		return carro;
	}
}
