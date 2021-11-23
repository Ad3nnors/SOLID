package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionaisDAO;
import br.ufscar.dc.dsw.domain.Profissionais;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/Ctlprofissional/*")
public class ProfissionaisController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private ProfissionaisDAO dao;

    @Override
    public void init() {
        dao = new ProfissionaisDAO();
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
                    System.out.println("removeuu");
                    Profissionais profissional = new Profissionais(id);
                    dao.delete(profissional);
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
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        ArrayList<Profissionais> listaProfissionais = dao.getAll();
        request.setAttribute("listaProfissionais", listaProfissionais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissionais/lista.jsp");
        dispatcher.forward(request, response);
        */
        request.getSession().setAttribute("tipo", "admin");
        response.sendRedirect("/SistemaAgendamento/Admin.jsp");
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissionais/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Profissionais profissional = dao.get(id);
        request.setAttribute("profissional", profissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissionais/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("UTF-8");
        
        
        Long id = Long.parseLong(request.getParameter("id"));
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String area = request.getParameter("area");
        String especialidade = request.getParameter("especialidade");

        
        Profissionais profissional = new Profissionais(id, cpf, email, senha, nome, area, especialidade);
        dao.insert(profissional);
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
        String area = request.getParameter("area");
        String especialidade = request.getParameter("especialidade");
        
        Profissionais profissional = new Profissionais(id, cpf, email, senha, nome, area, especialidade);
        dao.update(profissional);
        request.getSession().setAttribute("tipo", "admin");
        response.sendRedirect("/SistemaAgendamento/Admin.jsp");
    }

}