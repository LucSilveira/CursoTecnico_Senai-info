<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/assets/img" var="raiz" />
<c:url value="/assets/css" var="cssRaiz" />
<c:url value="/assets/js" var="jsRaiz" />
<c:url value="/usuario/autenticar" var="urlAutenticarUsuario" />

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${cssRaiz}/home.css"/>
		<link href="${cssRaiz}/bootstrap.min.css" rel="stylesheet">
	    <script src="${jsRaiz }/bootstrap.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Ianes</title>
	</head>
	<body id="body">
		<img id="Ola" src="${raiz}/SEMAI02.jpg" alt="Logo Senai" >
		<img id="tchau" src="${raiz}/logo.png" alt="Logo Senai" >
    	<h1 id="h1" style="margin-top: 10%;margin-left: 59.8%;font-size: 484%;">Entre em nosso site!</h1>
		<div>
			<form:form modelAttribute="usuario" action="${urlAutenticarUsuario}" method="post"
				id="FormEntra" style="margin-left: 70.5%;margin-top: 26%;">
				<div class="form-group" id="Email">
					<label for="exampleInputEmail1">
						<form:input path="email" type="email" required="required" maxlength="120" id="inputEmail" class="form-control"
						aria-describedby="emailHelp" placeholder="Email:" style="width: 10em; font-size: 1em; text-align: center;"/>
					</label>
				</div>
				<div class="form-group" id="passwrd">
					<label for="exampleInputPassword1"><!--     font-size: 34px;    height: 64px; -->
						<form:input path="senha" type="password" required="required" maxlength="20" class="form-control"
						id="exampleInputPassword1" placeholder="Senha:" style="width: 11em; font-size: 1em; text-align: center; margin-top:1em;"/>
					</label>
				</div>
				<button id="Entrar" type="submit" class="btn btn-primary" style="margin-left: 0em;width: 44%;height: 44px;">ENTRAR</button>
			</form:form>
		</div>	
	</body>
</html>