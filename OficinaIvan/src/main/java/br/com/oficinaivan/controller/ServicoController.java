package br.com.oficinaivan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.Servico;
import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.facade.CarroFacade;
import br.com.oficinaivan.model.facade.ServicoFacade;

@Controller
@RequestMapping(value="/servicoController")
public class ServicoController extends GenericController {
	
	@Autowired
	private ServicoFacade servicoFacade;
	
	@Autowired
	private CarroFacade carroFacade;
	
	@Autowired
	@Qualifier("servicoValidator")
	private Validator validator;
	
	@Autowired
	@Qualifier("messageSource")
	private MessageSource messageSource;
	
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, true));
		webDataBinder.setValidator(validator);
	}
	
	@RequestMapping(value="/listar", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listar(){
		ModelAndView mav = new ModelAndView("lis-servico.view");
		try {
			mav.addObject("listaServico", servicoFacade.listar());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}

	public void setServicoFacade(ServicoFacade servicoFacade) {
		this.servicoFacade = servicoFacade;
	}
	
	@RequestMapping(value="/novo", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView novo(Servico servico, BindingResult result){
		ModelAndView mav = new ModelAndView("inc-servico.view");
		try {
			mav.addObject("listaCarro", carroFacade.listar());
			if(servico.getCarro() != null && servico.getCarro().getId() != null){
				servico.setCliente(carroFacade.obterClientePorCarro(servico.getCarro().getId()));
			} else {
				mav.addObject("servico", new Servico());
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value="/inserir", method=RequestMethod.POST)
	public ModelAndView inserir(@Validated Servico servico, BindingResult result){
		ModelAndView mav = new ModelAndView("forward:listar");
		try {
			if(result.hasErrors()){
				mav.addObject("listaCarro", carroFacade.listar());
				mav.setViewName("inc-servico.view");
				return mav;
			}
			servicoFacade.persistir(servico);
			mav.addObject(super.MSGSUCCESS, messageSource.getMessage("sucesso.servico.cadastro", null, null));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/excluir/{id}", method=RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id){
		ModelAndView mav = new ModelAndView("forward:/servicoController/listar");
		try {
			servicoFacade.excluir(new Servico(id));
			mav.addObject(super.MSGSUCCESS, messageSource.getMessage("sucesso.servico.exclusao", null, null));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/detalhar/{id}/{isAlterar}", method=RequestMethod.GET)
	public ModelAndView detalhar(@PathVariable("id") Long id, @PathVariable("isAlterar") boolean isAlterar){
		ModelAndView mav = new ModelAndView();
		try {
			String forward = null;
			mav.addObject("servico", servicoFacade.obter(id));
			if(isAlterar){
				mav.addObject("listaCarro", carroFacade.listar());
				forward = "alt-servico.view";
			} else {
				forward = "det-servico.view";
			}
			mav.setViewName(forward);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/carregarCliente", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView carregarCliente(Servico servico, BindingResult result){
		ModelAndView mav = new ModelAndView("alt-servico.view");
		try {
			mav.addObject("listaCarro", carroFacade.listar());
			if(servico.getCarro() != null && servico.getCarro().getId() != null){
				servico.setCliente(carroFacade.obterClientePorCarro(servico.getCarro().getId()));
			} else {
				servico.setCliente(new Cliente());
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/alterar", method=RequestMethod.POST)
	public ModelAndView alterar(@Validated Servico servico, BindingResult result){
		ModelAndView mav = new ModelAndView("forward:listar");
		try {
			if(result.hasErrors()){
				mav.addObject("listaCarro", carroFacade.listar());
				mav.setViewName("alt-servico.view");
				return mav;
			}
			servicoFacade.persistir(servico);
			mav.addObject(super.MSGSUCCESS, messageSource.getMessage("sucesso.servico.alteracao", null, null));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	public void setCarroFacade(CarroFacade carroFacade) {
		this.carroFacade = carroFacade;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}
}