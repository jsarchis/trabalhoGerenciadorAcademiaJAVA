<%@ page import="br.com.jsarchis.model.dao.FichaDAO" %>
<%@ page import="br.com.jsarchis.model.dao.AlunoDAO" %>
<%@ page import="br.com.jsarchis.model.*" %>
<%@ page import="br.com.jsarchis.model.dao.LotacaoDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.jsarchis.model.dao.SerieDAO" %><%--
  Created by IntelliJ IDEA.
  User: jmsar
  Date: 01/05/2024
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Área do Professor</title>
    <link rel="stylesheet" href="../css/csAlunoLogado.css.css.css">
</head>
<body class="bg-secondary">
<% Usuario u = (Professor)session.getAttribute("user");
    Aluno a = (Aluno)request.getAttribute("alunoFind");
    Lotacao lot = new LotacaoDAO().find(1);
%><% Serie s = new Serie(); %>
<header class="bg-dark text-white py-1">
    <div class="container">
        <h1 class="text-light">Área do Professor - <%=u.getNome()%> </h1>
    </div>
</header>
<main class="container mt-4">
    <div class="row">
        <div class="col-12 text-center">
            <div class="btn-group mb-3" role="group" aria-label="Admin Actions">
                <button class="btn btn-primary btn-dark border-info" onclick="buscarAluno()">Buscar Aluno</button>
                <button class="btn btn-primary btn-dark border-info" onclick="irMensagens()">Caixa de Mensagens</button>
                <button class="btn btn-primary btn-dark border-info" onclick="attLotacao()">Atualizar Lotação</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="formulario mb-3">
                <form action="/master?ac=attLotacao" method="post" style="display: none" id="attLotacao" class="bg-secondary border">
                    <div class="form-group">
                        <label class="text-center text-warning"><h3 class="text-center">Atualiza Lotação</h3></label>
                    </div>
                    <div class="form-group">
                    <label for="lotAtual">FitnesZers Now:</label>
                    </div>
                    <div class="form-group">
                    <input class="form-control" type="number" id="lotAtual" name="lotAtual" value="<%=lot.getAlunosSalao()%>"><br>
                        <input type="submit" value="Atualizar" class="btn-dark">
                    </div>
                </form>
                <div class="formulario mb-3">
                    <form action="/master?ac=buscaAluno" method="post" style="display: none;" id="buscaAluno" class="bg-secondary border">
                        <div class="form-group">
                        <label>Buscar Aluno</label>
                        </div>
                        <div class="form-group">
                        <label for="cpf">Matricula:</label>
                        <input type="number" id="cpf" name="buscaMatricula" class="form-control"><br>
                        </div>
                        <input type="submit" value="Pesquisar" class="btn-info">
                    </form>
                </div>
                <div id="escreverMsg" class="formulario m-auto">
                    <form action="/master?ac=escreveMsg" method="post" style="display: none;" id="escreveMsg" class="bg-secondary border">
                        <div class="form-group">
                        <label>Escrever Mensagem</label><br>
                        </div>
                        <div class="form-group">
                        <label for="nome">Nome(Opcional):</label><br>
                        <input type="text" id="nome" name="nomeMsg" class="form-control"><br><br>
                        </div>
                        <div class="form-group">
                        <label for="texto">Mensagem:</label><br>
                        <textarea type="text" id="texto" name="texto" maxlength="255" rows="5" cols="50" class="form-text">
                        </textarea><br><br>
                        </div>
                        <div class="form-group">
                        <label for="dataMsg">Data:</label><br>
                        <input type="date" id="dataMsg" name="dataMsg" class="form-control" ><br><br>
                        </div>
                        <input type="submit" value="Enviar" class="btn btn-primary">
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

