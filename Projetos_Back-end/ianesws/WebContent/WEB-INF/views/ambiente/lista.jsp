<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/" var="raiz" />
<c:url value="/app" var="raizApp" />
<c:url value="/assets" var="assets" />
<c:url value="/app/adm/ambiente/editar" var="urlEditarAmbiente" />
<c:url value="/app/adm/ambiente/novo" var="urlNovoAmbiente" />
<c:url value="/sair" var="urlSair" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="../templates/header.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<ul class="top-menu-items aln-items-center no-list txt-aln-r">
		<li>${usuarioAutenticado.nome}</li>
		<li><a href="${urlSair}">Sair</a></li>
	</ul>
	<c:choose>
		<c:when test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR'}">
			<a href="${urlNovoAmbiente}"
				class="btn btn-blue d-block ma-l-auto ma-t-s"> Novo ambiente</a>
		</c:when>
		<c:otherwise>
			<a href="${raizApp}">Volte para home</a>
		</c:otherwise>
	</c:choose>

	<h1>Ambientes</h1>
	<h2>Ambientes do sistema</h2>
	<div class="card-container">
		<c:forEach items="${ambientes}" var="ambiente">
			<a class="card" href="${urlEditarAmbiente}?id=${ambiente.id}">
				<div class="card-header">${ambiente.nome}</div>
			</a>
		</c:forEach>
	</div>
</body>
</html>