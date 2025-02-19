package br.com.jsarchis.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ViewAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        String ac = (String) req.getAttribute("ac");
        ac = (ac == null) ? req.getParameter("ac") : ac;
        RequestDispatcher rd = req.getRequestDispatcher("template.jsp?pg="+ac);
        rd.forward(req,resp);
    }
}
