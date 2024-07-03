package br.com.jsarchis.controller;

import br.com.jsarchis.model.Professor;
import br.com.jsarchis.model.dao.ProfessorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AlteraProfAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        Professor p = new Professor(Integer.parseInt(req.getParameter("altIdProf")),req.getParameter("altNomeProf"),req.getParameter("altLoginProf"),req.getParameter("altSenhaProf"),req.getParameter("altCREFProf"));
        new ProfessorDAO().update(p);
        req.setAttribute("msgProfAlterado", "Alteração realizada com sucesso");
        new HomeAction().executar(req,resp);
    }
}
