<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url value="/sair" var="urlSair" />
<c:url value="/app/adm/usuario" var="urlListaUsuario" />
<c:url value="/app/ambiente" var="urlListaAmbiente" />
<c:url value="/app/adm/categoria" var="urlListaCategoria" />
<c:url value="/app/patrimonio" var="urlListaPatrimonio" />
<c:url value="/app/movimentacao/salvar" var="urlSalvarMovimentacao" />
<c:url value="/app/adm/usuario" var="urlVisualizarUsuario"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="../templates/header.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<li class="nav-item"><a class="nav-link" href="${urlSair}" id="nav-item">Sair</a></li>
	
	<h1>Movimentação</h1>

	<form:form modelAttribute="movimentacao" action="${urlSalvarMovimentacao}"
		method="post" class="grid-flex">
		<form:hidden path="id" />
		<form:hidden path="identificacao.identificacao" value="${item.identificacao}" />
		<form:hidden path="origem.id" value="${identificacao.ambiente.id}" />
		
		<div class="row">
			<div class="col flex-1">
			
				<form:select path="destino.id" items="${ambientes}" itemValue="id"
					itemLabel="nome" class="custom-select"></form:select>
			</div>
		</div>
		<div class="row btn-group">
			<button type="submit" class="btn btn-outline-success" id="btnMovimentar">MOVIMENTAR</button>
		</div>
	</form:form>

	<h1 class="fx-slide-in">Lista Movimentações</h1>

	<table id="tabelaPatrimonios" class="table container read-container">
		<thead>
			<tr>
				<th>ID</th>
				<th>Item de Patrimônio</th>
				<th>Usuário</th>
				<th>Data da movimentação</th>
				<th>Ambiente de Origem</th>
				<th>Ambiente de Destino</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${movimentacoes}" var="movimentacao">
				<tr>
					<td>
						<p class="movimentacao-id">${movimentacao.id}</p>
					</td>
					<td>
						<h5>${movimentacao.identificacao.associado.nome}</h5>
					</td>
					<td>
					<!-- item.patrimonio.usuario.nome -->
						<p>${movimentacao.movimentou.nome}</p>
					<td>
						<p><fmt:formatDate value="${movimentacao.data_movimentacao}"
								pattern="dd/MM/yyyy" /></p>
					</td>
					<td>
						<p>${movimentacao.origem.nome}</p>
					</td>
					<td>
						<p>${movimentacao.destino.nome}</p>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>