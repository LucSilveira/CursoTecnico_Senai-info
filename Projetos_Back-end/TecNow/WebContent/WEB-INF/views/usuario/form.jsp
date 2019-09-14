<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="../templates/head.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario</title>
</head>
<body>
	<form method="post" action="/TecNow/usuario/salvar">
		<label>
			Nome
			<input type="text" name="nome" maxlength="25">
		</label><br/>
		<label>
			Email
			<input type="email" name="email">
		</label><br/>
		<label>
			Senha
			<input type="password" name="senha" minlength="8" >
		</label><br/>
		<label>
			Data de Nascimento
			<input type="date" name="dataNascimento">
		</label><br/>
		<label>
			Sexo:
			<select name="sexo">
				<option value="FEMININO">Feminino</option>
				<option value="MASCULINO">Masculino</option>
			</select>
		</label>
		<button type="submit">Salvar</button>
	</form>
</body>
</html>