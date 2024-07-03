package br.com.jsarchis.controller;

import br.com.jsarchis.model.Serie;
import br.com.jsarchis.model.dao.SerieDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ExcluirSerie implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        Serie e = new SerieDAO().find(Integer.parseInt(req.getParameter("idSerie")));

        new SerieDAO().deletar(e);


        new HomeAction().executar(req,resp);
    }
}
