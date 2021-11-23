<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="br.ufscar.dc.dsw.dao.*"%>
<%@page import="br.ufscar.dc.dsw.domain.*"%>
<%@page import="br.ufscar.dc.dsw.controller.*"%>
<%@page import="java.util.ArrayList"%>
<%
String tipo = (String) request.getSession().getAttribute("tipo");
if (!tipo.equals("admin")) {
	RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
	rd.forward(request, response);
}
ProfissionaisDAO daoprofissionais = new ProfissionaisDAO();
ArrayList<Profissionais> profissionais = daoprofissionais.getAll();
ClientesDAO daoclientes = new ClientesDAO();
ArrayList<Clientes> clientes = daoclientes.getAll();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<fmt:bundle basename="messages">
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
<style type="text/css">
@import url("Estilo.css");
</style>
</head>
<body>
	<header class="containerHeader">

		<nav>
			<input type="checkbox" id="check"> <label for="check"
				class="checkbtn"> <i class="fas fa-bars"></i>
			</label>
			<ul>
				<li><a class="active"
					href="http://localhost:8080/SistemaAgendamento/">Início</a></li>

				<li><a href="login.jsp">Login</a></li>
			</ul>
		</nav>
	</header>

	<section class="cover">
		<div class="top_cover"></div>

		<h1>Olá, Admin</h1>
		<div class="top_cover">
			<h2>Confira nosso CRUD de Profissionais</h2>

			<div style="overflow-x: auto;">
				<table border="1">
					<thead>
						<tr>
							<th>CPF</th>
							<th>Nome</th>
							<th>Area</th>
							<th>Especialidade</th>
							<th>Email</th>
							<th>ações</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Profissionais profissional : profissionais) {
						%>
						<tr>
							<td><%=profissional.getCpf()%></td>
							<td><%=profissional.getNome()%></td>
							<td><%=profissional.getArea()%></td>
							<td><%=profissional.getEspecialidade()%></td>
							<td><%=profissional.getEmail()%></td>
							<td><a class="botao"
								href="edicaoProfissional.jsp?id=<%=profissional.getId()%>">Editar</a> 
								<a class="botao"
								href="Ctlprofissional/remocao?id=<%=profissional.getId()%>"
								onclick="return confirm('Tem certeza de que deseja excluir este item?');">Excluir</a>
							</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>

				<div style="overflow-x: auto;">
				<table border="1">
					<thead><th>NOVO CLIENTE:</th></thead>
					<tbody>
						<tr>
							<form action="cliente/criar" method="post">
								
							<td>CPF: <input type="text" id="cpf" name="cpf" required value="" /></td>
							<td>E-MAIL: <input type="text" id="email" name="email" required value="" /></td>
							<td>SENHA: <input type="password" id="senha" name="senha" required value="" /></td>
							<td>NOME: <input type="text" id="nome" name="nome" required value="" /></td>
							<td>TELEFONE: <input type="number" id="telefone" name="telefone" required value="" /></td>
							<td>SEXO: <input type="text" id="sexo" name="sexo" required size="2" value="" /></td>
							<td>Dia: <input name="dia" value="" type="number" min="1" max="31" /> </td>
							<td>Mes: <input name="mes" value="" type="number" min="1" max="12" /> </td>
							<td>Ano: <input name="ano" value="" type="number" min="1900" /> </td>
							<td><input class="botao" type="submit" value="Cadastrar" /></td>
							
							</form>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		</div>
		<div class="top_cover"></div>
		<div class="top_cover">
			<h2>Confira nosso CRUD de Clientes</h2>

			<div style="overflow-x: auto;">
				<a class="botao" href="Ctlcliente/cadastro">Adicionar Novo</a>
				<table border="1">
					<thead>
						<tr>
							<th>CPF</th>
							<th>Nome</th>
							<th>Sexo</th>
							<th>Data de nascimento</th>
							<th>Telefone</th>
							<th>Email</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Clientes cliente : clientes) {
						%>
						<tr>
							<td><%=cliente.getCpf()%></td>
							<td><%=cliente.getNome()%></td>
							<td><%=cliente.getSexo()%></td>
							<td><%=cliente.getNascimento()%></td>
							<td><%=cliente.getTelefone()%></td>
							<td><%=cliente.getEmail()%></td>
							<td><a class="botao"
								href="edicaoCliente.jsp?id=<%=cliente.getId()%>">Editar</a> <a
								class="botao" href="Ctlcliente/remocao?id=<%=cliente.getId()%>"
								onclick="return confirm('Tem certeza de que deseja excluir este item?');">Deletar</a>
							</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
			<div class="top_cover"></div>
	</section>
</body>
	</html>
</fmt:bundle>