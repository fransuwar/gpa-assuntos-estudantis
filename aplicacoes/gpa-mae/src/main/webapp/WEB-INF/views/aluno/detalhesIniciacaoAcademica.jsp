<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Detalhes Inscrição Iniciação Acadêmica</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container" align="left"">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Detalhes da Inscrição de Iniciação
					Acadêmica</h3>
			</div>
			<div class="panel-body">
				<dl class="col-sm-12">
					<dt class="col-sm-3">Data da Inscrição:</dt>
					<dd class="col-sm-3">
						<fmt:formatDate value="${inscricao.data}" pattern="dd/MM/yyyy" />
					</dd>
					<dt class=" col-sm-3">Tipo da Seleção:</dt>
					<dd class="col-sm-3">${inscricao.selecao.tipoSelecao.nome}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Resultado:</dt>
					<dd class="col-sm-3">${inscricao.resultado.nome}</dd>
					<dt class="col-sm-3">Observações:</dt>
					<dd class="col-sm-3">${inscricao.observacoes}</dd>

				</dl>
			</div>
			<div class="panel-heading">
				<h3 class="panel-title">Endereço de Residência de Origem</h3>
			</div>
			<div class="panel-body">
				<dl class="col-sm-12">
					<dt class="col-sm-3">Rua/Av:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.enderecoFamilia}</dd>
					<dt class="col-sm-3">Bairo de Origem:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.bairroFamilia}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Número:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.numeroFamilia}</dd>
					<dt class="col-sm-3">Cidade de Origem:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.cidadeFamilia}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Complemento:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.complementoFamilia}</dd>
					<dt class="col-sm-3">CEP:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.cepFamilia}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">UF:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.estadoFamilia}</dd>
					<dt class="col-sm-3">Referencia:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.referenciaFamilia}</dd>
				</dl>
			</div>
			<div class="panel-heading">
				<h3 class="panel-title">Endereço Atual</h3>
			</div>
			<div class="panel-body">
				<dl class="col-sm-12">
					<dt class="col-sm-3">Rua/Av:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.endereco}</dd>
					<dt class="col-sm-3">Bairo de Origem:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.bairro}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Número:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.numero}</dd>
					<dt class="col-sm-3">Cidade de Origem:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.cidade}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Complemento:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.complemento}</dd>
					<dt class="col-sm-3">CEP:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.cep}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">UF:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.estado}</dd>
					<dt class="col-sm-3">Referencia:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.referencia}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Fone:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.telefoneFixo}</dd>
					<dt class="col-sm-3">Celular:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.telefoneCelular}</dd>
				</dl>
			</div>
			<div class="panel-heading">
				<h3 class="panel-title">Situação Sócio Econômica</h3>
			</div>
			<div class="panel-body">
				<dl class="col-sm-12">
					<dt class="col-sm-3">Com Quem Reside Atualmente:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.comQuemReside}</dd>
					<dt class="col-sm-3">Qual a situação da sua residencia:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.situacaoResidencia}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Como define a localidade na qual vive
						atualmente?</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.tipoResidencia}</dd>
					<dt class="col-sm-3">Aparelho de Som:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdAparelhoSom}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Televisão:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdTelevisao}</dd>
					<dt class="col-sm-3">Rádio:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdRadio}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Automóvel:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdAutomovel}</dd>
					<dt class="col-sm-3">Motocicleta:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdMotocicleta}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Empregados Domésticos:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdEmpregadosDomesticos}</dd>
					<dt class="col-sm-3">Máquina de Lavar:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdMaquinaLavar}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Geladeira:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdGeladeira}</dd>
					<dt class="col-sm-3">Freezer:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdFreezer}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Telefone Fixo:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdTelefoneFixo}</dd>
					<dt class="col-sm-3">Celular Residentes:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdCelular}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Computador:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdComputador}</dd>
					<dt class="col-sm-3">Fogão a Gás:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdFogaoGas}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Banheiros:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdBanheiros}</dd>
					<dt class="col-sm-3">DVD:</dt>
					<dd class="col-sm-3">${inscricao.questionarioIniciacaoAcademica.qtdDvd}</dd>
				</dl>

			</div>
			<div class="panel-heading">
				<h3 class="panel-title">Informações do seu Grupo Familiar</h3>
			</div>
			<div class="panel-body">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Nome:</th>
							<th>Parentesco:</th>
							<th>Escolaridade:</th>
							<th>Atividade:</th>
							<th>Renda R$:</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pessoa"
							items="${inscricao.questionarioIniciacaoAcademica.pessoas }">
							<tr>
								<td>${pessoa.nome }</td>
								<td>${pessoa.parentesco.nome }</td>
								<td>${pessoa.escolaridade }</td>
								<td>${pessoa.profissao}</td>
								<td>${pessoa.rendaMensal }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="panel-heading">
				<h3 class="panel-title">Informações Adicionais</h3>
			</div>
			<div class="panel-body">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Dia:</th>
							<th>Turno:</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="horarios"
							items="${inscricao.questionarioIniciacaoAcademica.horariosDisponiveisSelecao }">
							<tr>
								<td>${horarios.dia.nome }</td>
								<td>${horarios.turno.nome }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="panel-heading">
				<h3 class="panel-title">Informações Adicionais</h3>
			</div>
			<div class="panel-body">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Dia:</th>
							<th>Turno:</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="horarios"
							items="${inscricao.questionarioIniciacaoAcademica.horariosDisponiveisSelecao }">
							<tr>
								<td>${horarios.dia.nome }</td>
								<td>${horarios.turno.nome }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="panel-heading">
				<h3 class="panel-title">Entrevista</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<div class="form-group">
						<dl class="col-sm-12">
							<dt class="col-sm-3">Observação:</dt>
							<dd class="col-sm-3">${inscricao.entrevista.observacao}</dd>
							<dt class="col-sm-3">Deferimento:</dt>
							<c:choose>
								<c:when test="${inscricao.entrevista.deferimento == true}">
									<dd class="col-sm-3">DEFERIDO</dd>
								</c:when>
								<c:otherwise>
									<dd class="col-sm-3">INDEFERIDO</dd>
								</c:otherwise>
							</c:choose>
							<dd class="col-sm-3">${inscricao.entrevista.observacao}</dd>
						</dl>
					</div>
				</div>
			</div>

		</div>

		<a id="detalhes"
			href="<c:url value="/servidor/detalhes/${inscricao.selecao.id}" ></c:url>">
			<button class="btn btn-primary btn-sm">Voltar</button>
		</a>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>