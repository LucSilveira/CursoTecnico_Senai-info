<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/sair" var="urlSair" />
<c:url value="/app/adm/categoria/deletar" var="urlDeletarCategoria" />
<c:url value="/app/adm/categoria/salvar" var="urlSalvarCategoria" />

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
	<h1>Categorias</h1>
	<c:choose>
		<c:when test="${empty categoria.id}">
			<h2>Nova Categoria</h2>
		</c:when>
		<c:otherwise>
			<h2>Editando categoria: ${categoria.nome}</h2>
		</c:otherwise>
	</c:choose>
	<form:form modelAttribute="categoria" action="${urlSalvarCategoria}"
		method="post">
		<form:hidden path="id" />
		<div class="flex-grid">
			<div class="row">
				<div class="col flex-1">
					<label> Nome <form:input path="nome" id="inputNome" /> <form:errors
							path="nome" element="div" cssClass="error" />
					</label>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<button type="submit" class="btn btn-blue">SALVAR</button>
				</div>
				<c:if test="${not empty categoria.id}">
					<div class="col">
						<a class="btn btn-red"
							href="${urlDeletarCategoria}?id=${categoria.id}">DELETAR</a>
					</div>
				</c:if>
			</div>
		</div>
	</form:form>
</body>
</html>