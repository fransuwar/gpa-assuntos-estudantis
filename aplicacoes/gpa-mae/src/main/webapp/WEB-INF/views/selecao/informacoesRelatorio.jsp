<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html >
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/bodyHeader.jsp" />

<title>Visualizar dados de Visita Domiciliar</title>
</head>


<body>
	<jsp:include page="../fragments/headTag.jsp" />

	<div class="col-md-7 col-md-offset-1 dist-footer" align="center">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h2 class="panel-title">Informações de Relatório de Visita
					Domiciliar</h2>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<dl class="dl-horizontal">
						<dt>Candidato:</dt>
						<dd>${aluno.nome}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Endereço:</dt>
						<!-- <dd></dd> -->
					</dl>
					<dl class="dl-horizontal">
						<dt>Forma de acesso a casa:</dt>
						<!-- <dd></dd> -->
					</dl>
					<dl class="dl-horizontal">
						<dt>Curso:</dt>
						<dd>${aluno.curso}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Semestre:</dt>
						<!-- <dd></dd> -->
					</dl>
					<dl class="dl-horizontal">
						<dt>Data da visita:</dt>
						<dd>${dataVisita}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Técnicos Responsáveis:</dt>
						<!-- <dd></dd> -->
					</dl>

				</div>
			</div>
		</div>

		<div class="panel panel-primary" align="center">
			<div class="panel-heading">
				<h3 class="panel-title">I - Perfil Econômico: Despesas</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<dl class="dl-horizontal">
						<dt>Moradia:</dt>
						<dd>${despesaMoradia}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Energia:</dt>
						<dd>${despesaEnergia}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Telefone:</dt>
						<dd>${despesaTelefone}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Educação:</dt>
						<dd>${despesaEducacao}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Saúde:</dt>
						<dd>${despesaSaude}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Alimentação:</dt>
						<dd>${despesaAlimentacao}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Outros:</dt>
						<dd>${despesaOutros}</dd>
					</dl>



				</div>
			</div>


			<div class="panel-heading">
				<h3 class="panel-title">I - Perfil Econômico: Receitas</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<dl>
						<dt>Nº de Pessoas residentes no domicílio:</dt>
						<dd>${qtdPessoasResidentes}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Trabalho Formal:</dt>
						<!-- <dd>${aluno.nome}</dd> -->
					</dl>
					<dl class="dl-horizontal">
						<dt>Trabalho Informal:</dt>
						<!-- <dd>${aluno.nome}</dd> -->
					</dl>
					<dl class="dl-horizontal">
						<dt>Aposentadoria:</dt>
						<!-- <dd>${aluno.nome}</dd> -->
					</dl>
					<dl class="dl-horizontal">
						<dt>Benefícios Sociais:</dt>
						<!-- <dd>${aluno.nome}</dd> -->
					</dl>
					<dl class="dl-horizontal">
						<dt>Auxílio de Parentes:</dt>
						<!-- <dd>${aluno.nome}</dd> -->
					</dl>
					<dl class="dl-horizontal">
						<dt>Outros:</dt>
						<!-- <dd>${aluno.nome}</dd> -->
					</dl>


				</div>
			</div>
		</div>

		<div class="panel panel-primary" align="center">
			<div class="panel-heading">
				<h3 class="panel-title">III - Moradia</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<table class="table">
						<thead>
							<tr class="info">
								<td>Item</td>
								<td>Quantidade</td>
								<td>Estado</td>
							</tr>
						</thead>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Varanda:</dt>
								</dl></td>
							<td>
								<!-- <dd>${varanda.moradiaqtd}</dd> -->
							</td>
							<td>${varanda.moradiaEstado}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Sala:</dt>
								</dl></td>
							<td>
								<!-- ${sala.moradiaqtd} -->
							</td>
							<td>${sala.moradiaEstado}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Banheiro:</dt>
								</dl></td>
							<td>
								<!-- ${banheiro.moradiaqtd} -->
							</td>
							<td>${banheiro.moradiaEstado}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Quarto:</dt>
								</dl></td>
							<td>
								<!-- ${quarto.moradiaqtd} -->
							</td>
							<td>${quarto.moradiaEstado}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Cozinha:</dt>
								</dl></td>
							<td>
								<!-- ${cozinha.moradiaqtd} -->
							</td>
							<td>${cozinha.moradiaEstado}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Quintal:</dt>
								</dl></td>
							<td>
								<!-- ${quintal.moradiaqtd} -->
							</td>
							<td>${quintal.moradiaEstado}</td>
						</tr>

					</table>
				</div>
			</div>

			<div class="panel-heading">
				<h3 class="panel-title">IV - Utensílios Domésticos (Eletro
					Eletrônicos)</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<table class="table">
						<thead>
							<tr class="info">
								<td>Item</td>
								<td>Quantidade</td>
								<td>Observação</td>
							</tr>
						</thead>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>TV:</dt>
								</dl></td>
							<td>${utensilioTvQtd}</td>
							<td>${utensilioTvObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Som:</dt>
								</dl></td>
							<td>${utensilioSomQtd}</td>
							<td>${utensilioSomObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Computador:</dt>
								</dl></td>
							<td>${utensilioComputadorQtd}</td>
							<td>${utensilioComputadorObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Fogão:</dt>
								</dl></td>
							<td>${utensilioFogaoQtd}</td>
							<td>${utensilioFogaoObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Geladeira:</dt>
								</dl></td>
							<td>${utensilioGeladeiraQtd}</td>
							<td>${utensilioGeladeiraObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Freezer:</dt>
								</dl></td>
							<td>${utensilioFreezerQtd}</td>
							<td>${utensilioFreezerObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Lavadora:</dt>
								</dl></td>
							<td>${utensilioLavadoraQtd}</td>
							<td>${utensilioLavadoraObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>DVD:</dt>
								</dl></td>
							<td>${utensilioDvdQtd}</td>
							<td>${utensilioDvdObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Outros:</dt>
								</dl></td>
							<td>${utensilioOutrosQtd}</td>
							<td>${utensilioOutrosObservacao}</td>
						</tr>
					</table>
				</div>
			</div>


		</div>

		<div class="panel panel-primary" align="center">
			<div class="panel-heading">
				<h3 class="panel-title">V - Bens Móveis</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<table class="table">
						<thead>
							<tr class="info">
								<td>Item</td>
								<td>Quantidade</td>
								<td>Observação</td>
							</tr>
						</thead>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Moto:</dt>
								</dl></td>
							<td>${bemMovelMotoQtd}</td>
							<td>${bemMovelMotoObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Bicicleta:</dt>
								</dl></td>
							<td>${bemMovelBicicletaQtd}</td>
							<td>${bemMovelBicicletaObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Carro:</dt>
								</dl></td>
							<td>${bemMovelCarroQtd}</td>
							<td>${bemMovelCarroObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Outros:</dt>
								</dl></td>
							<td>${bemMovelOutrosQtd}</td>
							<td>${bemMovelOutrosObservacao}</td>
						</tr>
					</table>
				</div>
			</div>



		</div>

		<div class="panel panel-primary" align="center">
			<div class="panel-heading">
				<h3 class="panel-title">VI - Síntese Perfil Econômico</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<table class="table">
						<thead>
							<tr class="info">
								<td>Item</td>
								<td>Compatível com a receita?</td>
							</tr>
						</thead>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Utensílios Doméstico (eletro eletrônicos):</dt>
								</dl></td>
							<td>${perfilCompativelUtensilioDomestico}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Bens Móveis:</dt>
								</dl></td>
							<td>${perfilCompativelBensMoveis}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Maquinário:</dt>
								</dl></td>
							<td>${perfilCompativelMaquinario}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Aspecto físico da residência:</dt>
								</dl></td>
							<td>${perfilCompativelAspectoFisicoResidencia}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Outros:</dt>
								</dl></td>
							<td>${perfilCompativelOutros}</td>
						</tr>

					</table>
				</div>
			</div>



		</div>


		<div class="panel panel-primary" align="center">
			<div class="panel-heading">
				<h3 class="panel-title">Análise e Descrição da Realidade/Perfil
					Psicossosial</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<dl>
						<dd>${analiseDescricaoRealidade}</dd>
					</dl>
				</div>
			</div>
			<div class="panel-heading">
				<h3 class="panel-title">Parecer Final</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<dl class="dl-horizontal">
						<dt>Deferido:</dt>
						<dd>${parecerFinalDeferido}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Data do Relatório:</dt>
						<dd>${dataRelatorio}</dd>
					</dl>
				</div>
			</div>
		</div>


	</div>


	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>