package br.com.oficinaivan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.oficinaivan.model.Carro;
import br.com.oficinaivan.model.Cliente;
import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.facade.CarroFacade;
import br.com.oficinaivan.model.facade.ClienteFacade;
import br.com.oficinaivan.model.facade.MarcaFacade;

@Controller
@RequestMapping("/carroController")
public class CarroController extends GenericController {
	
	@Autowired
	private CarroFacade carroFacade;
	
	@Autowired
	private ClienteFacade clienteFacade;
	
	@Autowired
	private MarcaFacade marcaFacade;
	
	@Autowired
	@Qualifier("messageSource")
	private MessageSource messageSource;
	
	@Autowired
	@Qualifier("carroValidator")
	private Validator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder){
		webDataBinder.setValidator(validator);
	}
		
	@RequestMapping(value="/inserir", method=RequestMethod.POST)
	public ModelAndView inserir(@Validated Carro carro, BindingResult result){
		ModelAndView mav = new ModelAndView("inc-carro.view");
		try {
			mav.addObject("listaMarca", marcaFacade.listar());
			if(result.hasErrors()){				
				return mav;
			}
			carroFacade.persistir(carro);
			Long idCliente = carro.getCliente().getId();
			String nomeCliente = carro.getCliente().getNome();
			carro = new Carro();
			carro.setCliente(new Cliente(idCliente));
			carro.getCliente().setNome(nomeCliente);
			mav.addObject(carro);
			mav.addObject(super.MSGSUCCESS, messageSource.getMessage("sucesso.carro.cadastro", null, null));
		} catch (BusinessException e) {
			e.printStackTrace();
			result.addError(new ObjectError("carro", messageSource.getMessage(e.getMessage(), null, null)));
		}
		return mav;
	}

	@RequestMapping(value="/novo", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView novo(Carro carro){
		ModelAndView mav = new ModelAndView("inc-carro.view");
		try {
			carro.setCliente(clienteFacade.obter(carro.getCliente().getId()));
			mav.addObject("listaMarca", marcaFacade.listar());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/alterar", method=RequestMethod.POST)
	public ModelAndView alterar(@Validated Carro carro){
		ModelAndView mav = new ModelAndView("forward:/clienteController/detalhar?id="+carro.getCliente().getId()+"&isAlterar=false");
		try {
			carroFacade.persistir(carro);
			mav.addObject(super.MSGSUCCESS, messageSource.getMessage("sucesso.carro.alteracao", null, null));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	
	@RequestMapping(value="/excluir/{carroId}/{clienteId}", method=RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("carroId") Long id, @PathVariable("clienteId") Long idCliente){
		ModelAndView mav = new ModelAndView("forward:/clienteController/detalhar?id="+idCliente+"&isAlterar=false");
		try {
			carroFacade.excluir(new Carro(id));
			mav.addObject(super.MSGSUCCESS, messageSource.getMessage("sucesso.carro.exclusao", null, null));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	public void setCarroFacade(CarroFacade carroFacade) {
		this.carroFacade = carroFacade;
	}

	public void setClienteFacade(ClienteFacade clienteFacade) {
		this.clienteFacade = clienteFacade;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public void setMarcaFacade(MarcaFacade marcaFacade) {
		this.marcaFacade = marcaFacade;
	}
}
