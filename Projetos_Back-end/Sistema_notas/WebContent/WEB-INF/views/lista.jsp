<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:url value="/notas/deletar" var="urlDeletar"/>
 
<!DOCTYPE html>
<html>
<head>
	<c:import url="templates/head.jsp"/>
</head>
<body>
	<table>
			<thead>
				<tr>
					<th>ID</th>
					<br/>
					<th>Título</th>
					<br/>
					<th>Alta prioridade</th>
					<br/>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach 	items="${notas }" var="nota">
					<tr>
						<td>${nota.id}</td>
						<td>${nota.titulo}</td>
						<td>${nota.prioridadeAlta }</td>
						<td><a href="${urlDeletar}?id=${nota.id}">Deletar nota</a></td>
					</tr>
				</c:forEach>
			</tbody>
	</table>
</body>
</html>