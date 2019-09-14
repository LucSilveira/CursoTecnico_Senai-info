<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/spring-biblioteca/usuario/salvar">
		<label>
			Nome
			<input type="text" name="nome" maxlength="30">
		</label><br/>
		<label>
			Sobrenome
			<input type="text" name="sobrenome" maxlength="50">
		</label><br/>
		<label>
			Data de Nascimento
			<input type="date" name="dataNascimento">
		</label><br/>
		<label>
			Email
			<input type="email" name="email">
		</label><br/>
		<label>
			Senha
			<input type="password" name="senha" minlength="8">
		</label><br/>
		<button type="submit">Salvar</button>
	</form>
</body>
</html>