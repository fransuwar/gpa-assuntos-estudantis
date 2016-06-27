<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="panel-card">
	<div class="panel-card-content">
		<label id="card-selected" class="hidden">${card}</label>
		<div id="card-inscricao" class="card card-hover">
			<a href="<c:url value="/servidor/detalhes/${selecao.id}"></c:url>">
				<div class="card-content">
					<div class="card-icon">
						<i class="fa fa-folder-open"></i>
					</div>
					<div class="card-deion">INSCRIÇÕES</div>
				</div>
			</a>
		</div>

		<div id="card-comissao" class="card">
			<a
				href="<c:url value="/coordenador/comissao/atribuir/${selecao.id}" ></c:url>">
				<div class="card-content">
					<div class="card-icon">
						<i class="fa fa-users"></i>
					</div>
					<div class="card-deion">COMISSÃO</div>
				</div>
			</a>
		</div>
		<div id="card-arquivos" class="card">
			<a
				href="<c:url value="/coordenador/selecao/adicionar-documento/${selecao.id}"></c:url>">
				<div class="card-content">
					<div class="card-icon">
						<i class="fa fa-file"></i>
					</div>
					<div class="card-deion">ARQUIVOS</div>
				</div>
			</a>
		</div>

		<div id="card-relatorio" class="card dropdown">
			<a class="dropdown-toggle" href="#" data-toggle="dropdown">
				<div class="card-content">
					<div class="card-icon">
						<i class="fa fa-file-text"></i>
					</div>
					<div class="card-deion">RELATÓRIO</div>
				</div>
			</a>

			<ul class="dropdown-menu">
				<li><a href="<c:url value="/servidor/relatorioVisitas/${selecao.id}"></c:url>">Visitas</a></li>
				<li><a href="<c:url value="/coordenador/comissao/relatorioFinal/${selecao.id}"></c:url>">Final</a></li>
			</ul>
		</div>

		<div id="card-rank" class="card">
			<a
				href="<c:url value="/selecao/selecionarClassificados/${selecao.id}"></c:url>">
				<div class="card-content">
					<div class="card-icon">
						<i class="fa fa-signal"></i>
					</div>
					<div class="card-deion">RANKING</div>
				</div>
			</a>
		</div>
	</div>
</div>


<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Detalhes da Seleção</h3>
	</div>
	<div class="panel-body center-h">
		<div class='f-container s1'>
			<label class='f-title'>Edital:</label>
			<div class='f-content'>${selecao.sequencial}/${selecao.ano}</div>
		</div>

		<div class='f-container s2'>
			<label class='f-title'>Seleção:</label>
			<div class='f-content'>${selecao.tipoSelecao.nome}</div>
		</div>

		<div class='f-container s2-half'>
			<label class='f-title'>Inscrições:</label>
			<div class='f-content'>
				<fmt:formatDate value="${selecao.dataInicio}" pattern="dd/MM/yyyy" />
				até
				<fmt:formatDate value="${selecao.dataTermino}" pattern="dd/MM/yyyy" />
			</div>
		</div>

		<div class='f-container s1'>
			<label class='f-title'>Vagas:</label>
			<div class='f-content'>${selecao.quantidadeVagas}</div>
		</div>

		<div class='f-container s1'>
			<label class='f-title'>Inscritos:</label>
			<div class='f-content'>${selecao.inscritos.size()}</div>
		</div>

		<div class='f-container s3'>
			<label class='f-title'>Responsável:</label>
			<div class='f-content'>${selecao.responsavel.pessoa.nome}</div>
		</div>
	</div>
</div>