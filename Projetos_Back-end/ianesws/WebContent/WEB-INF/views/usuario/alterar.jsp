

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/app/salvarSenha" var="urlSalvarSenha" />
<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/header.jsp" />
</head>
<body>

	<h1>Nova Senha</h1>
	<p>Para o cadastro de uma nova senha nós solicitamos que você
		confirme a senha antiga</p>
	<form action="${urlSalvarSenha}" method="post">

		<label> Nova Senha <input type="password" name="senhaNova">
		</label> <label> Senha Antiga <input type="password" name="senha">
		</label>

		<c:if test="${not empty erros}">
			<div
				style="background-color: #ffd2ce; margin-top: 10px; color: #992f26;
				width: 50%; text-align: center; border-radius: 2px; border-color: #992f26">
				<c:forEach items="${erros}" var="erro">
					<p>${erro}</p>
				</c:forEach>
			</div>
		</c:if>

		<button class="btn"type="submit">Salvar</button>
	</form>
</body>
</html>