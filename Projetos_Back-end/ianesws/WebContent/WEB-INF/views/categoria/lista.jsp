<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/app/adm/categoria/editar" var="urlEditarCategoria" />
<c:url value="/app/adm/categoria/nova" var="urlNovaCategoria" />
<c:url value="/app" var="raizApp" />
<c:url value="/sair" var="urlSair" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="../templates/header.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-81">
</head>
<body>
	<ul class="top-menu-items aln-items-center no-list txt-aln-r">
		<li>${usuarioAutenticado.nome}</li>
		<li><a href="${urlSair}">Sair</a></li>
	</ul>
	<c:choose>
		<c:when test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR'}">
			<a href="${urlNovaCategoria}"
				class="btn btn-blue d-block ma-l-auto ma-t-s"
				style="max-width: 240px"> Nova categoria</a>
		</c:when>
		<c:otherwise>
			<a href="${raizApp}">Volte para home</a>
		</c:otherwise>
	</c:choose>
	<h2>Categorias Cadastradas</h2>
	<table id="categorias" class="table container read-container ma-t-l">
		<thead>
			<tr>
				<th>Categorias</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${categorias}" var="categoria">
				<tr>
					<td><a href="${urlEditarCategoria}?id=${categoria.id}">${categoria.nome}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>