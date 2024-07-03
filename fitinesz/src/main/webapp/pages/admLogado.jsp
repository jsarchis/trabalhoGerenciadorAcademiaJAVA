
<%@ page import="java.util.List" %>
<%@ page import="br.com.jsarchis.model.*" %><%--
  Created by IntelliJ IDEA.
  User: jmsar
  Date: 01/05/2024
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Área do Administrador</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/csAdmLogado.css">
</head>
<body class="toast-body bg-secondary" >
<% Usuario u = (Adm)session.getAttribute("user");
    Aluno a = (Aluno)request.getAttribute("alunoFind");
    Professor p = (Professor) request.getAttribute("profFind");
    List<caixaMsg> msgs = (List<caixaMsg>)request.getAttribute("mensagensAchadas");
%>

<header class="bg-dark text-white py-3">
    <div class="container">
        <h1 class="h3 text-light">Área do Administrador - <%=u.getNome()%></h1>
    </div>
</header>

<main class="container mt-4">
    <div class="row">
        <div class="col-12 text-center">
            <div class="btn-group mb-3" role="group" aria-label="Admin Actions">
                <button type="button" class="btn btn-primary btn-dark" onclick="newAluno()">Cadastrar Aluno</button>
                <button type="button" class="btn btn-primary btn-dark" onclick="buscaAluno()">Buscar Aluno</button>
                <button type="button" class="btn btn-primary btn-dark" onclick="newProfessor()">Cadastrar Professor</button>
                <button type="button" class="btn btn-primary btn-dark" onclick="buscaProfessor()">Buscar Professor</button>
                <button type="button" class="btn btn-primary btn-dark" onclick="irMensagens('/master?ac=buscaMensagens')">Ler Mensagens</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <h1><%=(request.getAttribute("msgAlunoNot") == null)?"":(String)request.getAttribute("msgAlunoNot")%></h1>
            <h1><%=(request.getAttribute("msgProfNot") == null)?"":(String)request.getAttribute("msgProfNot")%></h1>
            <h1><%=(request.getAttribute("msgMSGExcluida") == null)?"":(String)request.getAttribute("msgMSGExcluida")%></h1>
            <h1><%=(request.getAttribute("msgProfDel") == null)?"":(String)request.getAttribute("msgProfDel")%></h1>
            <h1><%=(request.getAttribute("msgProfAlterado") == null)?"":(String)request.getAttribute("msgProfAlterado")%></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="formulario mb-3">
                <form action="/master?ac=cadAluno" method="post" style="display: none;" id="newAluno" class="bg-secondary border">
                    <div class="form-group">
                        <label for="nomeAluno">Nome:</label>
                        <input type="text" class="form-control" id="nomeAluno" name="nomeAluno" required>
                    </div>
                    <div class="form-group">
                        <label for="loginAluno">Login:</label>
                        <input type="text" class="form-control" id="loginAluno" name="loginAluno" required>
                    </div>
                    <div class="form-group">
                        <label for="senhaAluno">Senha:</label>
                        <input type="password" class="form-control" id="senhaAluno" name="senhaAluno" required>
                    </div>
                    <div class="form-group">
                        <label for="cpf">Matricula:</label>
                        <input type="text" class="form-control" id="cpf" name="matricula" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Cadastrar</button>
                </form>
            </div>
            <div class="formulario mb-3">
                <form action="/master?ac=buscaAluno" method="post" style="display: none;" id="buscaAluno" class="bg-secondary border">
                    <div class="form-group">
                        <label for="matricula">Buscar Aluno</label>
                        <input type="number" class="form-control" id="matricula" name="buscaMatricula">
                    </div>
                    <button type="submit" class="btn btn-primary">Pesquisar</button>
                </form>
            </div>
            <div id="alunoBuscado" class="mb-3">
                <% if(a != null) { %>
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                    <tr>
                        <th>Nome</th>
                        <th>Matricula</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody class="bg-secondary border">
                    <tr>
                        <td><%=a.getNome()%></td>
                        <td><%=a.getMatricula()%></td>
                        <td>
                            <button class="btn btn-info btn-sm" onclick="alteraAluno()">Alterar</button>
                            <button class="btn btn-danger btn-sm" onclick="chamarAction('/master?ac=excluiAluno&id=<%= a.getId()%>')">Excluir</button>
                            <button class="btn btn-danger btn-sm" style="width: 30px" onclick="cancelaBuscaaluno()">X</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <form action="/master?ac=alteraAluno" id="altAluno" style="display: none" method="post" class="bg-secondary border">
                    <table class="table table-striped table-bordered">
                        <thead class="thead-dark">
                        <tr>
                            <th>Nome</th>
                            <th>Login</th>
                            <th>Senha</th>
                            <th>Matricula</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <input type="number" name="altIdAluno" style="display: none" value="<%=a.getId()%>">
                            <td><input type="text" class="form-control" name="altNomeAluno" value="<%=a.getNome()%>"></td>
                            <td><input type="text" class="form-control" name="altLoginAluno" value="<%=a.getLogin()%>"></td>
                            <td><input type="password" class="form-control" name="altSenhaAluno" value="<%=a.getSenha()%>"></td>
                            <td><input type="number" class="form-control" name="altMatriculaAluno" value="<%=a.getMatricula()%>"></td>
                        </tr>
                        </tbody>
                    </table>
                    <button type="submit" class="btn btn-primary">Alterar</button>
                </form>
                <% } %>
            </div>
            <div class="formulario mb-3">
                <form action="/master?ac=buscaProfessor" method="post" style="display: none;" id="buscaProfessor" class="bg-secondary border">
                    <div class="form-group">
                        <label for="cref">Buscar Professor</label>
                        <input type="number" class="form-control" id="cref" name="buscaCref">
                    </div>
                    <button type="submit" class="btn btn-primary">Pesquisar</button>
                </form>
            </div>
            <div id="profBuscado" class="mb-3">
                <% if(p != null) { %>
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                    <tr>
                        <th>Nome</th>
                        <th>CREF</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><%=p.getNome()%></td>
                        <td><%=p.getCref()%></td>
                        <td>
                            <button class="btn btn-info btn-sm" onclick="alterarProf()">Alterar</button>
                            <button class="btn btn-danger btn-sm" onclick="chamarAction('/master?ac=excluiProf&idProfDel='+<%=p.getId()%>)">Excluir</button>
                            <button class="btn btn-danger btn-sm" onclick="cancelaBusca()">X</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <form action="/master?ac=altearProf" method="post" style="display: none" id="altprof">
                    <table class="table table-striped table-bordered">
                        <thead class="thead-dark">
                        <tr>
                            <th>Nome</th>
                            <th>Login</th>
                            <th>Senha</th>
                            <th>CREF</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <input type="number" name="altIdProf" value="<%=p.getId()%>" style="display: none">
                            <td><input type="text" class="form-control" name="altNomeProf" value="<%=p.getNome()%>"></td>
                            <td><input type="text" class="form-control" name="altLoginProf" value="<%=p.getLogin()%>"></td>
                            <td><input type="password" class="form-control" name="altSenhaProf" value="<%=p.getSenha()%>"></td>
                            <td><input type="text" class="form-control" name="altCREFProf" value="<%=p.getCref()%>"></td>
                        </tr>
                        </tbody>
                    </table>
                    <button type="submit" class="btn btn-primary">Alterar</button>
                </form>
                <% } %>
            </div>
            <div class="formulario mb-3">
                <form action="/master?ac=cadProf" method="post" style="display: none;" id="newProfessor" class="bg-secondary border">
                    <div class="form-group">
                        <label for="nomeProf">Nome:</label>
                        <input type="text" class="form-control" id="nomeProf" name="nomeProf" required>
                    </div>
                    <div class="form-group">
                        <label for="crefProf">CREF:</label>
                        <input type="text" class="form-control" id="crefProf" name="crefProf" required>
                    </div>
                    <div class="form-group">
                        <label for="loginProf">Login:</label>
                        <input type="text" class="form-control" id="loginProf" name="loginProf" required>
                    </div>
                    <div class="form-group">
                        <label for="senhaProf">Senha:</label>
                        <input type="password" class="form-control" id="senhaProf" name="senhaProf" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Cadastrar</button>
                </form>
            </div>
            <% if (msgs != null) { %>
            <div class="tabela mb-3">
                <table id="tabelaMsg" class="table table-striped table-bordered">
                    <thead class="thead-dark">
                    <tr>
                        <th>Nome</th>
                        <th>Mensagem</th>
                        <th>Data</th>
                        <th>Ação</th>
                    </tr>
                    </thead>
                    <tbody class="bg-secondary border">
                    <% for (caixaMsg msg : msgs) { %>
                    <tr>
                        <td><%= msg.getNome() %></td>
                        <td><%= msg.getTexto() %></td>
                        <td><%= msg.getData() %></td>
                        <td><button class="btn btn-danger btn-sm" onclick="chamarAction('/master?ac=excluiMsg&idMsg='+<%= msg.getIdCaixaMsg() %>)">Excluir</button></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
            <% } %>
        </div>
    </div>
</main>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function alterarProf() {
        esconder("altprof");
    }

    function alteraAluno() {
        esconder("altAluno");
    }

    function chamarAction(url) {
        window.location.href = url;
    }

    function cancelaBuscaaluno() {
        document.getElementById("alunoBuscado").style.display = "none";
    }

    function cancelaBusca() {
        document.getElementById("profBuscado").style.display = "none";
    }

    function newAluno() {
        esconder("newAluno");
    }

    function buscaAluno() {
        esconder("buscaAluno");
    }

    function newProfessor() {
        esconder("newProfessor");
    }

    function buscaProfessor() {
        esconder("buscaProfessor");
    }

    function irMensagens(url) {
        window.location.href = url;
    }

    function esconder(id) {
        var element = document.getElementById(id);
        if (element.style.display === "none" || element.style.display === "") {
            element.style.display = "block";
        } else {
            element.style.display = "none";
        }
    }
</script>
</body>
</html>
