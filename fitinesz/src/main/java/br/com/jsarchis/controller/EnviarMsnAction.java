package br.com.jsarchis.controller;

import br.com.jsarchis.model.caixaMsg;
import br.com.jsarchis.model.dao.CaixaMsgDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class EnviarMsnAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        caixaMsg msg = new caixaMsg();
        msg.setNome(req.getParameter("nomeMsg"));
        msg.setTexto(req.getParameter("texto"));
        msg.setData(req.getParameter("dataMsg"));
        new CaixaMsgDAO().inserir(msg);

        new HomeAction().executar(req,resp);
    }
}
