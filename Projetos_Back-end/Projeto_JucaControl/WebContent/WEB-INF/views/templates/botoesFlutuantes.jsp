<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="raiz" />

<c:url value="/assets" var="assets" />
<c:url value="/app/" var="urlVisualizarOcorrencias" />
<c:url value="/app/adm/categoria" var="urlVisualizarCategorias" />
<c:url value="/app/adm/usuario" var="urlVisualizarUsuarios" />

<%--Bot�es de gerenciamento --%>
<div class="float-btn-group">
	<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR'}">
		<a title="Usu�rios do sistema" class="btn" href="${urlVisualizarUsuarios}">
			<img alt="Usu�rios do sistema" src="${assets}/images/user.png">
		</a>
		<a title="Categorias de ocorr�ncias" class="btn" href="${urlVisualizarCategorias}">
			<img alt="Categorias de ocorr�ncias" src="${assets}/images/label.png">
		</a>
	</c:if>
	<a title="Visualizar ocorr�ncias" class="btn" href="${urlVisualizarOcorrencias}">
		<img alt="Abrir nova ocorr�ncia" src="${assets}/images/ocorrencia.png">
	</a>
</div>