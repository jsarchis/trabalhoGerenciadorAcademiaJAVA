package br.com.jsarchis.controller;

import br.com.jsarchis.model.Aluno;
import br.com.jsarchis.model.Ficha;
import br.com.jsarchis.model.dao.AlunoDAO;
import br.com.jsarchis.model.dao.FichaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ExcluirAluno implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        Aluno a = buscaAluno(Integer.parseInt(req.getParameter("id")));
        if(a.getFichaa() != null){
            Ficha f = new FichaDAO().find(a.getFichaa().getIdFicha());
            new FichaDAO().deletar(f);
            a.setFichaa(null);
        }
        new AlunoDAO().deletar(a);
        new HomeAction().executar(req,resp);
    }


    private Aluno buscaAluno(Integer id){
        try{
            return new AlunoDAO().buscarPorId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
