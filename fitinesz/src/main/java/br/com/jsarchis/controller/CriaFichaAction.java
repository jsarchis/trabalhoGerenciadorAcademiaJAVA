package br.com.jsarchis.controller;

import br.com.jsarchis.model.Ficha;
import br.com.jsarchis.model.Serie;
import br.com.jsarchis.model.dao.FichaDAO;
import br.com.jsarchis.model.dao.SerieDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CriaFichaAction implements ICommanderAction{
    @Override
    public boolean ehPublico() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {

        String nomeExercicio = req.getParameter("nExercicioCriaFicha");
        String series = req.getParameter("seriesCriaFicha");
        String repeticoes = req.getParameter("repCriaFicha");
        String carga = req.getParameter("cargaCriaFicha");
        Character treino =  req.getParameter("treinoABCCriaFicha").charAt(0);


        Serie s = new Serie();
        s.setExercicio(nomeExercicio);
        s.setSeries(series);
        s.setRepeticoes(repeticoes);
        s.setCarga(carga);
        s.setTreinoABC(treino);
        Ficha f = new FichaDAO().find(Integer.parseInt(req.getParameter("idFichaCria")));
        f.addSerie(s);
        s.setFicha(f);
        req.setAttribute("fichaAtt", f);
        new FichaDAO().update(f);
    }
}
