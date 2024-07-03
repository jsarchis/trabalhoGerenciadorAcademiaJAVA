<%@ page import="br.com.jsarchis.model.Usuario" %>
<%@ page import="br.com.jsarchis.model.Lotacao" %>

<%--
  Created by IntelliJ IDEA.
  User: jmsar
  Date: 05/05/2024
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>FitnesZ</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/cssTemplate.css">
</head>
<body>
<% Usuario u = (Usuario)session.getAttribute("user");
  Lotacao lot = (Lotacao) request.getAttribute("lotacaoAtual");
%>
<header class="bg-dark text-white py-3">
  <div class="container d-flex justify-content-between align-items-center">
    <h1 class="h1">FitnesZ <%if(u != null){ %> - Bem vindo(a) <%=u.getNome()%> <% } %></h1>
    <nav class="navbar navbar-expand-lg navbar-dark">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
          <%if(u==null){%>
          <li class="nav-item"><a class="nav-link" href="/master?ac=login">Login</a></li>
          <%} else{ %>
          <li class="nav-item"><a class="nav-link" href="/master">Home</a></li>
          <%}%>
          <%if(u!=null){%>
          <li class="nav-item"><a class="nav-link" href="/master?ac=buscaMensagens">Caixa de Mensagem</a></li>
          <li class="nav-item">
            <a class="nav-link" href="/master?ac=logout">Sair</a>
          </li>
          <% } %>
        </ul>
      </div>
    </nav>

  </div>
  <button type="button" id="buscaLotacao" class="btn btn-secondary">Alunos na FitnesZ</button>
</header>

<main class="container mt-4">
  <div class="row">
    <div class="col-12">
      <h2 class="text-center">Bem Vindo a FitnesZ</h2>
    </div>
  </div>
  <%
    String pg = request.getParameter("pg");
    pg = (pg==null)? "home" : pg ;
    pg = "/pages/"+pg+".jsp";
  %>
  <jsp:include page="<%=pg%>"/>
</main>

<footer class="bg-dark text-white py-3 mt-5">
  <div class="container text-center">
    <p>&copy; 2024 FitnesZ - Sistema de gerenciamento de fichas.</p>
  </div>
</footer>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script>
  $(document).ready(function() {
    $('#buscaLotacao').click(function() {
      $.ajax({
        type: 'POST',
        url: '/master?ac=buscaLotacao',
        data: { idLotacao: 1 },
        dataType: 'json',
        success: function(response) {
          alert(response.numeroDeAlunos + ' alunos agora!');
        },
        error: function(xhr, status, error) {
          alert('Erro ao alterar exercício: ' + error);
        }
      });
    });
  });
</script>
</body>
</html>
