package br.com.jsarchis.controller;

import br.com.jsarchis.model.caixaMsg;
import br.com.jsarchis.model.dao.CaixaMsgDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class BuscaMensagemAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {

        try{
            List<caixaMsg> mensagens = new CaixaMsgDAO().findAll();
            req.setAttribute("mensagensAchadas", mensagens);
            new HomeAction().executar(req,resp);
        } catch (ServletException | NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
