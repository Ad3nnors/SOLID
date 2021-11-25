public class ControleAcesso {
	private IDentificaCliente service;
	
	public controlaAcesso(IdentificaCliente service){
		this.service = service;
	}
	
	Pessoa login(Pessoa p){
		if (service.isValid(p))
			return p;
		throw new AcessoNegadoException();
	}
}

public class IdentificaCliente {
	public boolean identificar() {
		// codigo para identificar cliente
	}
}
 
public class identificarAdmin : IdentificaService {
	@Override
	public boolean identificar() {
		// codigo para identificar admin
	}
}
