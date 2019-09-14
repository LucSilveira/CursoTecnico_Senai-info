<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/adm/usuario/editar" var="urlEditarUsuario" />
<c:url value="/app/adm/usuario/novo" var="urlNovoUsuario" />


<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
</head>
<body>
	<c:import url="../templates/header.jsp"/>
	<main class="container">
		<a href="${urlNovoUsuario}" class="btn btn-blue d-block ma-l-auto ma-t-s" style="max-width: 240px"> Novo usu�rio</a>
		<h1>Usu�rios</h1>
		<section id="sectionCategorias">
			<h2>Usu�rios do sistema</h2>
			<div class="card-container">
				<c:forEach items="${usuarios}" var="usuario">
	
					<a class="card" href="${urlEditarUsuario}?id=${usuario.id}">
						<div class="card-header">${usuario.nome} ${usuario.sobrenome}</div>
						<div class="card-main">
							<img class="card-cover" alt="Foto do usu�rio" src="${assets}/fotos/foto_${usuario.id}">
						</div>
					</a>
				</c:forEach>
			</div>
		</section>
	</main>
	<c:import url="../templates/botoesFlutuantes.jsp" />
</body>
</html>