package br.com.jsarchis.controller;

import br.com.jsarchis.model.Aluno;
import br.com.jsarchis.model.dao.AlunoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AlteraAlunoAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        Aluno a = new Aluno(Integer.parseInt(req.getParameter("altIdAluno")),req.getParameter("altNomeAluno"),req.getParameter("altLoginAluno"),req.getParameter("altSenhaAluno"),Integer.parseInt(req.getParameter("altMatriculaAluno")));
        new AlunoDAO().update(a);
        new HomeAction().executar(req,resp);
    }
}