<div id="alunoBuscado" class="formulario m-auto">
    <%
        if(a != null){
    %>

    <table class="table table-striped table-bordered">
        <thead class="thead-dark border-white">
        <tr>
            <th>Nome</th>
            <th>Matricula</th>
            <th>Acções</th>
        </tr>
        </thead>
        <tbody>
        <tr>
        <td><%=a.getNome()%></td>
        <td><%=a.getMatricula()%></td>
        <td>
            <button class="btn-primary" onclick="buscaFicha()">Ver Ficha</button>
            <button class="btn-primary" onclick="novaFicha(<%= (a.getFichaa() == null) ? '0' : a.getFichaa().getIdFicha()%>)">Nova Ficha</button>
            <button class="btn-danger" onclick="excluiFicha(<%= (a.getFichaa() == null) ? '0' : a.getFichaa().getIdFicha()%>)">Excluir Ficha</button>
            <button class="btn-danger" onclick="cancelaBusca()">X</button>
        </td>
        </tr>
        </tbody>
    </table>

</div>
<div id="buscaFicha" style="display: block">
<%if(a.getFichaa() == null) {
    Ficha f = new Ficha();
    f.setAluno(a);
    a.setFichaa(f);
    new AlunoDAO().update(a);
} else{
    List<Serie> series = new SerieDAO().acharSeries(a.getFichaa().getIdFicha());
%>
        <table class="table table-striped table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>
            Exercicio
            </th>
                <th>
            Series
            </th>
                <th>
            Repetições
            </th>
                <th>
            Carga
            </th>
                <th>
            Treino
            </th>
                <th>
                    Ações
                </th>
            </tr>
        </thead>
<%
    for (Serie e:series){
%>
        <tbody>
            <tr>
                <td>
                    <%=e.getExercicio()%>
                </td>
                <td>
                    <%=e.getSeries()%>
                </td>
                <td>
                    <%=e.getRepeticoes()%>
                </td>
                <td>
                    <%=e.getCarga()%>
                </td>
                <td>
                    <%=e.getTreinoABC()%>
                </td>
                <td>
                    <button class="btn-info" type="button" id="alteraSerie" onclick="openEditModal({
                            idSerie: '<%= e.getIdSerie() %>',
                            exercicio: '<%= e.getExercicio() %>',
                            series: '<%= e.getSeries() %>',
                            repeticoes: '<%= e.getRepeticoes() %>',
                            carga: '<%= e.getCarga() %>',
                            treinoABC: '<%= e.getTreinoABC() %>'
                            })">Altera Serie</button>
                    <button class="btn-danger" type="button" id="deletaSerie" onclick="deleteSerie(<%=e.getIdSerie()%>)">Excluir Série</button>
                </td>
            </tr>
        </tbody>
        <div id="editModal" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%, -50%); padding:20px; background-color:white; border:1px solid #ccc; z-index:1000;" class="modal-dialog-centered bg-secondary">
            <h2>Alterar Série</h2>
            <form id="alterarFicha" class="formulario mb-3 bg-secondary">
                <div class="form-group">
                <label for="exercicioAlt">Exercicio:</label>
                <input type="text" id="exercicioAlt" name="exercicioAlt" class="form-control">
                </div>
                <div class="form-group">
                <label for="seriesAlt">Series:</label>
                <input type="text" id="seriesAlt" name="seriesAlt" class="form-control">
                </div>
                <div class="form-group">
                <label for="repeticoesAlt">Repetições:</label>
                <input type="text" id="repeticoesAlt" name="repeticoesAlt" class="form-control">
                </div>
                <div class="form-group">
                <label for="cargaAlt">Carga:</label>
                <input type="text" id="cargaAlt" name="cargaAlt" class="form-control">
                </div>
                <div class="form-group">
                <label for="treinoABCAlt">Treino:</label>
                <input type="text" id="treinoABCAlt" name="treinoABCAlt" class="form-control">
                </div>
                <input type="hidden" id="serieIdAlt" name="serieIdAlt">
                <div class="form-group">
                <button type="button" id="salvaAlteracaoSerie" class="btn-warning">Salvar</button>
                <button type="button" onclick="closeModal()" class="btn-danger">Fechar</button>
                </div>
            </form>
        </div>
<% } %>
        </table>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<form id="criaFicha" class="formulario mb-3">
    <table id="tabelaCadastroSerie" class="table table-sub-heading-color border-dark">
        <thead class="border">
        <tr>
            <th class="border"><label for="nExercicioCriaFicha" >Exercicio</label></th>
            <th class="border"><label for="seriesCriaFicha">Series</label></th>
            <th class="border"><label for="repCriaFicha">Repetições</label></th>
            <th class="border"><label for="cargaCriaFicha"> Carga </label></th>
            <th class="border"><label for="treinoABCCriaFicha"> Treino</label></th>
            <th class="border">Ações</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="border"><input type="text" id="nExercicioCriaFicha" name="nExercicioCriaFicha" class="form-text"></td>
            <td class="border"><input type="text" id="seriesCriaFicha" name="seriesCriaFicha" class="form-text"></td>
            <td class="border"><input type="text" id="repCriaFicha" name="repCriaFicha" class="form-text"></td>
            <td class="border"><input type="text" id="cargaCriaFicha" name="cargaCriaFicha" class="form-text"></td>
            <td class="border"><input type="text" id="treinoABCCriaFicha" name="treinoABCCriaFicha" class="form-text"></td>
            <td class="border"><button class="btn-success" type="button" id="adicionarExercicio">Add Serie</button></td>
        </tr>
        </tbody>
    </table>
    <script>
        $(document).ready(function() {
            $('#adicionarExercicio').click(function() {

                var formData = $('#criaFicha').serialize();

                $.ajax({
                    type: 'POST',
                    url: '/master?ac=criaFicha&idFichaCria=<%=a.getFichaa().getIdFicha()%>',
                    data: formData,
                    success: function(response) {
                        $('#nExercicioCriaFicha').val('');
                        $('#seriesCriaFicha').val('');
                        $('#repCriaFicha').val('');
                        $('#cargaCriaFicha').val('');
                        $('#treinoABCCriaFicha').val('');

                        alert('Exercício adicionado com sucesso!');
                        window.location.reload();
                    },
                    error: function(xhr, status, error) {
                        alert('Erro ao adicionar exercício: ' + error);
                    }
                });
            });
        });
    </script>
