<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<c:if test="${not empty erro}">
		<div class="alert alert-danger alert-dismissible">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<c:out value="${erro}"></c:out>
		</div>
	</c:if>
	<c:if test="${not empty info}">
		<div class="alert alert-success alert-dismissible">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<c:out value="${info}"></c:out>
		</div>
	</c:if>
</div>