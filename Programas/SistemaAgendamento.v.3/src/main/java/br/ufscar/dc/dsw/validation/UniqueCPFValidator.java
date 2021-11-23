package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.*;
import br.ufscar.dc.dsw.domain.*;

@Component
public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {

	@Autowired
	private IClientesDAO dao1;
	private IProfissionaisDAO dao2;

	@Override
	public boolean isValid(String CPF, ConstraintValidatorContext context) {
		if (dao1 != null) {
			Clientes cliente = dao1.findBycpf(CPF);
			return cliente == null;
		} else {
			if (dao2 != null) {
				Profissionais profissional = dao2.findBycpf(CPF);
				return profissional == null;

			} else {
				// Não necessidade de validação
				return true;
			}
		}
	}
}
