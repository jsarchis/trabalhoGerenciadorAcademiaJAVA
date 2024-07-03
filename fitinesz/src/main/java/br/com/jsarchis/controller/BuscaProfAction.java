package br.com.jsarchis.controller;


import br.com.jsarchis.model.Professor;
import br.com.jsarchis.model.dao.ProfessorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class BuscaProfAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        try{
            Professor prof = buscaProf((req.getParameter("buscaCref")));
            if(prof == null){
                req.setAttribute("msgProfNot", "Professor não encontrado");
                new HomeAction().executar(req,resp);
            } else{
                req.setAttribute("profFind", prof);
                new HomeAction().executar(req,resp);
            }
        } catch (ServletException | NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Professor buscaProf(String cref){
        try{
            return new ProfessorDAO().buscarPorCref(cref);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
