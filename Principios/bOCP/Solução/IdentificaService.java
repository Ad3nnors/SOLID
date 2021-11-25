public abstract class IdentificaService {
	public abstract boolean identificar();
}
 
public class IdentificaCliente : IdentificaService {
	@Override
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
