# SOLID

Cada letra da palavra SOLID corresponde a um princípio de design de classes. Ou seja, não se trata de regras, e sim boas práticas que levam a um código melhor. Esse artigo buscará explicar cada um desses princípios e fazer análise de exemplos.

*******
Tabela de conteúdo 
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

*domain.Cliente*:

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

*dao.ClienteDAO*:

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

<div id='lsp'/>

<div id='isp'/>

<div id='dip'/> 
