package br.com.jsarchis.controller;

import br.com.jsarchis.model.Aluno;
import br.com.jsarchis.model.Professor;
import br.com.jsarchis.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class HomeAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        Usuario user = (Usuario) req.getSession().getAttribute("user");
        RequestDispatcher rd;
        if(user == null){
            rd = req.getRequestDispatcher("template.jsp?pg=home");
        } else if (user instanceof Aluno){
            rd = req.getRequestDispatcher("template.jsp?pg=alunoLogado");
        } else if (user instanceof Professor) {
            rd = req.getRequestDispatcher("template.jsp?pg=profLogado");
        } else {
            rd = req.getRequestDispatcher("template.jsp?pg=admLogado");
        }

        rd.forward(req, resp);
    }
}
