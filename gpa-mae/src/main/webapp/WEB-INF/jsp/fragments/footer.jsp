<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<footer>
		<img id="logo-npi"
			alt="Núcleo de Práticas em Informática - Campus da UFC em Quixadá"
			src="<c:url value="/images/logo-npi.png" />">
		<p>
			Desenvolvido por <a href="http://www.npi.quixada.ufc.br" target="_blank">Núcleo de Práticas em Informática</a>
		</p>
		<p>
			<a href="http://www.quixada.ufc.br" target="_blank">Universidade Federal do Ceará - Campus Quixadá</a>
		</p>

	</footer>

<script src="<c:url value="/js/libs/jquery-1.11.3.min.js" />"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="<c:url value="/js/libs/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/js/libs/bootstrap-datepicker.pt-BR.js" />"></script>
<script src="<c:url value="/js/gpa/states-and-cities.js"/>"></script>
<script src="<c:url value="/js/libs/bootstrap.file-input.js" />"></script>
<script src="<c:url value="/js/libs/bootbox.min.js" />"></script>
<script src="<c:url value="/js/libs/bootstrapValidator.min.js" />"></script>
<script src="<c:url value="/js/libs/jquery.mask.js" />"></script>
<script src="<c:url value="/js/libs/jquery.maskMoney.js" />"></script>
<script src="<c:url value="/js/libs/list-dynamic.js" />"></script>

<script src="<c:url value="/js/libs/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/js/libs/jquery.dataTables.buttons.min.js" />"></script>
<script src="<c:url value="/js/libs/jquery.pdfmake.min.js" />"></script>
<script src="<c:url value="/js/libs/jquery.vfs_fonts.js" />"></script>
<script src="<c:url value="/js/libs/jquery.buttons.html5.min.js" />"></script>

<script src="<c:url value="/js/libs/jquery.validate.min.js" />"></script>
<script src="<c:url value="/js/libs/moment-with-locales.js"/>"></script>
<script src="<c:url value="/js/libs/jquery.steps.min.js"/>"></script>
<script src="<c:url value="/js/gpa/npi.js"/>"></script>
<script src="<c:url value="/js/gpa/funcoes_aluno.js"/>"></script>
<script src="<c:url value="/js/gpa/funcoes_coordenador.js"/>"></script>
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<input type="hidden" id="aba" value="${aba}"/>