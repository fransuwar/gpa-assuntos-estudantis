<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/bodyHeader.jsp" />
<title>Relatório final</title>

</head>
<body>
	<jsp:include page="../fragments/headTag.jsp" />
	<div class='container'>

		<div class="panel panel-primary" align="left">
			<div class="panel-heading">
				<h3 class="panel-title">Inscrições</h3>
			</div>

			<br />
			<div class="panel-body">

				<ul class="nav nav-tabs">
					<li id="aba-classificados" class="active"><a
						href="#classificados-tab" data-toggle="tab">Classificados<i
							class="fa"></i>
					</a></li>
					<li id="aba-reservas"><a href="#reservas-tab"
						data-toggle="tab">Reservas <i class="fa"></i>
					</a></li>
					<li id="aba-indeferidos"><a href="#indeferidos-tab"
						data-toggle="tab">Indeferidos <i class="fa"></i>
					</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="classificados-tab">

						<table class="display full-width" id="resultadoFinalTableClassificados">
							<thead>
								<tr>
									<td class='dt-head-center'><b>Aluno</b></td>
									<td class='dt-head-center'><b>Matricula</b></td>
									<td class='dt-head-center'><b>Resultado</b></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="inscricao" items="${classificados}">
									<tr>
										<td class='dt-body-center'>${inscricao.aluno.pessoa.nome}</td>
										<td class='dt-body-center'>${inscricao.aluno.matricula}</td>
										<td class='dt-body-center'>Classificado</td>
									</tr>
								</c:forEach>


							</tbody>

						</table>

						<br /> <br />
						<div id="buttons-container1"></div>


					</div>


					<div class="tab-pane" id="reservas-tab">
						<table class="display full-width" id="resultadoFinalTableReservas">
							<thead>
								<tr>
									<td class='dt-head-center'><b>Aluno</b></td>
									<td class='dt-head-center'><b>Matricula</b></td>
									<td class='dt-head-center'><b>Resultado</b></td>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="inscricao" items="${reservas}">
									<tr>
										<td class='dt-body-center'>${inscricao.aluno.pessoa.nome}</td>
										<td class='dt-body-center'>${inscricao.aluno.matricula}</td>
										<td class='dt-body-center'>Reserva</td>
									</tr>
								</c:forEach>

							</tbody>

						</table>

						<br /> <br />
						<div id="buttons-container2"></div>

					</div>


					<div class="tab-pane" id="indeferidos-tab">


						<table class="display full-width" id="resultadoFinalTableIndeferidos">
							<thead>
								<tr>
									<td class='dt-head-center'><b>Aluno</b></td>
									<td class='dt-head-center'><b>Matricula</b></td>
									<td class='dt-head-center'><b>Resultado</b></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="inscricao" items="${indeferidos}">
									<tr>
										<td class='dt-body-center'>${inscricao.aluno.pessoa.nome}</td>
										<td class='dt-body-center'>${inscricao.aluno.matricula}</td>
										<td class='dt-body-center'>Indeferido</td>
									</tr>
								</c:forEach>
							</tbody>

						</table>


						<br /> <br />
						<div id="buttons-container3"></div>

					</div>

				</div>

			</div>

		</div>

		<jsp:include page="../fragments/footer.jsp" />
</body>
</html>