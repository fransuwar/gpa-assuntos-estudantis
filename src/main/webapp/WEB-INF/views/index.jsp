<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en">

<jsp:include page="fragments/headTag.jsp"/>
<link href="<c:url value="/resources/css/npi.css" />" rel="stylesheet" />

<body>
<div id="container" style="width: 1000px; margin: 0 auto;">
	
		<jsp:include page="fragments/bodyHeader.jsp" />
		
		<div class="jumbotron">
		  <h1>Bem vindo ao GPA!</h1>
		  <p>Sistema de Gestão  de Programas Acadâmicos</p>
		</div>
		
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>