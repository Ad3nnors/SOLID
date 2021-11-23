<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Autenticação de Usuário</title>
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
			<h1>Autenticação de Usuário</h1>

			<div style="overflow-x: auto;">


				<form method="post" action="autentifica.jsp">
					<table>
						<tr>
							<th>Login:</th>
							<td><input type="text" name="login" value="${param.login}" /></td>
						</tr>

						<tr>
							<th>Senha:</th>
							<td><input type="password" name="senha" /></td>
						</tr>

						<tr>
							<th>Tipo:</th>
							<td><select id="tipo" name="tipo">
									<option value="cliente">Cliente</option>
									<option value="profissional">Profissional</option>
									<option value="admin">Admin</option>
							</select></td>
						</tr>

						<tr>
							<td colspan="2"><input class="botao" type="submit" name="bOK"
								value="Entrar" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="top_cover"></div>
	</section>
</body>
</html>