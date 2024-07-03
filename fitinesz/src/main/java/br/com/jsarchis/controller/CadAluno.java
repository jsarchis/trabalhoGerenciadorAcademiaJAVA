package br.com.jsarchis.controller;

import br.com.jsarchis.model.Aluno;
import br.com.jsarchis.model.Ficha;
import br.com.jsarchis.model.dao.AlunoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CadAluno implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        Aluno aluno = new Aluno();
        aluno.setLogin(req.getParameter("loginAluno"));
        aluno.setSenha(req.getParameter("senhaAluno"));
        aluno.setNome(req.getParameter("nomeAluno"));
        aluno.setMatricula(Integer.parseInt(req.getParameter("matricula")));
        Ficha f = new Ficha();
        f.setAluno(aluno);
        aluno.setFichaa(f);
        new AlunoDAO().inserir(aluno);
        new HomeAction().executar(req,resp);
    }
}
