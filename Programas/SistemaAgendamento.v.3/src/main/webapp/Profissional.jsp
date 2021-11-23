<%@page import="br.ufscar.dc.dsw.domain.Clientes"%>
<%@page import="br.ufscar.dc.dsw.domain.Consultas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufscar.dc.dsw.domain.Profissionais"%>
<%@page import="br.ufscar.dc.dsw.dao.ConsultasDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
Profissionais profissional = (Profissionais) request.getSession().getAttribute("profissional");
if (profissional == null) {
	response.sendRedirect("login.jsp");
	return;
}
ConsultasDAO consultaDAO = new ConsultasDAO();
ArrayList<Consultas> consultas = consultaDAO.getbyProfissional(profissional.getId());
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<fmt:bundle basename="messages">
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profissional Page</title>
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
		<h1>
			Olá,
			<%=profissional.getNome()%>!
		</h1>

		<div class="top_cover">
			<h2>Confira Seus Agendamentos</h2>

			<div style="overflow-x: auto;">
				<table border="1">
					<thead>
						<tr>
							<th>Nome do cliente</th>
							<th>Data da consulta</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Consultas consulta : consultas) {
						%>
						<tr>
							<td><%=consulta.getNome_C()%></td>
							<td><%=consulta.getData()%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
		<div class="top_cover"></div>
	</section>
</body>
	</html>

</fmt:bundle>