package br.com.jsarchis.controller;

import br.com.jsarchis.model.Serie;
import br.com.jsarchis.model.dao.SerieDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AlterarSerieAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        String nomeExercicio = req.getParameter("exercicioAlt");
        String series = req.getParameter("seriesAlt");
        String repeticoes = req.getParameter("repeticoesAlt");
        String carga = req.getParameter("cargaAlt");
        Character treino =  req.getParameter("treinoABCAlt").charAt(0);
        Integer idSerie = Integer.parseInt(req.getParameter("serieIdAlt"));
        Serie e = new SerieDAO().find(idSerie);
        e.setExercicio(nomeExercicio);
        e.setSeries(series);
        e.setCarga(carga);
        e.setRepeticoes(repeticoes);
        e.setTreinoABC(treino);

        new SerieDAO().update(e);
        new HomeAction().executar(req,resp);
    }
}
