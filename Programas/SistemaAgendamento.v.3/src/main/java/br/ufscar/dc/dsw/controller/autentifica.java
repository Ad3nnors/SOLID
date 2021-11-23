package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufscar.dc.dsw.dao.ClientesDAO;
import br.ufscar.dc.dsw.dao.ProfissionaisDAO;
import br.ufscar.dc.dsw.domain.Clientes;
import br.ufscar.dc.dsw.domain.Profissionais;

@WebServlet(name = "Autentificação", urlPatterns = { "/autentifica.jsp" })
public class autentifica extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("bOK") != null) {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String tipo = request.getParameter("tipo");
				
			if (tipo.equals("cliente")) {
				ClientesDAO dao = new ClientesDAO();
				Clientes usuario = dao.getbyLogin(login);
				if (usuario != null) {
					if (usuario.getSenha().equals(senha)) {
						request.getSession().setAttribute("cliente", usuario);
						response.sendRedirect("Cliente.jsp");
						return;
					} 
				} 
			}
			
			if (tipo.equals("profissional")) {
				ProfissionaisDAO dao = new ProfissionaisDAO();
				Profissionais usuario = dao.getbyLogin(login);
				if (usuario != null) {
					if (usuario.getSenha().equals(senha)) {
						request.getSession().setAttribute("profissional", usuario);
						response.sendRedirect("Profissional.jsp");
						return;
					} 
				} 
			}
			
			if (tipo.equals("admin")) {
				if (senha.equals("admin")) {
					if (login.equals("admin")) {
						request.getSession().setAttribute("tipo", tipo);
						response.sendRedirect("Admin.jsp");
						return;
					} 
				} 
			}
				
		}
		
		response.sendRedirect("login.jsp");
	}
}