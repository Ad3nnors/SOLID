# SOLID

Cada letra da palavra SOLID corresponde a um princípio de design de classes. Ou seja, não se trata de regras, e sim boas práticas que levam a um código melhor. Esse artigo buscará explicar cada um desses princípios e fazer análise de exemplos.

*******
Tabela de conteúdo:
 1. [Single Responsibility Principle (SRP)](#srp)
 2. [Open-Closed Principle (OCP)](#ocp)
 3. [Liskov Substitution Principle (LSP)](#lsp)
 4. [Interface Segregation Principle (ISP)](#isp)
 5. [Dependency Inversion Principle (DIP)](#dip)

*******
<div id='srp'/>  

## Single Responsibility Principle (SRP)

Esse princípio diz que uma classe deve ter uma única razão para mudar.
Considere a classe Cliente abaixo:

*domain.Cliente*:

```java
public class Cliente{

	private Long id;
    	private static String cpf;
	private String nome;
	private String telefone;
	
	public void insert() throws Exception {
		String sql = "INSERT INTO CLIENTE (cpf, nome, telefone) VALUES (?, ?, ?)";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement = conn.prepareStatement(sql);
			statement.setString(1, cpf);
			statement.setString(2, nome);
			statement.setString(3, telefone);
			statement.executeUpdate();
	
			statement.close();
			conn.close();
		} catch (SQLException e) {
		    	throw new RuntimeException(e);
		}
    	}
	
	public void delete() {
		String sql = "DELETE FROM CLIENTE where cpf = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, cpf);
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    	}
	
	// + getters e setters
}

```

Deseja-se armazenar uma nova informação sobre os clientes: o e-mail. Esse é um motivo para a Classe mudar, pois ela é uma entidade do sistema. Outra mudança poderia ser: antes de inserir o cliente no banco de dados, adicionar uma verificação se já há cadastrado cliente com o mesmo cpf. Apenas com esses dois exemplos, já é possível identificar duas responsabilidades em uma mesma classe: representar a entidade e armazenar os dados no banco. Sendo assim, essa classe não está de acordo com o SRP.
Uma solução para adequar esse exemplo a SRP é dividir essa classe em duas: uma classe entidade e uma DAO (Data Access Object). A DAO funciona como uma ponte entre a entidade do sistema e o banco de dados, ela que ficará responsável por essa comunicação.

**domain.Cliente**:

```java
public class Cliente{
	private Long id;
	private static String cpf;
	private String nome;
	private String telefone;
	
	public Cliente() {}
	
	// + getters e setters
}
```

**dao.ClienteDAO**:

```java
import Solução.domain.Cliente;

public class ClienteDAO{

	public void insert(Cliente cli) throws Exception {
		String sql = "INSERT INTO CLIENTE (cpf, nome, telefone) VALUES (?, ?, ?)";
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement = conn.prepareStatement(sql);
			statement.setString(1, cli.getCpf());
			statement.setString(2, cli.getNome());
			statement.setString(3, cli.getTelefone());
			statement.executeUpdate();
	
			statement.close();
			conn.close();
		} catch (SQLException e) {
		    	throw new RuntimeException(e);
		}
    	}
	
	public void delete(Cliente cli) {
		String sql = "DELETE FROM CLIENTE where cpf = ?";
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, cli.getCpf());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    	}
}
```

Com essa nova organização, separa-se também os módulos do sistema. Assim, obtém-se uma melhor arquitetura de software. Observe essa representação do resultado utilizando a arquitetura em camadas:

<img src="/img/imagem_2021-11-25_021520.png" alt="My cool logo"/>

Sem aplicar o SRP não haveria segregação da camada de dados com a camada de aplicação. Isso implica em, para diferentes alterações, o mesmo módulo ser atualizado. Ou seja, ao inserir alguma modificação com um bug, o módulo inteiro poderia ser afetado. A divisão por responsabilidades elimina esse problema. Sendo assim, conclui-se que a utilização do SRP contribui para a manitenabilidade do sistema.

<div id='ocp'/> 

## Open-Closed Principle (OCP)

Esse princípio diz que classes (módulos, entidades, etc.) devem ser abertos para extensão e fechados para modificação. 
Os princípios SOLID possuem muita comunicação entre si. Perceba que ao aplicar o SRP, fecha-se o módulo para modificação, conforme dita também o OCP. 
Para compreender melhor esse princípio, considere o exemplo abaixo:


```java
public class IdentificaService {
	private String tipo;
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
```

Nesse exemplo, define-se que a identificação de admin é diferente da identificação de cliente. IdentificaCliente e IdentificaAdmin são especializações da classe IdentificaService. A variável tipo é responsável por guardar qual o tipo de identificação que deve ser realizado por esse identificador. Sendo assim. a implementação fica da seguinte maneira:

```java
public void identificaUsuario() {
	if (tipo.equals("Cliente"))
			this.identificarCliente();
    	else if (tipo.equals("Admin"))
    		this.identificarAdmin();
}
```

Suponha que a empresa dona desse sistema contratou um serviço que funciona na nuvem, e agora deve ser implementado um novo tipo de identificador para um novo tipo de usuário, que fica hospedado na nuvem.
Seria então necessário criar mais um else-if na implementação. O problema em adicionar mais if’s é que existem outras partes da aplicação que também fazem essa verificação para invocar métodos específicos de cada classe. Além dessa adição modificar mais de um componente, qualquer mudança posterior poderia gerar um efeito em cascata (necessitando cada vez de mais mudança). Todos esses problemas evidenciam que esse design não é nem fechado para modificações nem aberto para extensão.
Alterando o código para atender à OCP, obtém o seguinte resultado:

```java
public abstract class IdentificaService {
	public abstract void identificar();
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
 
public class Identificador {
   public void identificaUsuario(IdentificaService serv) {
        serv.identificar();
}
```

IdentificaService passa a ser uma entidade abstrata, visto que não é necessário instanciá-la. É criado um método abstrato que será responsável pela dentificação, identificar(). Para cada forma de identificação, define-se uma implementação da abstração. Cada classe que implementa a classe abstrata sobrescreve a função identificar().
Agora, sempre que surgir um novo meio de identificação, não será necessário adicionar nenhum if.

<img src="/img/imagem_2021-11-25_024416.png"/>

<div id='lsp'/>

## Liskov Substitution Principle (LSP)

Esse princípio diz que deve haver a possibilidade de se substituir a classe base pela sua classe derivada sem alterar o correto funcionamento do software. Ou seja, a classe filha não pode ser nem mais forte nem mais fraca do que a classe pai, e vice-versa. 
Considere o mesmo problema de identificação abordado acima, que trata IdentificaAdmin como uma especialização de IdentificaCliente. 

<img src="/img/imagem_2021-11-25_024920.png"/>

Essa abordagem funciona. Porém, no exemplo abaixo, note que nas linhas 15 e 16 uma nova funcionalidade é adicionada. Todo dia às 4h o sistema entra em manutenção. Neste momento, as tentativas de identificação devem retornar uma HoraManutencaoException. 

```java
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
```

A classe filha, então, é mais fraca do que a classe pai, violando o LSP. Como a exceção foi implementada apenas na classe pai, um exemplo de problema que pode vir dessa violação é a identificação de administradores mesmo no horário de manutenção. 

<div id='isp'/>

## Interface Segregation Principle (ISP)

Esse princípio diz que clientes não devem depender de interfaces que não utilizam. Ou seja, muitas interfaces específicas são melhores do que uma interface mais genérica.
Considere os seguintes DAOs, ClientesDAO e ConsultasDAO.

<img src="/img/imagem_2021-11-25_033837.png"/>

```java
import br.ufscar.dc.dsw.domain.Cliente;

public class ClienteDAO extends GenericDAO {

	public void insert(Cliente cliente) throws Exception {
    		// implementação
	}
    
	public ArrayList<Cliente> getAll() {
		// implementação
	}

	public void delete(Cliente cliente) {
        	// implementação
	}

	public void update(Cliente cliente) {
		// implementação
	}

	public Cliente get(Long id) {
        	// implementação
	}
    
	public Cliente getbyID(Long id) {
        	// implementação
	}
    
	public Cliente getbyLogin(String login) {
		// implementação
	}
}
```

```java
import br.ufscar.dc.dsw.domain.Consulta;

public class ConsultaDAO extends GenericDAO {
	
	public void insert(Consulta consulta) throws Exception {
    		// implementação
	}
    
	public void delete(Consulta consulta) {
        	// implementação
	}

	public ArrayList<Consulta> getbyCliente(Long id) throws Exception {
        	// implementação
	}
    
	public ArrayList<Consulta> getbyProfissional(Long id) throws Exception {
		// implementação
	}
}
```
Deseja-se criar uma interface para as classes DAO. Note que, apesar de ClientesDAO e ConsultasDAO possuírem alguns métodos em comum, há métodos exclusivos. Criar apenas uma interface genérica IDAO com a união dos métodos de ClientesDAO e ConsultasDAO faria com que essas classes herdassem os métodos uma das outras, inclusive os métodos que não utilizam, ocorrendo então a violação da ISP, conforme ilustrado abaixo.

<img src="/img/imagem_2021-11-25_033656.png"/> 

Observe que a classe ClientesDAO tem acesso porém não utiliza métodos da classe ConsultasDAO, por ter sido criado uma interface genérica. 
A solução é criar uma interface para cada DAO, com seus respectivos métodos. Assim, não há herança de métodos inutilizados.

<img src="/img/imagem_2021-11-25_033530.png"/> 

**dao.ClienteDAO

<div id='dip'/> 

## Dependency Inversion Principle (DIP)
