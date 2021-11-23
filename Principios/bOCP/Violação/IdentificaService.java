public class IdentificaService {
	private String tipo;
	
	public void identificaUsuario() {
		if (tipo.equals("Cliente"))
			this.identificarCliente();
    	else if (tipo.equals("Admin"))
    		this.identificarAdmin();
	}
}
 
public class IdentificaCliente : IdentificaService {
    public void identificarCliente() {
        // codigo para identificar cliente
    }
}
 
public class identificarAdmin : IdentificaService {
    public void identificarAdmin() {
        // codigo para identificar admin
    }
}

