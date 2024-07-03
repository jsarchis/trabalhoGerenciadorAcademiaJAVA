package br.com.jsarchis.controller;

import br.com.jsarchis.model.Professor;
import br.com.jsarchis.model.dao.ProfessorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ExcluirProfessorAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        int idProf = Integer.parseInt(req.getParameter("idProfDel"));

        Professor p = new ProfessorDAO().find(idProf);
        new ProfessorDAO().deletar(p);
        req.setAttribute("msgProfDel", "Professor Excluido");
        new HomeAction().executar(req,resp);
    }
}
