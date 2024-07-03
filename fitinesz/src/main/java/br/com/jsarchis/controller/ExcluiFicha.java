package br.com.jsarchis.controller;

import br.com.jsarchis.model.Ficha;
import br.com.jsarchis.model.dao.FichaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ExcluiFicha implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        Ficha f = new FichaDAO().find(Integer.parseInt(req.getParameter("idFicha")));

        new FichaDAO().deletar(f);

        new HomeAction().executar(req,resp);
    }
}
