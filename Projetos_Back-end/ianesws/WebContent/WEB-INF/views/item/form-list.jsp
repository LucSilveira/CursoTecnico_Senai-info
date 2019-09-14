<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url value="/app" var="raizApp" />
<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/adm/item/deletar" var="urlItemDeletar" />
<c:url value="/app/item/salvar" var="urlSalvarItem" />
<c:url value="/sair" var="urlSair" />
<c:url value="/app/movimentacao" var="urlVisuMovimentacao" />
<c:url value="/app/adm/usuario" var="urlListaUsuario" />
<c:url value="/app/ambiente" var="urlListaAmbiente" />
<c:url value="/app/adm/categoria" var="urlListaCategoria" />
<c:url value="/app/patrimonio" var="urlListaPatrimonio" />
<c:url value="/app/adm/usuario" var="urlVisualizarUsuario"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="../templates/header.jsp" />
</head>
<body>
		<li class="nav-item"><a class="nav-link" href="${urlSair}"
			id="nav-item">Sair</a></li>

	<form:form modelAttribute="item" action="${urlSalvarItem}"
		method="post" class="grid-flex">
		
		<form:hidden path="identificacao" value="${item.identificacao}" />
		<form:hidden path="associado.id" value="${patrimonio.id}" />
		
		<div class="row">
			<div class="col flex-1">
				<%--Criando um select com spring/form--%>
				<form:select path="localizacao.id" items="${ambientes}" itemValue="id"
					itemLabel="nome"></form:select>
			</div>
		</div>
		
		
		<div class="row btn-group">
			<button type="submit" class="btn btn-blue col flex-1">SALVAR</button>
		</div>
	
	</form:form>
<!-- ------------------------------------------------------------------------------------------------------------------- -->
	<h1 class="fx-slide-in">Lista de Itens</h1>

	<table id="tabelaPatrimonios" class="table container read-container">
		<thead>
			<tr>
				<th>ID</th>
				<th>Patrimônio</th>
				<th>Usuário</th>
				<th>Categoria</th>
				<th>Ambiente</th>
				<th>foto</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${itens}" var="item">
				<tr>
					<td>
						<p class="item-id">${item.identificacao}</p>
					</td>
					<td>
					<a href="${urlVisuMovimentacao}?id=${item.identificacao}">
						<h3>${item.associado.nome}</h3>
					</a>
					<td>
						<p>${item.cadastro.nome}</p>
					<td>
						<p>${item.associado.obtem.nome}</p>
					</td>
					<td>
						<p>${item.localizacao.nome}</p>
					</td>
					<td>
						<p>${item.associado.caminhoFoto}</p>
					</td>
					<td><a href="${urlItemDeletar}?id=${item.identificacao}"
						style="color: red">DELETAR</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>