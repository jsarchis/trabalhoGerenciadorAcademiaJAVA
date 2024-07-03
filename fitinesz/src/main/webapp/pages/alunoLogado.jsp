<%@ page import="br.com.jsarchis.model.Usuario" %>
<%@ page import="br.com.jsarchis.model.Aluno" %>
<%@ page import="br.com.jsarchis.model.Ficha" %>
<%@ page import="br.com.jsarchis.model.dao.AlunoDAO" %>
<%@ page import="br.com.jsarchis.model.Serie" %>
<%@ page import="br.com.jsarchis.model.dao.SerieDAO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jmsar
  Date: 01/05/2024
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Área do Aluno</title>
  <link rel="stylesheet" href="../css/csAlunoLogado.css">
</head>
<body class="bg-secondary">
<% Aluno u = (Aluno)session.getAttribute("user");
  Aluno a = new AlunoDAO().buscarPorMatricula(u.getMatricula());
%>
<header class="bg-secondary ">
  <h1 class="text-light">Área do Aluno - <%=u.getNome()%></h1>
</header>

<div class="botao">
  <button class="btn btn-primary btn-dark" onclick="mostrarTreino()">Mostrar Treino</button>
  <button class="btn btn-primary btn-dark" onclick="irMensagens()">Caixa de Mensagens</button>
</div>

<div id="tabelaTreino" style="display: none">
  <% if(a.getFichaa() == null) {
    a.setFichaa((Ficha) request.getAttribute("fichaAtt"));
  } else { %>
  <table class="table table-striped table-bordered">
    <thead class="thead-dark">
    <tr>
      <th>Exercicio</th>
      <th>Series</th>
      <th>Repetições</th>
      <th>Carga</th>
      <th>Treino</th>
    </tr>
    </thead>
    <tbody>
    <% List<Serie> eList = new SerieDAO().acharSeries(a.getFichaa().getIdFicha());
      for (Serie e:eList) { %>
    <tr>
      <td><%=e.getExercicio()%></td>
      <td><%=e.getSeries()%></td>
      <td><%=e.getRepeticoes()%></td>
      <td><%=e.getCarga()%></td>
      <td><%=e.getTreinoABC()%></td>
    </tr>
    <% } %>
    </tbody>
  </table>
  <% } %>
</div>

<div id="escreverMsg" style="display: none;">
  <form action="/master?ac=escreveMsg" method="post" id="escreveMsg" class="bg-secondary border">
    <div class="form-group">
      <label for="nome">Nome (Opcional):</label>
      <input type="text" class="form-control" id="nome" name="nomeMsg">
    </div>
    <div class="form-group">
      <label for="texto">Mensagem:</label>
      <textarea class="form-control" id="texto" name="texto" maxlength="255" rows="5"></textarea>
    </div>
    <div class="form-group">
      <label for="dataMsg">Data:</label>
      <input type="date" class="form-control" id="dataMsg" name="dataMsg">
    </div>
    <button type="submit" class="btn btn-primary">Enviar</button>
  </form>
</div>

<script>
  function mostrarTreino() {
    let obj = document.getElementById("tabelaTreino").style.display;
    document.getElementById("tabelaTreino").style.display = obj === "block" ? "none" : "block";
  }

  function irMensagens() {
    let obj = document.getElementById("escreverMsg").style.display;
    document.getElementById("escreverMsg").style.display = obj === "block" ? "none" : "block";
  }
</script>
</body>
</html>


