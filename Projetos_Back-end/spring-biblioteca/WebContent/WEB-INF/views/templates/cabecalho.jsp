<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    
    <c:url value="/" var="raiz"/>
    
    <c:choose>
    	<c:when test="${empty usuarioLogado }">
    		<a href="${raiz }entrar">Login</a><br/>
			<a href="${raiz }usuario/novo">Cadastar</a>
    	</c:when>
    	
    	<c:otherwise>
    		<a href="${raiz }app/cadastro">Nova categoria/livro</a><br/>
			<a href="${raiz }sair">Logout</a>
    	</c:otherwise>
    	    </c:choose>