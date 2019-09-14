<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/adm/patrimonio/deletar" var="urlPatrimonioDeletar" />
<c:url value="/app/adm/patrimonio/salvar" var="urlSalvarPatrimonio" />
<c:url value="/sair" var="urlSair" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="../templates/header.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<ul class="top-menu-items aln-items-center no-list txt-aln-r">
		<li>${usuarioAutenticado.nome}</li>
		<li><a href="${urlSair}">Sair</a></li>
	</ul>
	<form:form modelAttribute="patrimonio" action="${urlSalvarPatrimonio}"
		method="post" class="grid-flex">
		<form:hidden path="id" />
		<div class="row">
			<div class="col flex-1">
				<label for="inputNome"> NOME </label>
				<form:input path="nome" id="inputNome" />
				<form:errors path="nome" element="div" cssClass="error" />
			</div>
		</div>
		<div class="row">
			<div class="col flex-1">
				<%--Criando um select com spring/form--%>
				<form:select path="obtem.id" items="${categorias}"
					itemValue="id" itemLabel="nome"></form:select>
			</div>
		</div>
		<div class="row btn-group">
			<button type="submit" class="btn btn-blue col flex-1">SALVAR</button>

			<c:if test="${not empty patrimonio.id}">
				<a href="${urlPatrimonioDeletar}?id=${patrimonio.id}"
					class="btn btn-red">DELETAR</a>
			</c:if>
		</div>
		<div class="row">
				<div class="col flex-1">
					<label for="inputFoto">Foto de Pat</label> <input type="file"
						name="fotoPAT" accept=".png, .jpg, .jpeg">
				</div>
			</div>
	</form:form>
</body>
</html>