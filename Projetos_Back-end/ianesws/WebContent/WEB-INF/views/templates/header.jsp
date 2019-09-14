<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/" var="raiz" />
<c:url value="/assets/css" var="cssRaiz" />
<c:url value="/assets/js" var="jsRaiz" />
<c:url value="/app/adm/usuario" var="urlUsuario" />
<c:url value="/app/adm/categoria" var="urlAlterar" />
<c:url value="/app/adm/ambiente/novo" var="urlAmbiente" />
<c:url value="/app/adm/categoria/nova" var="urlCategoria" />
<c:url value="/app/adm/patrimonio/novo" var="urlPatrimonio" />
<c:url value="/sair" var="urlSair" />
<c:url value="/app/adm/categoria" var="urlListaCategoria" />
<c:url value="/app/" var="urlListaPatrimonio" />
<c:url value="/app/ambiente" var="urlListaAmbiente" />
<c:url value="/app/adm/usuario" var="urlListaUsuario" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>Ianes Patrimônios</title>

<link href="${cssRaiz}/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${jsRaiz}/jquery-1.11.0.min.js"></script>
<!-- Custom Theme files -->
<link href="${cssRaiz}/style.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript">
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 


</script>
<header id="menuSuperior">
	<nav>
		<a href="${urlUsuario }">Usuario</a> <a href="${urlAmbiente }">Ambiente</a>
		<a href="${urlCategoria }">Categoria</a> <a href="${urlPatrimonio }">Patrimonio</a>

		<a href="${urlListaPatrimonio }">Lista-Patrimonio</a> <a
			href="${urlListaCategoria }">Lista-Categoria</a> <a
			href="${urlListaAmbiente }">Lista-Ambiente</a> <a href="${urlSair}">Sair</a>
	</nav>
</header>


