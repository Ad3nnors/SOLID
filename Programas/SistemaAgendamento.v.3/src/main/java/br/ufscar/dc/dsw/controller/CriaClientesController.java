package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.*;
import br.ufscar.dc.dsw.domain.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CriaCliente", urlPatterns = { "/cliente/criar" })
public class CriaClientesController extends HttpServlet {

    private static final long serialVersionUID = 1L;
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
        	String tipo = (String) request.getSession().getAttribute("tipo");
        	if (!tipo.equals("admin")) {
        		RequestDispatcher rd = request.getRequestDispatcher("/SistemaAgendamento/login.jsp");
        		rd.forward(request, response);
        		return;
        	}
        	
         

            ClientesDAO clienteDAO = new ClientesDAO();
            Clientes cliente = new Clientes();
            
            cliente.setCpf(request.getParameter("cpf"));
            cliente.setEmail(request.getParameter("email"));
            cliente.setSenha(request.getParameter("senha"));
            cliente.setNome(request.getParameter("nome"));
            cliente.setTelefone(request.getParameter("telefone"));
            cliente.setSexo(request.getParameter("sexo"));
            Integer ano = Integer.parseInt(request.getParameter("ano"));
            Integer mes = Integer.parseInt(request.getParameter("mes"));
            Integer dia = Integer.parseInt(request.getParameter("dia"));
            cliente.setNascimento( ano,  mes,  dia);
            
            clienteDAO.insert(cliente);
            response.sendRedirect("/SistemaAgendamento/Cliente.jsp");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}