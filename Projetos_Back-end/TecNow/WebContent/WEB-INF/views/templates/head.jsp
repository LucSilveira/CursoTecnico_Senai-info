<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/assets" var="assets" />
<c:url value="/assets/css" var="cssRoot" />
<c:url value="/assets/js" var="jsRoot" />
<link rel="stylesheet" href="${cssRoot}/edit.css"/>
<link rel="stylesheet" href="${cssRoot}/materialize.min.css" />
<script src="${jsRoot}/jquery-3.2.1.min.js"></script>
<script src="${jsRoot}/materialize.min.js"></script>

<head>
	<title>TecNow</title>
</head>
<body>
<img src="${assets}/jogos/games.png" style="width: 205px;margin-left: 98em;"/>
<img src="${assets}/jogos/ff.png" style="width: 300px;margin-left: -1em;margin-top: 18em;"/>
<h1 style="margin-top: -881px;margin-left: 7em;">Bem vindo ao sistema TecNow!</h1>
</body>