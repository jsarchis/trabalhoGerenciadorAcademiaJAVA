package br.com.jsarchis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@WebServlet("/master")
public class MasterController extends HttpServlet{

    static HashMap<String, ICommanderAction> comandos = new HashMap();

    static{
        comandos.put(null, new HomeAction());
        comandos.put("master", new HomeAction());
        comandos.put("verify", new VerificaLoginAction());
        comandos.put("login", new ViewAction());
        comandos.put("logout", new LogoutAction());
        comandos.put("cadAluno", new CadAluno());
        comandos.put("cadProf", new CadProfessor());
        comandos.put("buscaAluno", new BuscaAlunoAction());
//        comandos.put("cadExercicio", new CadExercicio());
        comandos.put("excluiAluno", new ExcluirAluno());
        comandos.put("buscaProfessor", new BuscaProfAction());
        comandos.put("buscaMensagens", new BuscaMensagemAction());
        comandos.put("escreveMsg", new EnviarMsnAction());
        comandos.put("excluiMsg", new ExcluirMsgAction());
        comandos.put("excluiProf", new ExcluirProfessorAction());
        comandos.put("alteraAluno", new AlteraAlunoAction());
        comandos.put("altearProf", new AlteraProfAction());
        comandos.put("attLotacao", new AlterarLotacaoAction());
        comandos.put("criaFicha", new CriaFichaAction());
        comandos.put("excluiSerie", new ExcluirSerie());
        comandos.put("excluiFichaToda", new ExcluiFicha());
        comandos.put("alteraSerie", new AlterarSerieAction());
        comandos.put("buscaLotacao", new BuscarLotacaoAction());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reposta(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reposta(req,resp);
    }

    private void reposta(HttpServletRequest req, HttpServletResponse resp) {
        String ac = req.getParameter("ac");
        String id = req.getParameter("id");
        try{
            if(comandos.get(ac) == null){
                req.setAttribute("ac", "ERRO");
                req.setAttribute("msg", "Erro ao executar tarefa ou falta de permissão");
            }else {
                req.setAttribute("id", id);
                comandos.get(ac).executar(req, resp);
            }
        } catch(ServletException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        } catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}
