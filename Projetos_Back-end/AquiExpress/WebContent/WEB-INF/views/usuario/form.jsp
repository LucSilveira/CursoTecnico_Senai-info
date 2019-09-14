<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Produtos</title>
</head>
<body>
	<form method="post" action="/AquiExpress/usuario/salvar">
	<label>
			Nome Completo
			<input type="text" name="nomeCompleto" maxlength="30">
		</label><br/>
		<label>
			Usuario
			<input type="text" name="usuario" maxlength="50">
		</label><br/>
		<label>
			Data de Nascimento
			<input type="date" name="dataNascimento">
		</label><br/>
		<label>
			E-mail
			<input type="email" name="email">
		</label><br/>
		<label>
			Senha
			<input type="password" name="senha" minlength="8">
		</label>
		<button type="submit">Salvar</button>
	</form>
</body>
</html>