</form>

<% } %>
<% } %>
</div>


<script>
    $(document).ready(function() {
        $('#salvaAlteracaoSerie').click(function() {

            var formData = $('#alterarFicha').serialize();

            $.ajax({
                type: 'POST',
                url: '/master?ac=alteraSerie',
                data: formData,
                success: function(response) {
                    alert('Exercício alterado com sucesso!');
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    console.log(formData);
                    alert('Erro ao alterar exercício: ' + error);
                }
            });
        });
    });

    function openEditModal(serie) {
        document.getElementById('editModal').style.display = 'block';
        document.getElementById('exercicioAlt').value = serie.exercicio;
        document.getElementById('seriesAlt').value = serie.series;
        document.getElementById('repeticoesAlt').value = serie.repeticoes;
        document.getElementById('cargaAlt').value = serie.carga;
        document.getElementById('treinoABCAlt').value = serie.treinoABC;
        document.getElementById('serieIdAlt').value = serie.idSerie;
    }



    function novaFicha(idFicha){
        if (confirm('Criar nova ficha?')) {
            $.ajax({
                type: 'POST',
                url: '/master?ac=excluiFichaToda',
                data: { idFicha: idFicha },
                success: function(response) {
                    alert('Ficha nova iniciada!');
                    location.reload();
                },
                error: function(xhr, status, error) {
                    alert('Erro ao criar nova ficha: ' + error);
                }
            });
        }
    }

    function excluiFicha(idFicha){
        if (confirm('Tem certeza de que deseja excluir a ficha?')) {
            $.ajax({
                type: 'POST',
                url: '/master?ac=excluiFichaToda',
                data: { idFicha: idFicha },
                success: function(response) {
                    alert('Ficha excluída com sucesso!');
                    location.reload();
                },
                error: function(xhr, status, error) {
                    alert('Erro ao excluir ficha: ' + error);
                }
            });
        }
    }

    function deleteSerie(idSerie) {
        if (confirm('Tem certeza de que deseja excluir esta série?')) {
            $.ajax({
                type: 'POST',
                url: '/master?ac=excluiSerie',
                data: { idSerie: idSerie },
                success: function(response) {
                    alert('Série excluída com sucesso!');
                    location.reload();
                },
                error: function(xhr, status, error) {
                    alert('Erro ao excluir série: ' + error);
                }
            });
        }
    }

    function attLotacao(){
        let obj = document.getElementById("attLotacao").style.display;
        if(obj == "block"){
            document.getElementById("attLotacao").style.display = "none";
        } else {
            document.getElementById("attLotacao").style.display = "block";
        }
    }

    function chamarAction(url){
        window.location.href = url;
    }

    function cancelaBusca(){
        <% a = null; %>
        obj = document.getElementById("alunoBuscado").style.display = "none";
        document.getElementById("buscaFicha").style.display = "none";
        document.getElementById("criaFicha").style.display = "none";
    }

    function buscarAluno() {
        let obj = document.getElementById("buscaAluno").style.display;
        if(obj == "block"){
            document.getElementById("buscaAluno").style.display = "none";
        } else {
            document.getElementById("buscaAluno").style.display = "block";
        }
    }

    function criaFicha() {
        let obj = document.getElementById("criaFicha").style.display;
        if(obj == "block"){
            document.getElementById("criaFicha").style.display = "none";
        } else {
            document.getElementById("criaFicha").style.display = "block";
        }
    }

    function buscaFicha(){
        window.location.reload();
        let obj = document.getElementById("buscaFicha").style.display;
        if(obj == "block"){
            document.getElementById("buscaFicha").style.display = "none";
        } else {
            document.getElementById("buscaFicha").style.display = "block";
        }
    }

    function irMensagens() {
        let obj = document.getElementById("escreveMsg").style.display;
        if(obj == "block"){
            document.getElementById("escreveMsg").style.display = "none";
        } else {
            document.getElementById("escreveMsg").style.display = "block";
        }
    }
</script>
</body>
</html>