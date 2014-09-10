<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<jsp:include page="../fragments/headTag.jsp" />
	<title>Cadastro de Seleção de Bolsas</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />
	
	 <div class="container">
		<div class="novo-projeto" align="left">
			<div class="form" align="center">
				<h2>Programa de Iniciacao Academica</h2>
				<form:form id="adicionarSeleçãoForm" role="form" commandName="QuestionarioIniciacaoAcademica" servletRelativeAction="/inscricao/iniciacaoAcademica" method="POST" cssClass="form-horizontal">

					<div class="form-group">
						<label for="endereco_atual" class="col-sm-2 control-label">Rua/Av :</label>
						<div class="col-sm-4">
							<form:input id="endereco_atual" path="endereco_atual" cssClass="form-control" placeholder="Rua /Av"/>
							<div class="error-validation">
								<form:errors path="endereco_atual"></form:errors>
							</div>
						</div>
						
						<label for="complemento" class="col-sm-2 control-label">Complemento:</label>
						<div class="col-sm-4">
							<form:input id="complemento" type="text" path="complemento" cssClass="form-control data" placeholder="Data de Término"/>
							<div class="error-validation">
								<form:errors path="complemento"></form:errors>
							</div>
						</div>
						
					</div>
					
					<div class="form-group">
						<label for="numero" class="col-sm-2 control-label">Número:</label>
						<div class="col-sm-2">
							<form:input id="numero"  path="numero" cssClass="form-control" placeholder="Número"/>
							<div class="error-validation">
								<form:errors path="numero"></form:errors>
							</div>
						</div>	
						<label for="cep" class="col-sm-2 control-label">CEP:</label>
						<div class="col-sm-2">
							<form:textarea id="cep"  path="cep" cssClass="form-control" placeholder="CEP"/>
							<div class="error-validation">
								<form:errors path="cep"></form:errors>
							</div>
					    </div>
					    
					    <label for="bairro" class="col-sm-2 control-label">Bairro:</label>
						<div class="col-sm-2">
							<form:textarea id="bairro" type="text" path="bairro" cssClass="form-control data"  placeholder="Bairro"/>
							<div class="error-validation">
								<form:errors path="bairro"></form:errors>
							</div>
					    </div>
													
					</div>
					
					<div class="form-group">
						<label for="telefone_fixo" class="col-sm-2 control-label">Fone:</label>
						<div class="col-sm-4">
							<form:input id="telefone_fixo"  path="telefone_fixo" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="telefone_fixo"></form:errors>
							</div>
						</div>
						
						<label for="telefone_celular" class="col-sm-2 control-label">Celular:</label>
						<div class="col-sm-4">
							<form:input id="telefone_celular"  path="telefone_celular" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="telefone_celular"></form:errors>
							</div>
						</div>
					
					
					</div>
					<div class="form-group">
						<label for="cidade" class="col-sm-2 control-label">Cidade:</label>
						<div class="col-sm-4">
							<form:input id="cidade" type="text" path="cidade" cssClass="form-control" placeholder="Cidade"/>
							<div class="error-validation">
								<form:errors path="cidade"></form:errors>
							</div>
						</div>
					
					<label for="cidade" class="col-sm-2 control-label">Nivel Instrução:</label>	
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					
					<div class="form-group">
						<label for="cidade" class="col-sm-2 control-label">Ponto de referencia:</label>
						<div class="col-sm-2">
							<form:input id="cidade" type="text" path="cidade" cssClass="form-control" placeholder="Cidade"/>
							<div class="error-validation">
								<form:errors path="cidade"></form:errors>
							</div>
						</div>
								
					
					</div>
					
					
					
					
					
					
					
					
					<div class="form-group">
						<label for="endereco_atual" class="col-sm-2 control-label">Rua/Av :</label>
						<div class="col-sm-4">
							<form:input id="endereco_atual" path="endereco_atual" cssClass="form-control" placeholder="Rua /Av"/>
							<div class="error-validation">
								<form:errors path="endereco_atual"></form:errors>
							</div>
						</div>
						
						<label for="complemento" class="col-sm-2 control-label">Complemento:</label>
						<div class="col-sm-4">
							<form:input id="complemento" type="text" path="complemento" cssClass="form-control data" placeholder="Data de Término"/>
							<div class="error-validation">
								<form:errors path="complemento"></form:errors>
							</div>
						</div>
						
					</div>
					
					<div class="form-group">
						<label for="numero" class="col-sm-2 control-label">Número:</label>
						<div class="col-sm-2">
							<form:input id="numero"  path="numero" cssClass="form-control" placeholder="Número"/>
							<div class="error-validation">
								<form:errors path="numero"></form:errors>
							</div>
						</div>	
						<label for="cep" class="col-sm-2 control-label">CEP:</label>
						<div class="col-sm-2">
							<form:textarea id="cep"  path="cep" cssClass="form-control" placeholder="CEP"/>
							<div class="error-validation">
								<form:errors path="cep"></form:errors>
							</div>
					    </div>
					    
					    <label for="bairro" class="col-sm-2 control-label">Bairro:</label>
						<div class="col-sm-2">
							<form:textarea id="bairro" type="text" path="bairro" cssClass="form-control data"  placeholder="Bairro"/>
							<div class="error-validation">
								<form:errors path="bairro"></form:errors>
							</div>
					    </div>
													
					</div>
					
					<div class="form-group">
						<label for="telefone_fixo" class="col-sm-2 control-label">Fone:</label>
						<div class="col-sm-4">
							<form:input id="telefone_fixo"  path="telefone_fixo" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="telefone_fixo"></form:errors>
							</div>
						</div>
						
						<label for="telefone_celular" class="col-sm-2 control-label">Celular:</label>
						<div class="col-sm-4">
							<form:input id="telefone_celular"  path="telefone_celular" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="telefone_celular"></form:errors>
							</div>
						</div>
					
					
					</div>
					<div class="form-group">
						<label for="cidade" class="col-sm-2 control-label">Cidade:</label>
						<div class="col-sm-4">
							<form:input id="cidade" type="text" path="cidade" cssClass="form-control" placeholder="Cidade"/>
							<div class="error-validation">
								<form:errors path="cidade"></form:errors>
							</div>
						</div>
					
					<label for="cidade" class="col-sm-2 control-label">Nivel Instrução:</label>	
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					
					
					
					
					</div>
					
					<div class="form-group">
						<label for="cidade" class="col-sm-2 control-label">Ponto de referencia:</label>
						<div class="col-sm-2">
							<form:input id="cidade" type="text" path="cidade" cssClass="form-control" placeholder="Cidade"/>
							<div class="error-validation">
								<form:errors path="cidade"></form:errors>
							</div>
						</div>
								
					
					</div>
					
					
					<div class="form-group">
						<label for="cidade" class="col-sm-2 control-label">Com quem você reside Atualmente:</label>
						<div class="col-sm-4">
							<form:input id="cidade" type="text" path="cidade" cssClass="form-control" placeholder="Cidade"/>
							<div class="error-validation">
								<form:errors path="cidade"></form:errors>
							</div>
						</div>
								
					
					</div>
					
					
					<div class="form-group">
						<label for="cidade" class="col-sm-2 control-label">Como define a localidade na qual você mora?:</label>
						<div class="col-sm-4">
							<form:input id="cidade" type="text" path="cidade" cssClass="form-control" placeholder="Cidade"/>
							<div class="error-validation">
								<form:errors path="cidade"></form:errors>
							</div>
						</div>
								
					
					</div>
					
					<label for="cidade" class="col-sm-2 control-label">Qual a situação da sua residencia:</label>	
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Aparelho de Som:</label>	
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Televisao:</label>	
 						<div class="col-sm-2">
							<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
							<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>
							
							</select>
						</div>	
					</div>
					
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Radio:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Automovel:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Motocicleta:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Maquina de Lavar Roupa:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">DVD ou VideoCassete:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Geladeira:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">freezer:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Telefone Fixo:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Celular do moradores da residencia:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Computador:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Fogão a Gas:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Maquina de costura:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Cômodos excluindo o banheiro:</label>	
					<div class="col-sm-2">
					<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
						
						<c:forEach items="${NivelInstrucao}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.nome}"></c:out>
							</option>
						</c:forEach>	
					</select>
					</div>
					</div>
					
									
					
					
					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Cadastrar" />
						<a href="<c:url value="/inscricao/iniciacaoAcademica"></c:url>" class="btn btn-default">Cancelar</a>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>

</html>