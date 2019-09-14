<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app" var="urlOcorrencias" />
<c:url value="/app/ocorrencia" var="urlVisualizarOcorrencia" />
<c:url value="/app/ocorrencia/nova" var="urlNovaOcorrencia" />
<c:url value="/app/ocorrencia/assumir" var="urlAssumirOcorrencia" />
<c:url value="/app/ocorrencia/encerrar" var="urlEncerrarOcorrencia" />


<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
	<style>
	#tabelaOcorrencias img{
		background-image: linear-gradient(to left bottom, #2432cc, #cc29cc, #cc2525);
		width: 100px;
	}
	</style>
</head>
<body>
	<c:import url="../templates/header.jsp"/>
	<main class="container">
		<a href="${urlNovaOcorrencia}" class="btn btn-red d-block ma-l-auto ma-t-s" style="max-width: 220px"> Abrir ocorr�ncia</a>
		<h1 class="fx-slide-in">Ocorr�ncias</h1>
		<section id="sectionOcorrencias">
			<h2>Classificar por: </h2>
			<%--Filtros de busca --%>
			<form action="${urlOcorrencias}" method="get" class="flex-grid ma-b-l" style="max-width: 400px;">
				<div class="row">
				<div class="col flex-2">
					<select name="pesquisa">
						<c:forEach items="${pesquisas}" var="pesquisa">
							<option value="${pesquisa}">${pesquisa.descricao}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col flex-1">
					<button class="btn btn-blue" type="submit">Pesquisar</button>
				</div>
				</div>
			</form>
			
			<%-- Tabela de ocorr�ncias --%>
			<table id="tabelaOcorrencias" class="table container read-container">
				<thead>
					<tr>
						<th>#</th>
						<th>Ocorr�ncias</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ocorrencias}" var="ocorrencia">
						<tr>
							<%--- Sinalizador de status --%>
							<td></td>
							<%-- Descri��o da ocorr�ncia --%>
							<td>
								<p class="ocorrencia-id">
									<a href="${urlVisualizarOcorrencia}/editar?id=${ocorrencia.id}">
										${ocorrencia.id}
									</a>
								</p>
								<h4>${ocorrencia.titulo}</h4>
								<p class="ocorrencia-detalhe"><b class="color-pink">Data de abertura: </b>
									<fmt:formatDate value="${ocorrencia.dataCadastro}" pattern="dd/MM/yyyy hh:mm:ss"/>
								</p>
								<p class="ocorrencia-detalhe"><b class="color-pink">�ltima modifica��o: </b>
									<fmt:formatDate value="${ocorrencia.dataModificacao}" pattern="dd/MM/yyyy hh:mm:ss"/>
								</p>
								<p class="ocorrencia-detalhe"><b class="color-pink">Data de conclus�o: </b>
									<fmt:formatDate value="${ocorrencia.dataConclusaoEmissor}" pattern="dd/MM/yyyy hh:mm:ss"/>
								</p>
							</td>
							<%--Quem atendeu ocorrencia/link de atendimento--%>
							<td>

								<c:choose>
									<%-- Mostra o link de atender quando a ocorrencia n�o tem t�cnico --%>
									<c:when test="${empty ocorrencia.tecnico}">
										<a href="${urlAssumirOcorrencia}?id=${ocorrencia.id}">Assumir</a>
									</c:when>
									<%-- Mostra o link de encerrar do t�cnico quando 
									1� O usu�rio logado � o t�cnico que assumiu
									2� Quando a ocorr�ncia ainda n�o foi encerrada pelo t�cnico
									--%>
									<c:when 
									test="${ocorrencia.tecnico.id eq usuarioAutenticado.id 
									and empty ocorrencia.dataConclusaoTecnico}">
										<a href="${urlEncerrarOcorrencia}/tecnico?id=${ocorrencia.id}">
											T�CNICO - Encerrar ocorr�ncia
										</a>
									</c:when>
									<%-- Mostra o link de encerrar do emissor quando
									1� Deve ter sido concluida pelo t�cnico
									2� N�o deve ter sido concluida pelo emissor
									3� O emissor deve ser o usu�rio logado --%>
									<c:when 
									test="${not empty ocorrencia.dataConclusaoTecnico
									and empty ocorrencia.dataConclusaoEmissor
									and usuarioAutenticado.id eq ocorrencia.emissor.id}">
										<a href="${urlEncerrarOcorrencia}/emissor?id=${ocorrencia.id}">
											EMISSOR - Encerrar ocorr�ncia
										</a>
									</c:when>
								
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
	</main>
	<c:import url="../templates/botoesFlutuantes.jsp"/>
</body>
</html>