<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="datatables"
uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="gpa" tagdir="/WEB-INF/tags"%>
<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<link
href="<c:url value="/webjars/datatables/1.9.4/media/css/jquery.dataTables.css" />"
rel="stylesheet" />
<script src="<c:url value="/webjars/datatables/1.9.4/media/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/js/aluno.js" />"></script>
</head>
<body>
<div class="container">
<jsp:include page="../fragments/bodyHeader.jsp" />
<br> <br>
<h2>Alunos</h2>
<div id="mensagens" class="alert" hidden="true">
<button type="button" class="close" data-dismiss="alert" aria-hidden="true"
>&times;</button>
</div>
<!-- Button trigger modal -->
<button id="btnAdicionar" class="btn btn-primary" data-toggle="modal"
data-target="#myModal">Inscrição Alunos</button>
<datatables:table id="QuestionarioAuxilioMoradia" data="${selections}" cdn="false"
row="aluno" theme="bootstrap2" cssClass="table table-striped"
paginate="true" info="false" export="pdf">
<datatables:column title="moraCom" cssStyle="width: 200px;">
<br>Mora com: <br/>
<c:out value="${questionarioAuxilioMoradia.moraCom}" />
<h:selectOneMenu name="estado" id="estado">
<%-- <f:selectItems value="#{pessoas.estado}" /> --%>
</h:selectOneMenu>
</datatables:column>
<datatables:column title="ruaSedeCurso" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.ruaSedeCurso}" />
</datatables:column>
<datatables:column title="numeroSedeCurso" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.numeroSedeCurso}" />
</datatables:column>
<datatables:column title="bairroSedeCurso" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.bairroSedeCurso}" />
</datatables:column>
<datatables:column title="nomeMae" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.nomeMae}" />
</datatables:column>
<datatables:column title="nomePai" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.nomePai}" />
</datatables:column>
<datatables:column title="rua" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.rua}" />
</datatables:column>
<datatables:column title="numeroCasa" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.numeroCasa}" />
</datatables:column>
<datatables:column title="bairro" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.bairro}" />
</datatables:column>
<datatables:column title="complemento" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.complemento}" />
</datatables:column>
<datatables:column title="cidade" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.cidade}" />
</datatables:column>
<datatables:column title="cep" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.cep}" />
</datatables:column>
<datatables:column title="pontoReferencia" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.pontoReferencia}" />
</datatables:column>
<datatables:column title="telefone" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.telefone}" />
</datatables:column>
<datatables:column title="estado" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.estado}" />
</datatables:column>
<datatables:column title="situacaoImovel" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.situacaoImovel}" />
<h:selectOneMenu name="imovel" id="situacaoImovel">
<%-- <f:selectItems value="#{pessoas.situacaoImovel}" /> --%>
</h:selectOneMenu>
</datatables:column>
<datatables:column title="grauParentescoImovelRural" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.grauParentescoImovelRural}" />
<h:selectOneMenu name="imovelRural" id="grauParentescoImovelRural">
<%-- <f:selectItems value="#{pessoas.grauParentescoImovelRural}" /> --%>
</h:selectOneMenu>
</datatables:column>
<datatables:column title="areaPropriedade" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.areaPropriedade}" />
</datatables:column>
<datatables:column title="cidadeEstado" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.cidadeEstado}" />
</datatables:column>
<datatables:column title="veiculo" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.veiculo}" />
</datatables:column>
<datatables:column title="grauParentescoVeiculos" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.grauParentescoVeiculos}" />
<h:selectOneMenu name="veiculo" id="grauParentescoVeiculos">
<%-- <f:selectItems value="#{pessoas.grauParentescoVeiculos}" /> --%>
</h:selectOneMenu/>
</datatables:column>
<datatables:column title="tipo" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.tipo}" />
</datatables:column>
<datatables:column title="marca" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.marca}" />
</datatables:column>
<datatables:column title="modelo" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.modelo}" />
</datatables:column>
<datatables:column title="ano" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.ano}" />
</datatables:column>
<datatables:column title="finalidadeVeiculo" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.finalidadeVeiculo}" />
<h:selectOneMenu name="veiculoFinalidade" id="finalidadeVeiculo">
<%-- <f:selectItems value="#{pessoas.finalidadeVeiculo}" /> --%>
</h:selectOneMenu/>
</datatables:column>	
<datatables:column title="tipoEnsinoFundamental" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.tipoEnsinoFundamental}" />
</datatables:column>
<input type="radio" name="escola" value="publica"> Escola Publica <br />
<input type="radio" name="escola" value="particular"> Escola Particular <br />
<input type="radio" name="escola" value="bolsa"> Com bolsa <textarea rows="1" cols="3">%</textarea> <br />
<datatables:column title="tipoEnsinoMedio" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.tipoEnsinoMedio}" />
</datatables:column>
<input type="radio" name="colegio" value="publica"> Escola Publica <br />
<input type="radio" name="colegio" value="particular"> Escola Particular <br />
<input type="radio" name="colegio" value="bolsa"> Com bolsa <textarea rows="1" cols="3">%</textarea> <br />
<datatables:column title="cursinho" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.cursinho}" />
</datatables:column>
<datatables:column title="nomeCursinho" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.nomeCursinho}" />
</datatables:column>
<input type="radio" name="cursinho" value="nao"> Não <br />
<input type="radio" name="cursinho" value="sim"> Sim, qual <textarea rows="1" cols="15"></textarea> <br /><br />
<datatables:column title="rendaMediaFamilia" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.rendaMediaFamilia}" />
</datatables:column>
<datatables:column title="rendaMediaPessoa" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.rendaMediaPessoa}" />
</datatables:column>
<datatables:column title="bolsista" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.bolsista}" />
</datatables:column>
<datatables:column title="tipoBolsa" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.tipoBolsa}" />
</datatables:column>
<datatables:column title="possuiGraduacao" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.possuiGraduacao}" />
</datatables:column>
<datatables:column title="descricaoGraduacao" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.descricaoGraduacao}" />
</datatables:column>
<datatables:column title="justificativa" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.justificativa}" />
</datatables:column>
<datatables:column title="dataInscricao" cssStyle="width: 200px;">
<c:out value="${questionarioAuxilioMoradia.pessoas.dataInscricao}" />
</datatables:column>
<datatables:column title="Editar" display="html" property="editar">
<button id="btnEditar" class="btn btn-default btn-lg editarAluno"
data-toggle="modal" data-target="#myModal"
onclick="povoaForm('<c:url value="/inscricao/${iniciacao.id}" />', '#add-aluno-form', this);">
<span class="glyphicon glyphicon-edit"></span>
</button>
</datatables:column>
<datatables:column title="Excluir" display="html" property="excluir">
<button id="btnExcluir" class="btn btn-default btn-lg"
onclick="excluir('#alunos','<c:url value="/inscricao/${iniciacao.id}" />', this);">
<span class="glyphicon glyphicon-trash"></span>
</button>
</datatables:column>
</datatables:table>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal"
aria-hidden="true">&times;</button>
<h4 class="modal-title" id="myModalLabel">Adicionar contato</h4>
</div>
<div class="modal-body">
<form class="form-horizontal" id="add-contato-form">
<input type="hidden" name="id" id="id" />
<div class="form-group">
<label class="col-sm-2 control-label" for="nome">Nome</label>
<div class="col-sm-10">
<input type="text" class="form-control" placeholder="Nome"
name="nome" id="nome" />
</div>
</div>
<div class="form-group">
<label class="col-sm-2 control-label" for="sobreNome">SobreNome</label>
<div class="col-sm-10">
<input type="text" class="form-control"
placeholder="Sobre Nome" name="sobreNome" id="sobreNome" />
</div>
</div>
<div class="form-group">
<label class="col-sm-2 control-label" for="email">Email</label>
<div class="col-sm-10">
<input type="email" class="form-control" name="email"
id="email" placeholder="Email">
</div>
</div>
<div class="form-group">
<label class="col-sm-2 control-label" for="endereco">Endereço</label>
<div class="col-sm-10">
<input type="text" class="form-control" placeholder="Endereço"
name="endereco" id="endereco" />
</div>
</div>
<div class="form-group">
<label class="col-sm-2 control-label" for="cidade">Cidade</label>
<div class="col-sm-10">
<input type="text" class="form-control" placeholder="Cidade"
name="cidade" id="cidade" />
</div>
</div>
<div class="form-group">
<label class="col-sm-2 control-label" for="fone">Telefone</label>
<div class="col-sm-10">
<input type="text" class="form-control" placeholder="Telefone"
name="fone" id="fone" />
</div>
</div>
</form>
</div>
<div class="modal-footer">
<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
<button id="btnSubmitForm" type="button" class="btn btn-primary" onclick="submeterForm();">Salvar</button>
</div>
</div>
</div>
</div>
<jsp:include page="../fragments/footer.jsp" />
</div>
</body>
</html>
