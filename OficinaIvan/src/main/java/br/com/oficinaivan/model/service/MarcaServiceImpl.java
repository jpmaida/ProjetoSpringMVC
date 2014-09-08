package br.com.oficinaivan.model.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.oficinaivan.model.Marca;
import br.com.oficinaivan.model.dao.MarcaDAO;
import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.exception.DaoException;

@Service("marcaService")
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class MarcaServiceImpl extends GenericServiceImpl<Marca> implements MarcaService {
	
	private MarcaDAO marcaDAO;
	
	@Autowired
	public MarcaServiceImpl(MarcaDAO marcaDAO) {
		this.marcaDAO = marcaDAO;
		super.setGenericDAO(marcaDAO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.oficinaivan.model.service.MarcaService#verificarMarcaDuplicada(br.com.oficinaivan.model.Marca)
	 */
	public void verificarMarcaDuplicada(Marca marca) throws BusinessException{
		try {
			boolean isDuplicado = false;
			if(marca.getId() != null){
				isDuplicado = marcaDAO.verificarMarcaDuplicadaAlteracao(marca.getNome(), marca.getId());
			} else {
				isDuplicado = marcaDAO.verificarMarcaDuplicada(marca.getNome());
			}
			if(isDuplicado){
				throw new BusinessException("marca.duplicada");
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String gravarLogotipo(InputStream imagem, String nomeImagem){
		String caminhoArquivo = null;
		try {
			caminhoArquivo = super.getEnvironmentSource().getMessage("upload.folder", null, null) + nomeImagem;
			ImageIO.write(ImageIO.read(imagem), nomeImagem.substring(nomeImagem.lastIndexOf('.')+1), new File(caminhoArquivo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return caminhoArquivo;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void excluirLogotipo(String caminhoLogotipo){
		new File(caminhoLogotipo).delete();
	}

	public void setMarcaDAO(MarcaDAO marcaDAO) {
		this.marcaDAO = marcaDAO;
	}
}
