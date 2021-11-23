<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufscar.dc.dsw.domain.Profissionais"%>
<%@page import="br.ufscar.dc.dsw.dao.ProfissionaisDAO"%>
<%
ProfissionaisDAO daoprofissionais = new ProfissionaisDAO();
ArrayList<Profissionais> profissionais = null;
String area = request.getParameter("a");
if (area != null) {
	profissionais = daoprofissionais.getbyArea(area);
} else {
	profissionais = daoprofissionais.getAll();
}
%>


<!DOCTYPE html>
<fmt:bundle basename="messages">
	<html>
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="content-language" content="lat" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="description"
	content="Sistema para agendamento de consultas online com profissionais" />
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1, maximum-scale=1, user-scalable=no" />
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
		<div class="top_cover">
			<h1>
				<a href="login.jsp">Bem Vindo! Entre na sua conta</a><br> <br>
			</h1>
		</div>
		<div class="top_cover">
			<h2>
				Confira nossa lista de profissionais
				<%
			if (area != null) {
			%>
				em
				<%=area%>
				<%
				}
				%>
			</h2>

			<div style="overflow-x: auto;">
				<table>
					<thead>
						<tr>
							<th>CPF</th>
							<th>Nome</th>
							<th>Area</th>
							<th>Especialidade</th>
							<th>Email</th>
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
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
			<form action="" method="POST">
				<input type="text" id="a" name="a" size="20" value="Insira a Área Procurada"><input class="botao" type="submit" value="Pesquisar" />
			</form>

		</div>
		<div class="top_cover"></div>
	</section>

	<main id="#">
		<p>
			<a>Na tabela acima,</a> você pode realizar uma busca por profissionais de acordo com a área de atuação.
		</p>
	</main>

	<section class="container2x5">
		<div>
			<center>
				<h1>Sistema para Agendamento de Consultas com Profissionais Independentes</h1>
			</center>
			<br></br><br></br>
			<h2>Confira Abaixo Algumas Funcionalidades Deste Sistema :)</h2>
		</div>
		<div>
			<ul>
				<li>
					<div id="image_beside" class="_1">

						<h2>Conteúdo Aberto</h2>
						Lista de todos os profissionais cadastrados antes do Login
					</div>
				</li>
				<li>
					<div id="image_beside" class="_2">

						<h2>Filtro</h2>
						Filtro de profissionais de acordo com a Área
					</div>
				</li>
				<li>
					<div id="image_beside" class="_3">

						<h2>Controle de Usuários</h2>
						CRUD das tuplas de usuários com o login de Administrador
					</div>
				</li>
				<li>
					<div id="image_beside" class="_4">

						<h2>Consulta</h2>
						Lista as consultas de cada cliente e cada profissional após o login
					</div>
				</li>
				<li>
					<div id="image_beside" class="_5">

						<h2>Profissionais</h2>
						Visualização das consultas agendadas com o login de Profissional

					</div>
				</li>
				<li>
					<div id="image_beside" class="_6">

						<h2>Clientes</h2>
						Visualização e adição de consultas com o login de Cliente	
					</div>
				</li>

			</ul>
		</div>
	</section>

</body>
	</html>
</fmt:bundle>