<%@page import="br.ufscar.dc.dsw.dao.*"%>
<%@page import="br.ufscar.dc.dsw.domain.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
ClientesDAO clienteDAO = new ClientesDAO();
Clientes cliente = clienteDAO.get(Long.parseLong(request.getParameter("id")));
%>

<fmt:bundle basename="messages">
	<!DOCTYPE html>
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Client</title>
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

				<li><a href="login.jsp">Logout</a></li>
			</ul>
		</nav>
	</header>
	<section class="cover">
		<div class="top_cover"></div>

		<div class="top_cover">
			<h2>Edição de Cliente</h2>

			<div style="overflow-x: auto;">
				<form action="Ctlcliente/atualizacao" method="POST">
					<table border="1" size="40%">
						<tr>
							<td><label for="cpf">CPF</label></td>
							<td><input type="text" id="cpf" name="cpf" size="80%" required value="<%=cliente.getCpf()%>" /></td>
						</tr>
						<tr>
							<td><label for="email">E-mail</label></td>
							<td><input type="text" id="email" name="email" size="80%" required value="<%=cliente.getEmail()%>" /></td>
						</tr>

						<tr>
							<td><label for="senha">Senha</label></td>
							<td><input type="password" id="senha" name="senha" size="80%" required value="<%=cliente.getSenha()%>" /></td>
						</tr>
						<tr>
							<td><label for="nome">Nome</label></td>
							<td><input type="text" id="nome" name="nome" size="80%" required value="<%=cliente.getNome()%>" /></td>
						</tr>
						<tr>
							<td><label for="telefone">Telefone</label></td>
							<td><input type="number" id="telefone" name="telefone" size="80%" required value="<%=cliente.getTelefone()%>" /></td>
						</tr>
						<tr>
							<td><label for="sexo">Sexo</label></td>
							<td><input type="text" id="sexo" name="sexo" size="80%" required value="<%=cliente.getSexo()%>" /></td>
						</tr>
						<tr>
							<td><label for="nascimento">Data de nascimento</label></td>
							<td><input type="date" id="nascimento" name="nascimento" size="80%" 
								required value="<%=cliente.getNascimento()%>" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input class="botao"
								type="submit" value="Salvar" size="80%" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="top_cover"></div>
	</section>
</body>
	</html>
</fmt:bundle>