package br.com.jsarchis.controller;

import br.com.jsarchis.model.caixaMsg;
import br.com.jsarchis.model.dao.CaixaMsgDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ExcluirMsgAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        int idMsg = Integer.parseInt(req.getParameter("idMsg"));
        caixaMsg cxmsg = new CaixaMsgDAO().find(idMsg);
        req.setAttribute("msgMSGExcluida", "Mensagem Excluida");
        new CaixaMsgDAO().deletar(cxmsg);
        new HomeAction().executar(req,resp);

    }
}
