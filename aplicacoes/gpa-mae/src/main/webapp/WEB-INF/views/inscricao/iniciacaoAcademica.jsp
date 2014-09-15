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
					<fieldset>
					<legend>Moradia</legend>
					<div class="form-group">
					<fieldset class="sche">
						<label for="endereco_familia" class="col-sm-2 control-label">Rua/Av :</label>
						<div class="col-sm-4">
							<form:input id="endereco_familia" path="endereco_familia" cssClass="form-control" placeholder="Rua /Av"/>
							<div class="error-validation">
								<form:errors path="endereco_familia"></form:errors>
							</div>
						</div>
						
						<label for="complemento_familia" class="col-sm-2 control-label">Complemento:</label>
						<div class="col-sm-4">
							<form:input id="complemento_familia" type="text" path="complemento_familia" cssClass="form-control data" placeholder="Data de Término"/>
							<div class="error-validation">
								<form:errors path="complemento_familia"></form:errors>
							</div>
						</div>
					</fieldset>	
					</div>
					
					<div class="form-group">
						<label for="numero_familia" class="col-sm-2 control-label">Número:</label>
						<div class="col-sm-2">
							<form:input id="numero_familia"  path="numero_familia" cssClass="form-control" placeholder="Número"/>
							<div class="error-validation">
								<form:errors path="numero_familia"></form:errors>
							</div>
						</div>	
						<label for="cep_familia" class="col-sm-2 control-label">CEP:</label>
						<div class="col-sm-2">
							<form:textarea id="cep_familia"  path="cep_familia" cssClass="form-control" placeholder="CEP"/>
							<div class="error-validation">
								<form:errors path="cep_familia"></form:errors>
							</div>
					    </div>
					    
					    <label for="bairro_familia" class="col-sm-2 control-label">Bairro:</label>
						<div class="col-sm-2">
							<form:textarea id="bairro_familia" type="text" path="bairro_familia" cssClass="form-control data"  placeholder="Bairro"/>
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
							<form:input id="telefone_celular" type="number" min="0"  path="telefone_celular" cssClass="form-control" placeholder="Fone"/>
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
					</fieldset>
					
					
					
					
					
					
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
					</fieldset>			
					
					
					
					</div>
					<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Qual a situação da sua residencia:</label>	
						<div class="col-sm-2">
						<select name="${status.expression}" name="nivelInstrucao" id="nivelInstrucao">
							<option value=""> Selecione a situação</option>
							<option value=""> 1</option>
							<option value=""> 2</option>
							<option value=""> 3</option>
						 
							
						</select>
						</div>
					</div>
					
					<label for="qtd_aparelho_som" class="col-sm-2 control-label">Aparelho de Som:</label>
						<div class="col-sm-4">
							<form:input id="qtd_aparelho_som" type="number" min="0"  path="qtd_aparelho_som" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_aparelho_som"></form:errors>
							</div>
						</div>
					
					<label for="qtd_televisao" class="col-sm-2 control-label">Televisão:</label>
						<div class="col-sm-4">
							<form:input id="qtd_televisao" type="number" min="0"  path="qtd_televisao" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_televisao"></form:errors>
							</div>
						</div>
					
					<label for="qtd_radio" class="col-sm-2 control-label">Radio:</label>
						<div class="col-sm-4">
							<form:input id="qtd_radio" type="number" min="0"  path="qtd_radio" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_radio"></form:errors>
							</div>
						</div>
					
					<label for="qtd_automovel" class="col-sm-2 control-label">Automovel:</label>
						<div class="col-sm-4">
							<form:input id="qtd_automovel" type="number" min="0"  path="qtd_automovel" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_televisao"></form:errors>
							</div>
						</div>
					
					<label for="qtd_motocicleta" class="col-sm-2 control-label">Motocicleta:</label>
						<div class="col-sm-4">
							<form:input id="qtd_motocicleta" type="number" min="0"  path="qtd_motocicleta" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_motocicleta"></form:errors>
							</div>
						</div>
					
					<label for="qtd_dvd_videocassete" class="col-sm-2 control-label">Dvd/VideoCassete:</label>
						<div class="col-sm-4">
							<form:input id="qtd_dvd_videocassete" type="number" min="0"  path="qtd_dvd_videocassete" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_dvd_videocassete"></form:errors>
							</div>
						</div>
					
					<label for="qtd_maquina_lavar" class="col-sm-2 control-label">Maquina de Lavar:</label>
						<div class="col-sm-4">
							<form:input id="qtd_maquina_lavar" type="number" min="0"  path="qtd_maquina_lavar" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_televisao"></form:errors>
							</div>
						</div>
					
					<label for="qtd_geladeira" class="col-sm-2 control-label">Geladeira:</label>
						<div class="col-sm-4">
							<form:input id="qtd_geladeira" type="number" min="0"  path="qtd_geladeira" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_geladeira"></form:errors>
							</div>
						</div>
					
					<label for="qtd_freezer" class="col-sm-2 control-label">Freezer:</label>
						<div class="col-sm-4">
							<form:input id="qtd_freezer" type="number" min="0"  path="qtd_freezer" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_freezer"></form:errors>
							</div>
						</div>
					
					<label for="qtd_telefonefixo" class="col-sm-2 control-label">Telefone Fixo:</label>
						<div class="col-sm-4">
							<form:input id="qtd_telefonefixo" type="number" min="0"  path="qtd_telefonefixo" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_telefonefixo"></form:errors>
							</div>
						</div>
					
					<label for="qtd_celular_residentes" class="col-sm-2 control-label">Celular Residentes:</label>
						<div class="col-sm-4">
							<form:input id="qtd_celular_residentes" type="number" min="0"  path="qtd_celular_residentes" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_celular_residentes"></form:errors>
							</div>
						</div>
					
					<label for="qtd_computador" class="col-sm-2 control-label">Computador:</label>
						<div class="col-sm-4">
							<form:input id="qtd_computador" type="number" min="0"  path="qtd_computador" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_computador"></form:errors>
							</div>
						</div>
					
					<label for="qtd_fogao_gas" class="col-sm-2 control-label">Fogao A Gás:</label>
						<div class="col-sm-4">
							<form:input id="qtd_fogao_gas" type="number" min="0"  path="qtd_fogao_gas" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_fogao_gas"></form:errors>
							</div>
						</div>
					
					<label for="qtd_maquina_costura" class="col-sm-2 control-label">Máquina de Costura:</label>
						<div class="col-sm-4">
							<form:input id="qtd_maquina_costura" type="number" min="0"  path="qtd_maquina_costura" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_maquina_costura"></form:errors>
							</div>
						</div>
					
					<label for="qtd_comodos_sem_banheiro" class="col-sm-2 control-label">Comodo sem Banheiro:</label>
						<div class="col-sm-4">
							<form:input id="qtd_comodos_sem_banheiro" type="number" min="0"  path="qtd_comodos_sem_banheiro" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_comodos_sem_banheiro"></form:errors>
							</div>
						</div>
					
					<label for="qtd_banheiros" class="col-sm-2 control-label">Banheiros:</label>
						<div class="col-sm-4">
							<form:input id="qtd_banheiros" type="number" min="0"  path="qtd_banheiros" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_banheiros"></form:errors>
							</div>
						</div>
					
					<label for="qtd_empregados_domesticos" class="col-sm-2 control-label">Empregados Domesticos:</label>
						<div class="col-sm-4">
							<form:input id="qtd_empregados_domesticos" type="number" min="0"  path="qtd_empregados_domesticos" cssClass="form-control" placeholder="Fone"/>
							<div class="error-validation">
								<form:errors path="qtd_empregados_domesticos"></form:errors>
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

</html>