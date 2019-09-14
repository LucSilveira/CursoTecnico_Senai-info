<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"></c:import>
</head>
<body>
	<c:import url="../templates/cabecalho.jsp"></c:import>
	<h1>Cadastro de categoria</h1>
	
	<c:url value="/app/categoria/salvar" var="salvarCategoria"/>
	<form action="${salvarCategoria }" method="post">
	<%-- campo escondido para guardar o id --%>
	<input type="hidden" value="${categoria.id }" name="id">
		<label>
			<input type="text" name="nome" value="${categoria.nome }">
		</label>
		<button type="submit">Salvar</button>
	</form>
</body>
</html>