package br.com.jsarchis.controller;

import br.com.jsarchis.model.Aluno;
import br.com.jsarchis.model.dao.AlunoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class BuscaAlunoAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        try{
            Aluno aluno = new AlunoDAO().buscarPorMatricula(Integer.parseInt(req.getParameter("buscaMatricula")));
            if(aluno == null){
                req.setAttribute("msgAlunoNot", "Aluno não encontrado");
                new HomeAction().executar(req,resp);
            } else{
                req.setAttribute("alunoFind", aluno);
                new HomeAction().executar(req,resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private Aluno buscaAluno(Integer matricula){
        try{
            return new AlunoDAO().buscarPorMatricula(matricula);
        } catch (Exception e) {
            return null;
        }
    }
}
