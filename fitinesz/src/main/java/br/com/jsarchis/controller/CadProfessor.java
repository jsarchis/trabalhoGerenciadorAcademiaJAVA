package br.com.jsarchis.controller;

import br.com.jsarchis.model.Professor;
import br.com.jsarchis.model.dao.ProfessorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CadProfessor implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        Professor prof =  new Professor();
        prof.setLogin(req.getParameter("loginProf"));
        prof.setNome(req.getParameter("nomeProf"));
        prof.setSenha(req.getParameter("senhaProf"));
        prof.setCref(req.getParameter("crefProf"));
        new ProfessorDAO().inserir(prof);
        new HomeAction().executar(req,resp);
    }
}
