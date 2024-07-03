package br.com.jsarchis.controller;

import br.com.jsarchis.model.Lotacao;
import br.com.jsarchis.model.dao.LotacaoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AlterarLotacaoAction implements ICommanderAction {
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        Lotacao lot = new Lotacao();
        lot.setId(1);
        lot.setAlunosSalao(Integer.parseInt(req.getParameter("lotAtual")));
        new LotacaoDAO().update(lot);
        new HomeAction().executar(req,resp);
    }
}
