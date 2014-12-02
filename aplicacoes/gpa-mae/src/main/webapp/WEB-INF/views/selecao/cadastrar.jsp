<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<jsp:include page="../fragments/bodyHeader.jsp" />
	<title>Cadastro de seleções</title>
</head>
<body>

	<jsp:include page="../fragments/headTag.jsp" />
	<ol class="breadcrumb">
      <li><a href="/MAE/selecao/listar">Listar Seleções</a></li>
      <li class="active">Cadastrar Seleção</li>
    </ol>
	 <div class="container">
		<div class="novo-selecao" align="left">
			<div class="form" align="center">
				<h2>Nova Seleção</h2>
				<form:form id="adicionarSelecaoForm" role="form" commandName="selecao" servletRelativeAction="/selecao/cadastrar" method="POST" cssClass="form-horizontal">

					<div class="form-group">
						<label for="comentarios" class="col-sm-2 control-label">Comentarios:</label>
						<div class="col-sm-10">
							<form:textarea id="comentarios" path="comentarios" class="form-control" rows="5"  ></form:textarea>
							<div class="error-validation">
								<form:errors path="comentarios"></form:errors>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="dataInicio" class="col-sm-2 control-label">Data de Início:</label>
						<div class="col-sm-2">
							<form:input id="dataInicio" type="text" path="dataInicio" cssClass="form-control data" placeholder="Data de Início"/>
							<div class="error-validation">
								<form:errors path="dataInicio"></form:errors>
							</div>
						</div>
						
						<label for="dataTermino" class="col-sm-2 control-label">Data de Término:</label>
						<div class="col-sm-2">
							<form:input id="dataTermino" type="text" path="dataTermino" cssClass="form-control data" placeholder="Data de Término"/>
							<div class="error-validation">
								<form:errors path="dataTermino"></form:errors>
							</div>
						</div>
						
						<label for="sequencial" class="col-sm-2 control-label">Número do Edital:</label>
						<div class="col-sm-2">
							<form:input id="sequencial" type="number" path="sequencial" cssClass="form-control" placeholder="000"/>
							<div class="error-validation">
								<form:errors path="sequencial"></form:errors>
							</div>
							<div class="error-validation">
								${editalError}
							</div>
						</div>
						
						<label for="duracao" class="col-sm-2 control-label">Duração:</label>
						<div class="col-sm-2">
							<form:input id="duracao" type="text" path="duracao" cssClass="form-control" placeholder="0"/>
							<div class="error-validation">
								<form:errors path="duracao"></form:errors>
							</div>
						</div>
						<label for="ano" class="col-sm-2 control-label">Ano:</label>
						<div class="col-sm-2">
							<form:input id="ano" type="text" path="ano" cssClass="form-control	" placeholder="0" onkeypress="mascara(this,soNumeros)"/>
							<div class="error-validation">
								<form:errors path="ano"></form:errors>
							</div>
							<div class="error-validation">
								${dataError}
							</div>
						</div>
					
						
						<label for="quantidadeVagas" class="col-sm-2 control-label">Quantidade de Vagas:</label>
						<div class="col-sm-2">
							<form:input id="quantidadeVagas" type="number" min="0" placeholder="0" path="quantidadeVagas" cssClass="form-control"/>
								<div class="error-validation">
								<form:errors path="quantidadeVagas"></form:errors>
								</div>
								<div class="error-validation">
								${quantidadeError}
								</div>
								
							</div>
					</div>
					
				<div class="form-group"> 
					<label for="tipoBolsa" class="col-sm-2 control-label">Tipo de Bolsa:</label>	
					<form:select name="${status.expression}" path="tipoBolsa" id="tipoBolsa">
							<c:forEach items="${tipoBolsa}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.tipo}"></c:out>
							</option>
						</c:forEach>	
					</form:select>
					</div>
					
				<div class="form-group">
						<label for="arquivo" class="col-sm-2 control-label">Arquivos:</label>
						<div class="col-sm-10 files">
							<input class="btn btn-success" type="file" name="file" title="Adicionar Arquivos" multiple="multiple">
		                    <table id="file-upload" role="presentation" class="table table-striped">
		                    	<tbody class="files">
		                    		<c:forEach items="${projeto.documentos}" var="documento">
		                    			<tr class="template-upload fade in">
									        <td>
									            <a href="<c:url value="/documento/${documento.id }" />">${documento.nomeOriginal}</a>
									            <strong class="error text-danger"></strong>
									        </td>
									        <td>
								                <a id="${documento.id}" href="#" class="delete-document">
													<button type="button" class="btn btn-danger">Excluir <span class="glyphicon glyphicon-trash"></span></button>
												</a>
									        </td>
									    </tr>	
		                    		</c:forEach>
		                    	</tbody>
		                    </table>
						</div>
					</div>
					
					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Cadastrar" />
						<a href="<c:url value="/selecao/listar"></c:url>" class="btn btn-default">Cancelar</a>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>

</html>