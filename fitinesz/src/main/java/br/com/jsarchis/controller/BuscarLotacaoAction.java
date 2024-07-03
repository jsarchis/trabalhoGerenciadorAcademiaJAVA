package br.com.jsarchis.controller;

import br.com.jsarchis.model.Lotacao;
import br.com.jsarchis.model.dao.LotacaoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

public class BuscarLotacaoAction implements ICommanderAction {
    @Override
    public boolean ehPublico() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoSuchAlgorithmException {
        Integer idLot = Integer.parseInt(req.getParameter("idLotacao"));
        Lotacao lot = new LotacaoDAO().find(idLot);
        int numeroAlunos = lot.getAlunosSalao();

        // Criar um objeto JSON para a resposta
        JSONObject jsonResponse = new JSONObject();
        try {
            jsonResponse.put("numeroDeAlunos", numeroAlunos);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        // Configurar a resposta HTTP para JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
    }
}
