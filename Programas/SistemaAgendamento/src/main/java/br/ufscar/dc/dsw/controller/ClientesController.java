package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.*;
import br.ufscar.dc.dsw.domain.*;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/Ctlcliente/*")
public class ClientesController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private ClientesDAO dao;

    @Override
    public void init() {
        dao = new ClientesDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {           
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                	Long id = Long.parseLong(request.getParameter("id"));
                    Clientes cliente = new Clientes(id);
                    dao.delete(cliente);
                    request.getSession().setAttribute("tipo", "admin");
                    response.sendRedirect("/SistemaAgendamento/Admin.jsp");
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException | ParseException e) {
            throw new ServletException(e);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
    	ArrayList<Clientes> listaClientes = dao.getAll();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/lista.jsp");
        dispatcher.forward(request, response);
        */
    	request.getSession().setAttribute("tipo", "admin");
        response.sendRedirect("/SistemaAgendamento/Admin.jsp");
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Clientes cliente = dao.get(id);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        
        Long id = Long.parseLong(request.getParameter("id"));
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Integer ano = Integer.parseInt(request.getParameter("ano"));
        Integer mes = Integer.parseInt(request.getParameter("mes"));
        Integer dia = Integer.parseInt(request.getParameter("dia"));
              		
        Clientes cliente = new Clientes(id, cpf, email, senha, nome, telefone, sexo, ano, mes, dia);
        dao.insert(cliente);
        request.getSession().setAttribute("tipo", "admin");
        response.sendRedirect("/SistemaAgendamento/Admin.jsp");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        
        Integer ano = Integer.parseInt(request.getParameter("ano"));
        Integer mes = Integer.parseInt(request.getParameter("mes"));
        Integer dia = Integer.parseInt(request.getParameter("dia"));
              		
        Clientes cliente = new Clientes(id, cpf, email, senha, nome, telefone, sexo, ano, mes, dia);
        dao.update(cliente);
        request.getSession().setAttribute("tipo", "admin");
        response.sendRedirect("/SistemaAgendamento/Admin.jsp");
    }

}