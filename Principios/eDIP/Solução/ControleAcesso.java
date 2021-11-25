public class ControleAcesso {
	private Identificador service;
	
	public controlaAcesso(Identificador service){
		this.service = service;
	}
	
	Pessoa login(Pessoa p){
		if (service.isValid(p))
			return p;
		throw new AcessoNegadoException();
	}
}

interface Identificador(){
        public boolean identificar() {}
}
  
public class IdentificaCliente implements Identificador{
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
