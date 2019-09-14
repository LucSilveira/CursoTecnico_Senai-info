<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="../templates/head2.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${not empty erros }">
	<div style="background-color: red">
		<c:forEach items="${erros}" var="erro">
			<p>${erro}</p>
		</c:forEach>
	</div>
</c:if>
	<form method="post" action="/TecNow/autenticar">
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