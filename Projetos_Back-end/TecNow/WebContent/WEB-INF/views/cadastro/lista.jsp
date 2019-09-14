<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
   <c:url value="/app/jogo/deletar" var="urlDeletarJogo"/>
   <c:url value="/app/cadastro" var="urlTelaCadastro"/>
<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head2.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista</title>
</head>
<body>
	<h1>autenticado</h1>
	
	<a href="/TecNow/sair">Logout</a>
	<a href="/TecNow/app/cadastro">Novo jogo</a>
	<h2>Jogos</h2>
	<%-- Desenhando a table para listar as categorias --%>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Categoria</th>
				<th>Cadastro</th>
				<th>Acões</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${jogos}" var="jogo">
				<tr>
					<c:url value="/app/jogo/deletar" var="urlDeletarJogo"/>
					<td>${jogo.id}</td>
					<td>${jogo.nome}</td>
					<td>${jogo.categorias}</td>
					<td>${jogo.dataCadastro}</td>
					<td>
						<a href="${urlDeletarJogo}?id=${jogo.id}">Excluir</a>
					</td>
					<td>
						<a href="${urlTelaCadastro}?id=${jogo.id}">Alterar</a>
					</td>
				</tr>	
			</c:forEach>
		</tbody>
	</table>
</body>
</html>