package br.com.oficinaivan.model.service;

import java.io.InputStream;

import br.com.oficinaivan.model.Marca;
import br.com.oficinaivan.model.exception.BusinessException;

public interface MarcaService extends GenericService<Marca> {
	void verificarMarcaDuplicada(Marca marca) throws BusinessException;
	
	String gravarLogotipo(InputStream imagem, String nomeImagem);
	
	void excluirLogotipo(String caminhoLogotipo);
}
