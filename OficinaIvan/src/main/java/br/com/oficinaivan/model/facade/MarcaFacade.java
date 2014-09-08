package br.com.oficinaivan.model.facade;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.oficinaivan.model.Marca;
import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.service.MarcaService;

@Component("marcaFacade")
public class MarcaFacade extends GenericFacade<Marca> {
	
	private MarcaService marcaService;
	
	@Autowired
	public MarcaFacade(MarcaService marcaService) {
		this.marcaService = marcaService;
		super.setGenericService(marcaService);
	}
	
	public void persistir(Marca marca, InputStream imagem, String nomeImagem) throws BusinessException {
		marcaService.verificarMarcaDuplicada(marca);
		if(marca.getId() != null){
			marcaService.excluirLogotipo(marca.getCaminho());
		}
		marca.setCaminho(marcaService.gravarLogotipo(imagem, nomeImagem));
		super.persistir(marca);
	}
	
	@Override
	public void excluir(Marca marca) throws BusinessException {
		marca = super.obter(marca.getId());
		marcaService.excluirLogotipo(marca.getCaminho());
		super.excluir(marca);
	}

	public void setMarcaService(MarcaService marcaService) {
		this.marcaService = marcaService;
	}
}
