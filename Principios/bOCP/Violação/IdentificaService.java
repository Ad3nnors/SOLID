public class IdentificaService {
	private String tipo;
	
	public boolean identificaUsuario() {
		if (tipo.equals("Cliente"))
			return identificarCliente();
    	else if (tipo.equals("Admin"))
    		return identificarAdmin();
	}
}
 
public class IdentificaCliente : IdentificaService {
    public boolean identificarCliente() {
        // codigo para identificar cliente
    }
}
 
public class identificarAdmin : IdentificaService {
    public boolean identificarAdmin() {
        // codigo para identificar admin
    }
}

