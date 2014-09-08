package br.com.oficinaivan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.oficinaivan.model.Marca;
import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.facade.MarcaFacade;

@Controller("/marcaController")
@RequestMapping(value="/marcaController")
public class MarcaController extends GenericController {
	
	@Autowired
	private MarcaFacade marcaFacade;
	
	@Autowired
	@Qualifier("marcaValidator")
	private Validator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder){
		webDataBinder.setValidator(validator);
	}
	
	@RequestMapping(value="/novo", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView novo(){
		ModelAndView mav = new ModelAndView("inc-marca.view");
		mav.addObject("marca", new Marca());
		return mav;
	}
	
	@RequestMapping(value="/persistir", method=RequestMethod.POST)
	public ModelAndView persistir(@Validated Marca marca, BindingResult result, @RequestParam(value="logotipo") MultipartFile logotipo, @RequestParam(value="isAlterar") boolean isAlterar) {
		ModelAndView mav = new ModelAndView("forward:/marcaController/listar");
		try {
			boolean hasError = result.hasErrors();
			hasError = this.validar(result, logotipo, marca, isAlterar);
			if(hasError){
				if(isAlterar){
					mav.setViewName("alt-marca.view");
				} else {
					mav.setViewName("inc-marca.view");
				}
				return mav;
			}
			marcaFacade.persistir(marca, logotipo.getInputStream(), logotipo.getOriginalFilename());
			mav.addObject(super.MSGSUCCESS, super.getMessageSource().getMessage("sucesso.marca.cadastro", null, null));
		} catch (BusinessException e) {
			e.printStackTrace();
			result.addError(new ObjectError("marca", super.getMessageSource().getMessage(e.getMessage(), null, null)));
			mav.setViewName("inc-marca.view");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return mav;
	}
	
	private boolean validar(BindingResult result, MultipartFile logotipo, Marca marca, boolean isAlterar){
		boolean hasError = false;
		
		if(StringUtils.isBlank(logotipo.getOriginalFilename())){
			if(isAlterar){
				result.addError(new ObjectError("marca", super.getMessageSource().getMessage("campo.obrigatorio", new String[]{"Novo Logotipo"}, null)));
			} else {
				result.addError(new ObjectError("marca", super.getMessageSource().getMessage("campo.obrigatorio", new String[]{"Logotipo"}, null)));
			}
			hasError = true;
		}
		return hasError;
	}
	
	@RequestMapping(value="/listar", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listar() {
		ModelAndView mav = new ModelAndView("lis-marca.view");
		try {
			mav.addObject("listaMarca", marcaFacade.listar());
			mav.addObject("marca", new Marca());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.POST)
	public ModelAndView excluir(Marca marca) {
		ModelAndView mav = new ModelAndView("forward:/marcaController/listar");
		try {
			marcaFacade.excluir(marca);
			mav.addObject(super.MSGSUCCESS, super.getMessageSource().getMessage("sucesso.marca.exclusao", null, null));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/detalhar", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView detalhar(Marca marca, boolean isAlterar) {
		ModelAndView mav = new ModelAndView("det-marca.view");
		try {
			mav.addObject("marca", marcaFacade.obter(marca.getId()));
			if(isAlterar){
				mav.setViewName("alt-marca.view");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/carregarImagem", method=RequestMethod.GET)
	@ResponseBody
	public byte[] carregarImagem(Long id){
		try {
			FileInputStream fis = new FileInputStream(new File(marcaFacade.obter(id).getCaminho()));
			byte[] imageByteArray = IOUtils.toByteArray(fis);
			fis.close();
			return imageByteArray;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setMarcaFacade(MarcaFacade marcaFacade) {
		this.marcaFacade = marcaFacade;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}
}
