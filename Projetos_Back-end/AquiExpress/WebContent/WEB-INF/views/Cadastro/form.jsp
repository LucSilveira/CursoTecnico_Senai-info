<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../Templates/head.jsp"></c:import>
<body>

	<c:import url="../Templates/cabecalho.jsp"></c:import>
	
	<h1>Cadastro de produtos</h1>
	
	<c:url value="/app/produto/salvar" var="salvarProduto"></c:url>
	<form action="${salvarProduto }" method="post">
	
	<%-- Campo escondido para guardar o id --%>
	<input type="hidden" value="${ produto.id }" name="id">
	
	<label>
		Nome:
		<input type="text" name="nome" maxlength="30" value="${ produto.nome }">
	</label>
	
	<label>
		Preco:
		<input type = "number" placeholder = "1.0" step = "0.01" min = "0" name="preco" value="${produto.preco }">
	</label>
	
	<button type="submit">Salvar</button>
	
	</form>
	</body>
</html>