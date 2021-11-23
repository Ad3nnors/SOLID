package br.ufscar.dc.dsw.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.domain.Profissionais;
import br.ufscar.dc.dsw.service.spec.IProfissionaisService;

@Component
public class ProfissionaisConversor implements Converter<String, Profissionais>{

	@Autowired
	private IProfissionaisService service;
	
	@Override
	public Profissionais convert(String text) {
		
		if (text.isEmpty()) {
		 return null;	
		}
		
		Long id = Long.valueOf(text);	
		return service.buscarPorId(id);
	}
}