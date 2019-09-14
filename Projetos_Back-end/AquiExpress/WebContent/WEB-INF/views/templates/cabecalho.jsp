<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- URL para o nome do projeto --%>
<c:url value="/" var="raiz"></c:url>

<%-- Mostrando links alternativos dependendo se o usuário está logado ou não --%>

<c:choose>

<%-- Caso não esteja --%>
<c:when test="${ empty usuarioLogado }">
		
			<a href="${ raiz }entrar">Login</a> <br>
	    	<a href="${ raiz }usuario/novo">Cadastrar</a> <br>
		</c:when>
		<%-- Caso alguém esteja logado --%>
		<c:otherwise>
		
		${usuarioLogado.nome }
			<a href="${ raiz }sair">Sair</a> <br>
		
		</c:otherwise>
	
	</c:choose>
    
	
		
		