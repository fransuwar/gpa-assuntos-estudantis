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
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />

<title>Relatório de Visita Domiciliar</title>
</head>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />
	<ol class="breadcrumb">
		<li><a href="/MAE/selecao/listar">Listar Seleções</a></li>
		<li>Listar Inscritos</li>
	</ol>

	<div class="container">
		<sec:authorize access="hasAnyRole('STA','DOCENTE')">
			<div class="novo-projeto" align="left">
				<div class="form" align="center">
					<h2>Relatório de Visita Domiciliar</h2>

					<ul class="nav nav-tabs">
						<li class="active"><a href="#dados-gerais-tab"
							data-toggle="tab">Dados Gerais<i class="fa"></i>
						</a></li>
						<li><a href="#perfil-economico-tab" data-toggle="tab">Perfil
								Econômico<i class="fa"></i>
						</a></li>
						<li><a href="#moradia-tab" data-toggle="tab">Moradia<i
								class="fa"></i>
						</a></li>
						<li><a href="#bens-moveis-tab" data-toggle="tab">Bens
								Móveis<i class="fa"></i>
						</a></li>
						<li><a href="#sintese-perfil-tab" data-toggle="tab">Síntese
								Perfil Econômico<i class="fa"></i>
						</a></li>
						<li><a href="#analise-descricao-tab" data-toggle="tab">Análise
								e Descrição da Realidade/Perfil Psicossocial<i class="fa"></i>
						</a></li>
					</ul>

					<form:form id="relatorioForm" role="form"
						modelAttribute="relatorioVisitaDomiciliar"
						commandName="relatorioVisitaDomiciliar"
						servletRelativeAction="/servidor/visita" method="POST"
						cssClass="form-horizontal">

						<input type="hidden" id="idInscricao" name="idInscricao"
							value="${inscricao.id }">
						<input type="hidden" id="idSelecao" name="idSelecao"
							value="${idSelecao }" />

						<div class="tab-content">
							<div class="tab-pane active" id="dados-gerais-tab">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<h3 class="panel-title">Candidato</h3>
									</div>
									<div class="panel-body">


										<div class="form-group">
											<label for="nomeAluno" class="col-sm-2 control-label"
												id="form-label-2">Candidato:</label>
											<div class="col-sm-4">
												<form:input id="nomeAluno" type="text" readonly="true"
													path="" value="${inscricao.aluno.pessoa.nome}"
													cssClass="form-control" placeholder="Nome do candidato" />
											</div>
											<label for="curso" class="col-sm-2 control-label"
												id="form-label-2">Curso</label>
											<div class="col-sm-4">
												<form:input id="curso" type="text" readonly="true"
													path="curso" cssClass="form-control" placeholder=""
													value="${inscricao.aluno.curso}" />
												<div class="error-validation">
													<form:errors path="curso"></form:errors>
												</div>
											</div>
										</div>



										<div class="form-group">
											<label for="formaAcesso" class="col-sm-2 control-label"
												id="form-label-2" style="padding-left: 2px;">Forma
												de acesso a casa:</label>
											<div class="col-sm-4">
												<form:input id="formaAcesso" type="text"
													path="formaAcessoCasa" cssClass="form-control"
													placeholder="Forma de Acesso" />
												<div class="error-validation">
													<form:errors path="formaAcessoCasa"></form:errors>
												</div>
											</div>

											<label for="semestre" class="col-sm-2 control-label"
												id="form-label-2">Semestre:</label>
											<div class="col-sm-3">
												<form:input id="semestre" type="text" path="semestre"
													cssClass="form-control" placeholder="Semestre" value="0" />
												<div class="error-validation">
													<form:errors path="semestre"></form:errors>
												</div>
											</div>

										</div>



										<div class="form-group">
											<label for="dataVisita" class="col-sm-2 control-label"
												id="form-label-2">Data da visita:</label>
											<div class="col-sm-4">
												<form:input id="dataVisita" type="text" path="dataVisita"
													cssClass="form-control data" placeholder="Data da Visita" />
												<div class="error-validation">
													<form:errors path="dataVisita"></form:errors>
												</div>
											</div>

											<label for="tecnico" class="col-sm-2 control-label"
												id="form-label-2" style="padding-left: 4px;">Técnicos
												Responsáveis:</label>
											<div class="col-sm-4">
												<form:input id="tecnico" type="text" path=""
													cssClass="form-control" placeholder="Técnico" />
												<div class="error-validation">
													<form:errors path=""></form:errors>
												</div>
											</div>
										</div>



									</div>
								</div>
							</div>
						</div>






						<div class="tab-pane" id="perfil-economico-tab">
							<div class="panel panel-primary">

								<div class="panel-heading">
									<h3 class="panel-title">I - Perfil Econômico: Despesas</h3>
								</div>
								<div class="panel-body">
									<div class="panel-body">
										<jsp:include page="despesaVisita.jsp" />
									</div>
								</div>








								<div class="panel-heading">
									<h3 class="panel-title">II - Perfil Econômico: Receitas</h3>
								</div>
								<div class="panel-body">
									<div class="panel-body">
										<jsp:include page="receitasVisitas.jsp" />
									</div>
								</div>
							</div>
						</div>








						<div class="tab-pane" id="moradia-tab">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">III - Moradia</h3>
								</div>
								<div class="panel-body">

									<div class="form-group">
										<label for="moradiaVarandaQtd" class="col-sm-2 control-label"
											id="form-label-right">Varanda:</label>
										<div class="col-sm-1">
											<form:input id="moradiaVarandaQtd" data-mask="999" min="0"
												value="0" path="moradiaVarandaQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="moradiaVarandaQtd"></form:errors>
											</div>
										</div>
										<div class="col-sm-3">
											<form:select path="moradiaVarandaEstado" id="moradiaEstado"
												cssClass="form-control">
												<form:option value="" label="Selecione o estado da Varanda" />
												<form:options items="${estadoMoradia}" itemLabel="nome" />
											</form:select>

										</div>

										<label for="moradiaSalaQtd" class="col-sm-2 control-label"
											id="form-label-right">Sala:</label>
										<div class="col-sm-1">
											<form:input id="moradiaSalaQtd" data-mask="999" min="0"
												value="0" path="moradiaSalaQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="moradiaSalaQtd"></form:errors>
											</div>
										</div>
										<div class="col-sm-3">
											<form:select path="moradiaSalaEstado" id="moradiaSalaEstado"
												cssClass="form-control">
												<form:option value="" label="Selecione o estado da Sala" />
												<form:options items="${estadoMoradia}" itemLabel="nome" />
											</form:select>
										</div>
									</div>


									<div class="form-group">
										<label for="moradiaBanheiroQtd" class="col-sm-2 control-label"
											id="form-label-right">Banheiro:</label>
										<div class="col-sm-1">
											<form:input id="moradiaBanheiroQtd" data-mask="999" min="0"
												value="0" path="moradiaBanheiroQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="moradiaBanheiroQtd"></form:errors>
											</div>
										</div>
										<div class="col-sm-3">
											<form:select path="moradiaBanheiroEstado"
												id="moradiaBanheiroEstado" cssClass="form-control">
												<form:option value="" label="Selecione o estado do Banheiro" />
												<form:options items="${estadoMoradia}" itemLabel="nome" />
											</form:select>
										</div>

										<label for="moradiaQuartoQtd" class="col-sm-2 control-label"
											id="form-label-right">Quarto:</label>
										<div class="col-sm-1">
											<form:input id="moradiaQuartoQtd" data-mask="999" min="0"
												value="0" path="moradiaQuartoQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="moradiaQuartoQtd"></form:errors>
											</div>
										</div>
										<div class="col-sm-3">
											<form:select path="moradiaQuartoEstado"
												id="moradiaQuartoEstado" cssClass="form-control">
												<form:option value="" label="Selecione o estado do Quarto" />
												<form:options items="${estadoMoradia}" itemLabel="nome" />
											</form:select>
										</div>
									</div>


									<div class="form-group">
										<label for="moradiaCozinhaQtd" class="col-sm-2 control-label"
											id="form-label-right">Cozinha:</label>
										<div class="col-sm-1">
											<form:input id="moradiaCozinhaQtd" data-mask="999" min="0"
												value="0" path="moradiaCozinhaQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="moradiaCozinhaQtd"></form:errors>
											</div>
										</div>
										<div class="col-sm-3">
											<form:select path="moradiaCozinhaEstado"
												id="moradiaCozinhaEstado" cssClass="form-control">
												<form:option value="" label="Selecione o estado da Cozinha" />
												<form:options items="${estadoMoradia}" itemLabel="nome" />
											</form:select>
										</div>

										<label for="moradiaQuintalQtd" class="col-sm-2 control-label"
											id="form-label-right">Quintal:</label>
										<div class="col-sm-1">
											<form:input id="moradiaQuntalQtd" data-mask="999" min="0"
												value="0" path="moradiaQuintalQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="moradiaQuintalQtd"></form:errors>
											</div>
										</div>
										<div class="col-sm-3">
											<form:select path="moradiaQuintalEstado"
												id="moradiaQuintalEstado" cssClass="form-control">
												<form:option value="" label="Selecione o estado do Quintal" />
												<form:options items="${estadoMoradia}" itemLabel="nome" />
											</form:select>
										</div>
									</div>

								</div>

								<div class="panel-heading">
									<h3 class="panel-title">IV - Utensílios Domésticos</h3>
								</div>
								<div class="panel-body">

									<div class="form-group">
										<label for="utensilioTvQtd" class="col-sm-2 control-label"
											id="form-label-right">TV:</label>
										<div class="col-sm-1">
											<form:input id="utensilioTvQtd" data-mask="999" min="0"
												value="0" path="utensilioTvQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioTvQtd" id="utensilioTvQtd"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-sm-3">
											<form:input id="utensilioTvObservacao"
												path="utensilioTvObservacao" placeholder="Observação"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioTvObservacao"
													id="utensilioTvObservacao" cssClass="form-control" />
											</div>
										</div>

										<label for="utensilioSomQtd" class="col-sm-2 control-label"
											id="form-label-right">Som:</label>
										<div class="col-sm-1">
											<form:input id="utensilioSomQtd" data-mask="999" min="0"
												value="0" path="utensilioSomQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioSomQtd" id="utensilioSomQtd"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-sm-3">
											<form:input id="utensilioSomObservacao"
												path="utensilioSomObservacao" placeholder="Observação"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioSomObservacao"
													id="utensilioSomObservacao" cssClass="form-control" />
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="utensilioComputadorQtd"
											class="col-sm-2 control-label" id="form-label-right">Computador:</label>
										<div class="col-sm-1">
											<form:input id="utensilioComputadorQtd" data-mask="999"
												min="0" value="0" path="utensilioComputadorQtd"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioComputadorQtd"
													id="utensilioComputadorQtd" cssClass="form-control" />
											</div>
										</div>
										<div class="col-sm-3">
											<form:input id="utensilioComputadorObservacao"
												path="utensilioComputadorObservacao"
												placeholder="Observação" cssClass="form-control" value="" />
											<div class="error-validation">
												<form:errors path="utensilioComputadorObservacao"
													id="utensilioComputadorObservacao" cssClass="form-control" />
											</div>
										</div>

										<label for="utensilioFogaoQtd" class="col-sm-2 control-label"
											id="form-label-right">Fogão:</label>
										<div class="col-sm-1">
											<form:input id="utensilioFogaoQtd" data-mask="999" min="0"
												value="0" path="utensilioFogaoQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioFogaoQtd" id="utensilioFogaoQtd"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-sm-3">
											<form:input id="utensilioFogaoObservacao"
												path="utensilioFogaoObservacao" placeholder="Observação"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioFogaoObservacao"
													id="utensilioFogaoObservacao" cssClass="form-control" />
											</div>
										</div>
									</div>


									<div class="form-group">
										<label for="utensilioGeladeiraQtd"
											class="col-sm-2 control-label" id="form-label-right">Geladeira:</label>
										<div class="col-sm-1">
											<form:input id="utensilioGeladeiraQtd" data-mask="999"
												min="0" value="0" path="utensilioGeladeiraQtd"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioGeladeiraQtd"
													id="utensilioGeladeiraQtd" cssClass="form-control" />
											</div>
										</div>
										<div class="col-sm-3">
											<form:input id="utensilioGeladeiraObservacao"
												path="utensilioGeladeiraObservacao" placeholder="Observação"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioGeladeiraObservacao"
													id="utensilioGeladeiraObservacao" cssClass="form-control" />
											</div>
										</div>


										<label for="utensilioFreezerQtd"
											class="col-sm-2 control-label" id="form-label-right">Freezer:</label>
										<div class="col-sm-1">
											<form:input id="utensilioFreezerQtd" data-mask="999" min="0"
												value="0" path="utensilioFreezerQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioFreezerQtd"
													id="utensilioFreezerQtd" cssClass="form-control" />
											</div>
										</div>
										<div class="col-sm-3">
											<form:input id="utensilioFreezerObservacao"
												path="utensilioFreezerObservacao" placeholder="Observação"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioFreezerObservacao"
													id="utensilioFreezerObservacao" cssClass="form-control" />
											</div>
										</div>
									</div>


									<div class="form-group">
										<label for="utensilioLavadoraQtd"
											class="col-sm-2 control-label" id="form-label-right">Lavadora:</label>
										<div class="col-sm-1">
											<form:input id="utensilioLavadoraQtd" data-mask="999" min="0"
												value="0" path="utensilioLavadoraQtd"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioLavadoraQtd"
													id="utensilioLavadoraQtd" cssClass="form-control" />
											</div>
										</div>
										<div class="col-sm-3">
											<form:input id="utensilioLavadoraObservacao"
												path="utensilioLavadoraObservacao" placeholder="Observação"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioLavadoraObservacao"
													id="utensilioLavadoraObservacao" cssClass="form-control" />
											</div>
										</div>


										<label for="utensilioDvdQtd" class="col-sm-2 control-label"
											id="form-label-right">DVD:</label>
										<div class="col-sm-1">
											<form:input id="utensilioDvdQtd" data-mask="999" min="0"
												value="0" path="utensilioDvdQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioDvdQtd" id="utensilioDvdQtd"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-sm-3">

											<form:input id="utensilioDvdObservacao"
												path="utensilioDvdObservacao" placeholder="Observação"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioDvdObservacao"
													id="utensilioDvdObservacao" cssClass="form-control" />
											</div>
										</div>
									</div>


									<div class="form-group">
										<label for="utensilioOutrosQtd" class="col-sm-2 control-label"
											id="form-label-right">Outros:</label>
										<div class="col-sm-1">
											<form:input id="utensilioOutrosQtd" data-mask="999" min="0"
												value="0" path="utensilioOutrosQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioOutrosQtd"
													id="utensilioOutrosQtd" cssClass="form-control" />
											</div>
										</div>
										<div class="col-sm-3">

											<form:input id="utensilioOutrosObservacao"
												path="utensilioOutrosObservacao" placeholder="Observação"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="utensilioOutrosObservacao"
													id="utensilioOutrosObservacao" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>


							</div>
						</div>


						<div class="tab-pane" id="bens-moveis-tab">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">V - Bens Móveis</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<label for="bemMovelMoto" class="col-sm-2 control-label"
											id="form-label-right">Moto:</label>
										<div class="col-sm-1">
											<form:input id="bemMovelMotoQtd" data-mask="999" min="0"
												value="0" placeholder="Quantidade de Motos"
												path="bemMovelMotoQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="bemMovelMotoQtd" id="bemMovelMotoQtd" />
											</div>
										</div>
										<div class="col-sm-3">
											<form:input id="bemMovelMotoObservacao"
												path="bemMovelMotoObservacao" placeholder="Observação"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="bemMovelMotoObservacao"
													id="bemMovelMotoObservacao" />
											</div>
										</div>

										<label for="bemMovelBicicleta" class="col-sm-2 control-label"
											id="form-label-right">Bicicleta:</label>
										<div class="col-sm-1">
											<form:input id="bemMovelBicicletaQtd" data-mask="999" min="0"
												value="0" placeholder="Quantidade de Bicicletas"
												path="bemMovelBicicletaQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="bemMovelBicicletaQtd"
													id="bemMovelBicicletaQtd" />
											</div>
										</div>
										<div class="col-sm-3">
											<form:input id="bemMovelBicicletaObservacao"
												path="bemMovelBicicletaObservacao" placeholder="Observação"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="bemMovelBicicletaObservacao"
													id="bemMovelBicicletaObservacao" />
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="bemMovelCarro" class="col-sm-2 control-label"
											id="form-label-right">Carro:</label>
										<div class="col-sm-1">
											<form:input id="bemMovelCarroQtd" data-mask="999" min="0"
												value="0" placeholder="Quantidade de Carros"
												path="bemMovelCarroQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="bemMovelCarroQtd" id="bemMovelCarroQtd" />
											</div>
										</div>
										<div class="col-sm-3">
											<form:input id="bemMovelCarroObservacao"
												path="bemMovelCarroObservacao" placeholder="Observação"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="bemMovelCarroObservacao"
													id="bemMovelCarroObservacao" />
											</div>
										</div>

										<label for="bemMovelOutros" class="col-sm-2 control-label"
											id="form-label-right">Outros:</label>
										<div class="col-sm-1">
											<form:input id="bemMovelOutrosQtd" data-mask="999" min="0"
												value="0" placeholder="Quantidade de Outros bens móveis"
												path="bemMovelOutrosQtd" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="bemMovelOutrosQtd" id="bemMovelOutrosQtd" />
											</div>
										</div>
										<div class="col-sm-3">
											<form:input id="bemMovelOutrosObservacao"
												path="bemMovelOutrosObservacao" placeholder="Observação"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="bemMovelOutrosObservacao"
													id="bemMovelOutrosObservacao" />
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>


						<div class="tab-pane" id="analise-descricao-tab">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">VI - Síntese Perfil Econômico</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<h4>
											<b>Informe quais itens são compatíveis com a renda</b>
										</h4>
										<label for="perfilCompativelUtensilioDomestico"
											class="col-sm-4 control-label">Utensílios doméstico
											(Eletro Eletrônico):</label>
										<div class="col-sm-1" id="col-sm-radio">
											<form:checkbox path="perfilCompativelUtensilioDomestico" />
											<div class="error-validation">
												<form:errors path="perfilCompativelUtensilioDomestico"></form:errors>
											</div>
										</div>
										<label for="perfilCompativelBensMoveis"
											class="col-sm-4 control-label">Bens Móveis:</label>
										<div class="col-sm-1" id="col-sm-radio">
											<form:checkbox path="perfilCompativelBensMoveis" />
											<div class="error-validation">
												<form:errors path="perfilCompativelBensMoveis"></form:errors>
											</div>
										</div>
										<label for="perfilCompativelMaquinario"
											class="col-sm-4 control-label">Maquinário:</label>
										<div class="col-sm-1" id="col-sm-radio">
											<form:checkbox path="perfilCompativelMaquinario" />
											<div class="error-validation">
												<form:errors path="perfilCompativelMaquinario"></form:errors>
											</div>
										</div>
										<label for="perfilCompativelAspectoFisicoResidencia"
											class="col-sm-4 control-label">Aspecto Físico da
											Residência:</label>
										<div class="col-sm-1" id="col-sm-radio">
											<form:checkbox path="perfilCompativelAspectoFisicoResidencia" />
											<div class="error-validation">
												<form:errors path="perfilCompativelAspectoFisicoResidencia"></form:errors>
											</div>
										</div>
										<label for="perfilCompativelOutros"
											class="col-sm-4 control-label">Outros:</label>
										<div class="col-sm-1" id="col-sm-radio">
											<form:checkbox path="perfilCompativelOutros" />
											<div class="error-validation">
												<form:errors path="perfilCompativelOutros"></form:errors>
											</div>
										</div>

									</div>

								</div>
							</div>
						</div>


						<div class="tab-pane" id="">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">Análise e Descrição da Realidade/Perfil Psicossocial</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<div class="form-group">
											<label for="analiseDescricaoRealidade"
												class="col-sm-3 control-label">Análise e Descrição
												da Realidade:</label>
											<div class="col-sm-8">
												<form:textarea id="analiseDescricaoRealidade"
													path="analiseDescricaoRealidade" cssClass="form-control"
													placeholder="Análise e Descrição da Realidade" rows="10" />
												<div class="error-validation">
													<form:errors path="analiseDescricaoRealidade"></form:errors>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="panel-heading">
									<h3 class="panel-title">Parecer Final</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<label for="parecerFinalDeferido"
											class="col-sm-4 control-label" id="form-label-right">Deferido:</label>
										<div class="col-sm-1" id="col-sm-radio">
											<form:checkbox path="parecerFinalDeferido" />
											<div class="error-validation">
												<form:errors path="parecerFinalDeferido"></form:errors>
											</div>
										</div>

										<label for="dataRelatorio" class="col-sm-2 control-label"
											id="form-label-1">Data do Relatório</label>
										<div class="col-sm-2">
											<form:input id="dataRelatorio" path="dataRelatorio"
												cssClass="form-control data" placeholder="Data" />
											<div class="error-validation">
												<form:errors path="dataRelatorio" />
											</div>
										</div>

									</div>
								</div>

							</div>
						</div>


						<div class="form-group">
							<div class="col-sm-2" id="div-form-btn">
								<input name="submit" type="submit" class="btn btn-primary"
									value="Cadastrar" id="form-btn" />
							</div>
							<div class="col-sm-2" id="div-form-btn">
								<a href="<c:url value="/selecao/inscritos/${id}" ></c:url>"
									class="btn btn-default" id="form-btn">Cancelar</a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</sec:authorize>
	</div>


	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>