package br.ufscar.dc.dsw.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.domain.Clientes;
import br.ufscar.dc.dsw.service.spec.IClientesService;

@Component
public class ClientesConversor implements Converter<String, Clientes>{

	@Autowired
	private IClientesService service;
	
	@Override
	public Clientes convert(String text) {
		
		if (text.isEmpty()) {
		 return null;	
		}
		
		Long id = Long.valueOf(text);	
		return service.buscarPorId(id);
	}
}