<%@page import="java.time.LocalDateTime"%>
<%@page import="br.ufscar.dc.dsw.dao.ClientesDAO"%>
<%@page import="br.ufscar.dc.dsw.domain.Profissionais"%>
<%@page import="br.ufscar.dc.dsw.dao.ProfissionaisDAO"%>
<%@page import="br.ufscar.dc.dsw.dao.ConsultasDAO"%>
<%@page import="br.ufscar.dc.dsw.domain.Consultas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufscar.dc.dsw.domain.Clientes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
Clientes cliente = (Clientes) request.getSession().getAttribute("cliente");
if (cliente == null) {
	response.sendRedirect("login");
	return;
}
ConsultasDAO consultaDAO = new ConsultasDAO();
ProfissionaisDAO profissionalDAO = new ProfissionaisDAO();
ArrayList<Consultas> consultas = consultaDAO.getbyCliente(cliente.getId());
ArrayList<Profissionais> profissionais = profissionalDAO.getAll();
%>


<!DOCTYPE html>
<fmt:bundle basename="messages">
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Patient Page</title>
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

	<h1>
		Olá,
		<%=cliente.getNome()%>!
	</h1>
	<section class="cover">
		<div class="top_cover"></div>
		<div class="top_cover">
			<h2>Confira Seus Agendamentos</h2>

			<div style="overflow-x: auto;">
				<table border="1">
					<thead>
						<tr>
							<th>Nome do profissional</th>
							<th>Area de atuação</th>
							<th>Especialidade</th>
							<th>Data</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Consultas consulta : consultas) {
						%>
						<tr>
							<td><%=consulta.getNome_P()%></td>
							<td><%=consulta.getArea()%></td>
							<td><%=consulta.getEspecialidade()%></td>
							<td><%=consulta.getData()%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>

			<div style="overflow-x: auto;">
				<table border="1">

					<tbody>
						<tr>
							<form action="consulta/criar" method="post">
								<input type="hidden" value="<%=cliente.getId()%>" name="cliente" />
								<th>Nova Consulta: <select name="profissional"
									value="Profissional">
										<%
										for (Profissionais p : profissionais) {
										%>
										<option value="<%=p.getId()%>">
											<%=p.getNome()%> -
											<%=p.getArea()%> -
											<%=p.getEspecialidade()%>
										</option>
										<%
										}
										%>
								</select></th>
								<th>Dia: <input name="dia" value="" type="number" min="1"
									max="31" />
								</th>
								<th>Mes: <input name="mes" value="" type="number" min="1"
									max="12" />
								</th>
								<th>Ano: <input name="ano" value="" type="number"
									min="<%=LocalDateTime.now().getYear()%>" />
								</th>
								<th>Hora: <input name="hora" value="" type="number" min="7"
									max="19" />
								</th>
								<th><input class="botao" type="submit" value="Agendar" /></th>
					</tbody>
				</table>
				</form>
			</div>
			<div class="top_cover"></div>
	</section>
</body>
	</html>
</fmt:bundle>