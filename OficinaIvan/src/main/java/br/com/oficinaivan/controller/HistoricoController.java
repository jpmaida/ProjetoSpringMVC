package br.com.oficinaivan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.oficinaivan.model.Carro;
import br.com.oficinaivan.model.Servico;
import br.com.oficinaivan.model.exception.BusinessException;
import br.com.oficinaivan.model.facade.CarroFacade;
import br.com.oficinaivan.util.Util;

import com.google.gson.Gson;

@Controller
@RequestMapping("/historicoController")
public class HistoricoController {
	
	@Autowired
	private CarroFacade carroFacade;
	
	@RequestMapping(value="/novo", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView novo(Carro carro){
		ModelAndView mav = new ModelAndView("det-historico.view");
		try {
			mav.addObject("listaCarro", new Gson().toJson(this.montarListaPlacaAutoComplete(carroFacade.listarCarroComServico())).replaceAll("\"", "'"));
			if(carro != null && StringUtils.isNotBlank(carro.getPlaca())){
				mav.addObject("listaServico", carroFacade.listaServicosPorCarro(carro.getPlaca()));
			} else {
				mav.addObject("carro", new Carro());
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/gerarRelatorioPDF", method=RequestMethod.GET)
	public ModelAndView gerarRelatorioPDF(Carro carro){
		List<Servico> servicos = null;
		try {
			servicos = carroFacade.listaServicosPorCarro(carro.getPlaca());
			carro = carroFacade.obter(carro.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new ModelAndView("pdfReport", this.montarRelatorioPDF(servicos, carro));
	}
	
/**
   * Implementação de método que gera um relatório no formato PDF com o histórico completo do automóvel.
   * @param listaServicos - Objeto do tipo List<Servico> que representam os serviços do automóvel ordenados cronologicamente.
   * @param caminhoRelatorio - Objeto do tipo String que representa o caminho do arquivo .jasper.
   * @throws Exception Representa um erro genérico.
   */
	private Map<String, Object> montarRelatorioPDF(List<Servico> listaServicos, Carro carro){
		List<String> historicos = new ArrayList<String>();
		for(Servico servico : listaServicos){
			StringBuilder historico = new StringBuilder();
			historico.append("Data: ");
			historico.append(servico.getData() != null ? Util.formatarData(servico.getData()) : '-');
			historico.append('\n');
			historico.append("Descrição: ");
			historico.append(servico.getDescricao());
			historico.append('\n');
			historico.append("Quilometragem: ");
			historico.append(StringUtils.isNotBlank(servico.getQuilometragem()) ? servico.getQuilometragem() : '-');
			historico.append('\n');
		  	historico.append("Material: ");
		  	historico.append(StringUtils.isNotBlank(servico.getMaterial()) ? servico.getMaterial() : '-');
		  	historico.append('\n');
		  	historico.append("Observação: ");
		  	historico.append(StringUtils.isNotBlank(servico.getObservacao()) ? servico.getObservacao() : '-');
		  	historicos.add(historico.toString());
		}
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("MARCA", carro.getMarca().getNome());
		parameters.put("MODELO", carro.getModelo());
		parameters.put("ANO", carro.getAno());
		parameters.put("PROPRIETARIO", carro.getCliente().getNome());
		parameters.put("datasource", new JRBeanCollectionDataSource(historicos));
		return parameters;
	}
	
	public void setCarroFacade(CarroFacade carroFacade) {
		this.carroFacade = carroFacade;
	}
	
	private List<Carro> montarListaPlacaAutoComplete(List<Carro> listaCarros){
		List<Carro> autoComplete = new ArrayList<Carro>();
		for(int i=0; i<listaCarros.size(); i++){
			Carro c = new Carro();
			c.setId(listaCarros.get(i).getId());
			c.setPlaca(listaCarros.get(i).getPlaca());
			autoComplete.add(c);
		}
		return autoComplete;
	}
}
