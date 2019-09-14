<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/notas/cadastrar" var="urlNotasCadastrar"/>   
 
<!DOCTYPE html>
<html>
<head>
	<c:import url="templates/head.jsp"/>
</head>
<body>
	<form method="post" action="${urlNotasCadastrar }">
		<label>
			Título
			<input type="text" name="titulo"/>
		</label>
		
		<br/>
		
		<textarea name="descricao" placeholder="Descrição da tarefa"></textarea>
		
		<br/>
		
		<label>
			Prioridade alta
			<input type="checkbox" name="prioridadeAlta"/>
		</label>
		
		<br/>
		
		<label>
			Data de conclusao
			<input type="text" name="dataDeConclusao"/>
		</label>
		
		<br/>
		
		<button type="submit">Salvar</button>
	
	</form>
</body>
</html>