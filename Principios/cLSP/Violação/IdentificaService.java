import java.util.Calendar;

public class IdentificaService {
	private String tipo;
	
	public void identificaUsuario() {
		if (tipo.equals("Cliente"))
			this.identificarCliente();
    	else if (tipo.equals("Admin"))
    		this.identificarAdmin();
	}
}
 
public class IdentificaCliente {
	if (Calendar.getInstance().get(Calendar.HOUR).equals(4))
		throw new HoraManutencaoException();
    public void identificar() {
        // codigo para identificar cliente
    }
}
 
public class identificarAdmin : IdentificaCliente {
	public void identificar() {
        // codigo para identificar admin
    }
}

