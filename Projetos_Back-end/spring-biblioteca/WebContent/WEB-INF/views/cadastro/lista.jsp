<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
   <c:url value="/app/categoria/deletar" var="urlDeletarCategoria"/>
   <c:url value="/app/cadastro" var="urlTelaCadastro"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<h1>autenticado</h1>
		
		<a href="/spring-biblioteca/sair">Sair</a>
		
		<h2>Categorias</h2>
		<%-- Desenhando a table para listar as categorias --%>
		<table>
			<thead>
				<tr>
					<th>Nome</th>
					<th>Ações</th>
				</tr>
			</thead>			
			<tbody>
				<%-- Gerar o conteudo para listar as categorias --%>
				<c:forEach items="${categorias}" var="categoria">
					<tr>
					<c:url value="/app/categoria/deletar" var="urlDeletarCategoria"/>
						<td>${categoria.nome }</td>
						<td>
							<a href="${urlDeletarCategoria }?id=${categoria.id}">Remover</a>
						</td>
						<td>
							<a href="${urlTelaCadastro}?id=${categoria.id}">Alterar</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>