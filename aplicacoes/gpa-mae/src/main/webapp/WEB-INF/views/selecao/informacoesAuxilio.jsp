<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags"%>

<html>
<head>
	<jsp:include page="../fragments/bodyHeader.jsp" />
<title>Informações</title>
</head>
<body>
	<jsp:include page="../fragments/headTag.jsp" />
	<div class="center col-md-6" align="left">
		
		<div class="panel panel-primary" align="left">
  			<div class="panel-heading">Informações da Bolsa De Auxílio Moradia</div>
 				 <div class="panel-body">
   				 <b>Orgão</b> Universidade Federal do Ceará<p>
   				 <b>Período de Inscrições: </b> de 12 de Janeiro a 01 de Fevereiro de 2015<p>
   				 <b>Valor da Bolsa</b> R$ 400<p>
   				 
   				 <b>Descrição do Programa</b><p>
   				O Programa Auxílio Moradia tem por objetivo viabilizar a permanência de estudantes matriculados nos<p>
   				 Cursos de Graduação dos Campi da Universidade Federal do Ceará (UFC) em Sobral, Cariri e Quixadá, em <p>
   				 comprovada situação de vulnerabilidade econômica, assegurando-lhes auxílio institucional para<p> 
   				 complementação de despesas com moradia e alimentação durante todo o período do curso ou enquanto <p>
   				persistir a mesma situação.
  				</div>
		</div>
		
		<div class="panel panel-primary">
			<div class="panel-heading">Edital</div>
			<td class="head" valign="top">Arquivos:</td>
				<c:forEach var="documento" items="${projeto.documentos}">
					<td class="content"><a href="<c:url value="/documento/${documento.id}"></c:url>">${documento.nomeOriginal}</a></td>
				</c:forEach>
		</div>	
		
		
	</div>
		<div class="col-md-4">
			<div class="panel panel-primary">
			<div class="panel-heading">Login</div>
			<div class="center col-md-4">
		<div style="text-align: center" class="panel panel-default">
			<div class="panel-heading">
				<h5 class="panel-title">Login</h5>
			</div>
			<div class="panel-body">

				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>

				<form name='f' action="<c:url value='j_spring_security_check' />"
					method='POST'>
					<div class="form-group col-md-12 form-inline">
						<h4 class="col-md-6">Usuário:</h4>
						<input class="col-md-8 form-control" type='text' name='j_username'
							value=''>
					</div>
					<div class="form-group col-md-12 form-inline">
						<h4 class="col-md-6">Senha:</h4>
						<input class="col-md-8 form-control" type='password'
							name='j_password' />
					</div>
					<input class="btn btn-primary" name="submit" type="submit"
						value="Login" value="Login" /> <input class="btn btn-default"
						name="reset" type="reset" value="Limpar" />
				</form>
			</div>
		</div>
	</div>
		</div>
		</div>	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>