<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"></c:import>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
	<c:import url="../templates/head.jsp" />
</head>
<body>
<c:if test="${not empty erros }">
	<div style="background-color: red">
		<c:forEach items="${erros}" var="erro">
			<p>${erro}</p>
		</c:forEach>
	</div>
</c:if>
	<form method="post" action="/spring-biblioteca/autenticar">
		<label>
		Email
		<input type="email" name="email">
		</label><br/>
		<label>
		Senha
		<input type="password" name="senha">
		</label><br/>
		<button type="submit">Login</button>
	</form>
</body>
</html>