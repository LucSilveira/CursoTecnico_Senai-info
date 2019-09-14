<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<c:import url="../templates/cabecalho.jsp"></c:import>
	<h1>Cadastro de categoria</h1>
	
	<c:url value="/app/jogo/salvar" var="salvarJogo"/>
	<form action="${salvarJogo }" method="post">
	<%-- campo escondido para guardar o id --%>
	<input type="hidden" value="${jogo.id }" name="id">
		<label>
		Nome:
			<input type="text" name="nome" value="${jogo.nome }">
		</label>
		
		<label>
			categoria
				<input type="radio" value="TIRO" name="categorias"/>Tiro
				<input type="radio" value="RPG" name="categorias"/>Rpg
				<input type="radio" value="PLATAFORMA" name="categorias"/>Plataforma
				<input type="radio" value="ESPORTE" name="categorias"/>Esporte
				<input type="radio" value="HACK_AND_SLASH" name="categorias"/>Hack_and_slash
				<input type="radio" value="OUTROS" name="categorias"/>Outros
		</label>
		<button class="waves-effect waves-light btn" type="submit">Salvar</button>
	</form>
</body>
</html>