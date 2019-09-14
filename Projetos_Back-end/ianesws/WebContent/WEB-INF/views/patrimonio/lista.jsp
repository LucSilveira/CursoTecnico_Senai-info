<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url value="/app" var="raizApp" />
<c:url value="/assets/img" var="raiz" />
<c:url value="/assets/css" var="cssRaiz" />
<c:url value="/assets/js" var="jsRaiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/adm/patrimonio/editar" var="urlEditarPatrimonio" />
<c:url value="/app/adm/patrimonio/novo" var="urlNovoPatrimonio" />
<c:url value="/app/item" var="urlItem" />
<c:url value="/sair" var="urlSair" />

<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/header.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${cssRaiz}/pat.css" />
<link href="${cssRaiz}/bootstrap.min.css" rel="stylesheet">
<script src="${jsRaiz }/bootstrap.min.js"></script>
</head>
<body style="background: black;">
	<img id="Ola" src="${raiz}/pat.jpg" alt="Logo Senai" />
	<div id="formu"></div>
	<h1 class="fx-slide-in">Lista de Patrimônios</h1>

	<div class="card" style="width: 18rem;">
		<img id="cad" class="card-img-top" src="${raiz}/bloco.png"
			alt="Card image cap">
	</div>

		<table id="tabelaPatrimonios" class="table container read-container">
			<thead>
				<tr>
					<th>Patrimônios</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${patrimonios}" var="patrimonio">
					<tr>
						<td><c:choose>
								<c:when test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR'}">
									<p class="patrimonio-id">
										<a href="${urlEditarPatrimonio}?id=${patrimonio.id}">
											${patrimonio.id} </a>
									</p>
								</c:when>
								<c:otherwise>
									<p class="patrimonio-id">${patrimonio.id}</p>
								</c:otherwise>
							</c:choose> <!--  -->
							<h4>
								<a href="${urlItem}?id=${patrimonio.id}">${patrimonio.nome}</a>
							</h4> <!--  -->
							<p class="patrimonio-data">
								<b class="color-pink">Data de cadastro: </b>
								<fmt:formatDate value="${patrimonio.data_cadastro}"
									pattern="dd/MM/yyyy" />
							</p></td>
						<%--Quem criou o patrimônio/link de atendimento--%>
						<td>
							<p>${patrimonio.cadastrador.nome}</p>
						</td>
						<td>
							<p>${patrimonio.obtem.nome}</p>
						</td>
						<td><img class="card-cover" alt="Foto do pat"
							src="${assets}/fotos/foto_${patrimonio.id}" style="width: 50px">
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>