package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ConsultasDAO;
import br.ufscar.dc.dsw.domain.Consultas;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CriaConsulta", urlPatterns = { "/consulta/criar" })
public class CriaConsultasController extends HttpServlet {

    private static final long serialVersionUID = 1L;
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            if(request.getSession().getAttribute("cliente") == null){
                response.sendRedirect("/SistemaAgendamento/login");
                return;
            }
            //public Consultas(Long id, Long cliente, Long profissional, String nome_C, String nome_P, String area, String especialidade, int ano, int mes, int dia, int hora){
            ConsultasDAO consultaDAO = new ConsultasDAO();
            Consultas consulta = new Consultas(Long.parseLong("99"), Long.parseLong(request.getParameter("cliente")), Long.parseLong(request.getParameter("profissional")), Integer.parseInt(request.getParameter("ano")), Integer.parseInt(request.getParameter("mes")),Integer.parseInt(request.getParameter("dia")), Integer.parseInt(request.getParameter("hora")));
            consultaDAO.insert(consulta);
            response.sendRedirect("/SistemaAgendamento/Cliente.jsp");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}