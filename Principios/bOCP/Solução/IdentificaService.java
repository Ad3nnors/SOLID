public abstract class IdentificaService {
	public abstract void identificar();
	
	public void identificaUsuario() {
		this.identificar();
}
 
public class IdentificaCliente : IdentificaService {
	@Override
    public void identificar() {
        // codigo para identificar cliente
    }
}
 
public class identificarAdmin : IdentificaService {
	@Override
    public void identificar() {
        // codigo para identificar admin
    }
}
