# SOLID

Trabalho final de Programação Orientada a Objetos Avançada (POOA), matéria ministrada por professor Delano.

Autor: Anderson Henrique Giacomini RA: 769720

*******
Tabela de conteúdo:
 1. [O que é SOLID?](#solid)
 2. [Single Responsibility Principle (SRP)](#srp)
 3. [Open-Closed Principle (OCP)](#ocp)
 4. [Liskov Substitution Principle (LSP)](#lsp)
 5. [Interface Segregation Principle (ISP)](#isp)
 6. [Dependency Inversion Principle (DIP)](#dip)

	6.1. [Discussões](#dip0)

*******

<div id='solid'>
	
## O que é SOLID?

Cada letra da palavra SOLID corresponde a um princípio de design de classes. Ou seja, não se trata de regras, e sim boas práticas que levam a um código melhor. Esse artigo buscará explicar cada um desses princípios e fazer análise de exemplos.

<div id='srp'/>  

## Single Responsibility Principle (SRP)

Esse princípio diz que uma classe deve ter uma única razão para mudar.
Considere a classe Cliente abaixo:

**domain.Cliente**:

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
    public boolean identificarCliente() {
        // codigo para identificar cliente
    }
}
 
public class identificarAdmin : IdentificaService {
    public boolean identificarAdmin() {
        // codigo para identificar admin
    }
}

```

Nesse exemplo, define-se que a identificação de admin é diferente da identificação de cliente. IdentificaCliente e IdentificaAdmin são especializações da classe IdentificaService. A variável tipo é responsável por guardar qual o tipo de identificação que deve ser realizado por esse identificador. Sendo assim. a implementação fica da seguinte maneira:

```java
public boolean identificaUsuario() {
	if (tipo.equals("Cliente"))
		return identificarCliente();
    	else if (tipo.equals("Admin"))
    		return identificarAdmin();
}
```

Suponha que a empresa dona desse sistema contratou um serviço que funciona na nuvem, e agora deve ser implementado um novo tipo de identificador para um novo tipo de usuário, que fica hospedado na nuvem.
Seria então necessário criar mais um else-if na implementação. O problema em adicionar mais if’s é que existem outras partes da aplicação que também fazem essa verificação para invocar métodos específicos de cada classe. Além dessa adição modificar mais de um componente, qualquer mudança posterior poderia gerar um efeito em cascata (necessitando cada vez de mais mudança). Todos esses problemas evidenciam que esse design não é nem fechado para modificações nem aberto para extensão.
Alterando o código para atender à OCP, obtém o seguinte resultado:

```java
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
```

IdentificaService passa a ser uma entidade abstrata, visto que não é necessário instanciá-la. É criado um método abstrato que será responsável pela dentificação, identificar(). Para cada forma de identificação, define-se uma implementação da abstração. Cada classe que implementa a classe abstrata sobrescreve a função identificar().
Agora, sempre que surgir um novo meio de identificação, não será necessário adicionar nenhum if.

<img src="/img/imagem_2021-11-25_105111.png"/>

<div id='lsp'/>

## Liskov Substitution Principle (LSP)

Esse princípio diz que deve haver a possibilidade de se substituir a classe base pela sua classe derivada sem alterar o correto funcionamento do software. Ou seja, a classe filha não pode ser nem mais forte nem mais fraca do que a classe pai, e vice-versa. 
Considere esse novo design para uma etapa de identificação:

<img src="/img/imagem_2021-11-25_024920.png"/>

Essa abordagem funciona. Porém, no exemplo abaixo, note que, na classe IdentificaCliente, uma nova funcionalidade é adicionada: todo dia às 4h o sistema entra em manutenção. Neste momento, as tentativas de identificação devem retornar uma HoraManutencaoException. 

```java
import java.util.Calendar;
 
public abstract class IdentificaService {
	public abstract boolean identificar();
}
	
public class IdentificaCliente : IdentificaService{
	if (Calendar.getInstance().get(Calendar.HOUR).equals(4))
		throw new HoraManutencaoException();
	public boolean identificar() {
		// codigo para identificar cliente
	}
}
 
public class identificarAdmin : IdentificaCliente {
	public boolean identificar() {
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

**dao.IClienteDAO**:
```java
import br.ufscar.dc.dsw.domain.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Long> {
	Cliente findById(long id);

	Cliente findBycpf(String cpf);

	List<Cliente> findAll();

	Cliente save(Cliente cliente);

	void deleteById(Long id);
}
```

**dao.IConsultaDAO**:
```java
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Pessoa;
import br.ufscar.dc.dsw.domain.Profissional;

public interface IConsultaDAO extends CrudRepository<Consulta, Long> {
	Consulta findById(long id);

	List<Consulta> findAll();

	Consulta save(Consulta consulta);

	void deleteById(Long id);
	
	List<Consulta> findAllByProfissional(Profissional u);

	List<Consulta> findAllByCliente(Cliente cli);
	
	Consulta findByProfissionalAndDataHora(Profissional profissional, String dataHora);

	Consulta findByClienteAndDataHora(Cliente cliente, String dataHora);
}
```

<div id='dip'/> 

## Dependency Inversion Principle (DIP)

O Princípio de Inversão de Dependência traz duas regras: 
> Módulos de alto nível não devem depender de módulos de baixo nível, ambos devem depender de abstrações.

> Abstrações não devem depender de detalhes, detalhes que devem depender de abstrações.

Mas o que é um módulo de alto nível?
Robert Martin define que módulo de alto nível são as classes que executam algo utilizando alguma ferramenta e módulo de baixo nível são essas ferramentas. Ou seja, os módulos de alto nível costumam mudar mais do que os de baixo.
Considere o mesmo problema de identificação abordado acima, que trata IdentificaAdmin como uma especialização de IdentificaCliente. 

<img src="/img/imagem_2021-11-25_122653.png"/> 

Diferentemente do exemplo anterior, neste, IdentificaAdmin implementa IdentificaCliente, que é uma ferramenta de ControleAcesso.
Há uma relação direta entre ControleAcesso e IdentificaCliente, ou seja, um módulo de alto nível depende de um módulo de baixo nível, ocorrendo a violação do DIP. Em outras palavras, toda vez que se desejar utilizar a classe ControleAcesso, a classe IdentificaCliente terá que vir junto. 

```java
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
```
	
```java
public class IdentificaCliente {
	public boolean identificar() {
		// codigo para identificar cliente
	}
}
```
	
```java
public class identificarAdmin : IdentificaService {
	@Override
	public boolean identificar() {
		// codigo para identificar admin
	}
}
```
	
Note o acesso direto em: private **IDentificaCliente** service;

Para resolver esse problema, cria-se a interface Identificador. 

<img src="/img/imagem_2021-11-25_122822.png"/> 

Agora ControleAcesso acessa a interface, que é implementada pela classe IdentificaCliente.

```java
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
```
	
```java
interface Identificador(){
        public boolean identificar() {}
}
```
	
```java
public class IdentificaCliente implements Identificador{
	public boolean identificar() {
		// codigo para identificar cliente
	}
}
```

Enquanto antes ControleAcesso dependia de IdentificaCliente, agora IdentificaCliente que depende de Produto, ocorrendo a inversão de dependência. Essa mudança soluciona a DIP e permite a reutilização da classe ControleAcesso.
	
<div id='dip0'/> 
	
### Discussoes
	
Com base nos dois artigos abaixo, será debatido algumas questões sobre o Princípio da Inversão de Dependencia (DIP).
	
>Martin Fowler. Inversion of Control. martinfowler.com, 2005.
https://martinfowler.com/bliki/InversionOfControl.html
	
>Martin Fowler. Inversion of Control Containers and the Dependency Injection pattern.
martinfowler.com, 2004.
https://martinfowler.com/articles/injection.html
	
	

	
#### O que é inversão de controle? O que é injeção de dependência? Qual a relação entre inversão de dependência, inversão de controle e injeção de dependência? 
	
Considere a classe A, a interface I, e as classem implementadoras de I, B e C. 
Quando há um autoacoplamento entre dois serviços, por exemplo, a classe B é instanciada em A, caso surja a necessidade de troca desse serviço B por um serviço C, será necessário abrir a classe A, que não deveria ser aberta para essa mudança, e dentro dela trocar a instanciação de B para C. Uma forma de resolver esse problema é com a inversão de controle: Remover esse poder de decisão da classe A (remover a instanciação, deixando apenas a declatação da dependência, a interface I). Um mecanismo de inversão de controle é a injeção de dependência, que pode ser realizada, por exemplo, com a criação de um construtor que recebe como argumento a interface I que é implementada por B e C. Assim, poderá ser instanciado B ou C em A sem alterar nada dentro de A (essa alteração poderia ser feita, por exemplo, na main).
A inversão de dependência se relaciona com os demais conceitos acima pois quem quem aplica ela é a própria injeção de dependência
txt

