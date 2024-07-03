package br.com.jsarchis.controller;

import br.com.jsarchis.model.Aluno;
import br.com.jsarchis.model.Professor;
import br.com.jsarchis.model.Usuario;
import br.com.jsarchis.model.dao.AdmDAO;
import br.com.jsarchis.model.dao.AlunoDAO;
import br.com.jsarchis.model.dao.ProfessorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class VerificaLoginAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        try {
            Usuario user = buscarUser(req.getParameter("login"), req.getParameter("senha"));
            if (user == null) {
                req.setAttribute("msg", "Login e/ou Senha incorretos");
                req.setAttribute("ac", "login");
                new ViewAction().executar(req, resp);
            } else {
                req.getSession().setAttribute("user", user);
                new HomeAction().executar(req, resp);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    private Usuario buscarUser(String login, String senha){
        try{
            Aluno aluno = new AlunoDAO().checkLogin(login, senha);
            if(aluno == null){
                Professor prof = new ProfessorDAO().checkLogin(login,senha);
                return (prof == null) ? new AdmDAO().checkLogin(login,senha) : prof;
            }
            return aluno;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
