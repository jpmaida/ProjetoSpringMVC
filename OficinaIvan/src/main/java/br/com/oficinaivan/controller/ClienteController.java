package br.com.oficinaivan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.facade.ClienteFacade;
import br.com.oficinaivan.model.facade.MarcaFacade;

/**
 * Classe da camada de controle que controla as operações do cliente.
 */
@Controller
@RequestMapping("/clienteController")
public class ClienteController extends GenericController{
	
	@Autowired
	private ClienteFacade clienteFacade;
	
	@Autowired
	@Qualifier("clienteValidator")
	private Validator validator;
	
	@Autowired
	@Qualifier("messageSource")
	private MessageSource messageSource;
	
	@Autowired
	private MarcaFacade marcaFacade;
	
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder){
		webDataBinder.setValidator(validator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, true));
	}
	
	@RequestMapping(value="/novo", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView novo() throws Exception{
		ModelAndView mav = new ModelAndView("inc-cliente.view");
		mav.addObject("cliente", new Cliente());
		return mav;
	}
	
	@RequestMapping(value="/inserir", method={RequestMethod.POST})
	public ModelAndView inserir(@Validated Cliente cliente, BindingResult result){
		ModelAndView mav = new ModelAndView();
		try {
			if(result.hasErrors()){
				mav.setViewName("inc-cliente.view");
				return mav;
			}
			clienteFacade.persistir(cliente);
			mav.addObject(super.MSGSUCCESS, messageSource.getMessage("sucesso.cliente.cadastro", null, null));
			mav.setViewName("forward:/carroController/novo?cliente.id="+cliente.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
			result.addError(new ObjectError("cliente", messageSource.getMessage(e.getMessage(), null, null)));
			mav.setViewName("inc-cliente.view");
		}
		return mav;
	}
	
	@RequestMapping(value="/listar", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView listar(Cliente cliente){
		ModelAndView mav = new ModelAndView("lis-cliente.view");
		try {
			mav.addObject("listaCliente", clienteFacade.listar());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.GET)
	public ModelAndView excluir(Long id){
		ModelAndView mav = new ModelAndView("forward:listar");
		try {
			clienteFacade.excluir(clienteFacade.obter(id));
			mav.addObject(super.MSGSUCCESS, messageSource.getMessage("sucesso.cliente.exclusao", null, null));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/detalhar", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView detalhar(Long id, boolean isAlterar){
		ModelAndView mav = new ModelAndView();
		try {
			mav.addObject("listaMarca", marcaFacade.listar());
			mav.addObject("cliente", clienteFacade.obter(id));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		if(isAlterar){
			mav.setViewName("alt-cliente.view");
		} else {
			mav.setViewName("det-cliente.view");
		}
		return mav;
	}

	@RequestMapping(value="/alterar", method=RequestMethod.POST)
	public ModelAndView alterar(@Validated Cliente cliente, BindingResult result){
		ModelAndView mav = new ModelAndView("forward:listar");
		try {
			if(result.hasErrors()){
				mav.setViewName("alt-cliente.view");
				return mav;
			}
			clienteFacade.persistir(cliente);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	public void setClienteFacade(ClienteFacade clienteFacade) {
		this.clienteFacade = clienteFacade;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setMarcaFacade(MarcaFacade marcaFacade) {
		this.marcaFacade = marcaFacade;
	}	
}