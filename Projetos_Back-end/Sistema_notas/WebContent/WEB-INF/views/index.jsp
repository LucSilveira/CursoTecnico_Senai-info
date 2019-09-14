<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Taglib - Biblioteca com resursos adicionais que podemos utilizar no JSP -->
<!-- ↑↑↑↑↑   ----      Biblioteca de Tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Criando URLs -->
<c:url value="/notas/nova" var="urlNovaNota"/>
<c:url value="/notas" var="urlNotas"/>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="templates/head.jsp"/>
</head>
<body>
		<header>
			<ul>
				<li><a href="${urlNovaNota }">Nova nota</a></li>
				<li><a href="${ulrNotas }">Visualizar notas</a></li>
			</ul>
		</header>
		<h1>Bem vindo ao Notas M1l Gr4u</h1>
</body>
</html>