<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:url value="/app" var="raizApp" />
<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/assets/css" var="cssRaiz" />
<c:url value="/assets/js" var="jsRaiz" />
<c:url value="/app/adm/usuario/editar" var="urlEditarUsuario" />
<c:url value="/app/adm/usuario/novo" var="urlNovoUsuario" />
<c:url value="/sair" var="urlSair" />


<!DOCTYPE html>
<html>
<head>
<!-- link rel="stylesheet" type="text/css" href="${cssRaiz}/home.css"/-->
		<link href="${cssRaiz}/bootstrap.min.css" rel="stylesheet">
	    <script src="${jsRaiz }/bootstrap.min.js"></script>
	    <c:import url="../templates/header.jsp" />
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<link href="bootstrap-4.1.1-dist/css/bootstrap.min.css" rel="stylesheet">	  
  	<link href="bootstrap-4.1.1-dist/css/style.css" rel="stylesheet">
  	<script src="bootstrap-4.1.1-dist/js/jquery.js"></script>	 
  	<script src="bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>
  	<script src="bootstrap-4.1.1-dist/js/jquery.js"></script>
	<script src="bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>
	    
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<ul class="top-menu-items aln-items-center no-list txt-aln-r">
		<li>${usuarioAutenticado.nome}</li>
		<li><a href="${urlSair}">Sair</a></li>
	</ul>
	<main class="container">
	
	<!-- button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" style="
      margin-left: 46em;
    margin-top: 13em;">
  Editar o Usuário
</button-->

<!-- Modal -->
<!-- div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Edição</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
  		<h2>Editando o Usuário</h2>
  			<div class="form-row">

    			<div class="form-group col-md-6">
      					<input type="text" class="form-control" id="inputEmail4" placeholder="Nome">
    			</div>

    			<div class="form-group col-md-6">
      					<input type="text" class="form-control" id="inputEmail4" placeholder="Sobrenome">
    			</div>

    			<div class="form-group col-md-6">
      					<input type="email" class="form-control" id="inputEmail4" placeholder="Email">
    			</div>

			   <div class="form-group col-md-6">
      				<input type="password" class="form-control" id="inputPassword4" placeholder="Senha">
    		 </div>

			   <div class="form-group col-md-6">
      				<input type="password" class="form-control" id="inputPassword4" placeholder="Confirmar Senha">
      				
<hr class="my-4">
    		 </div>

  		</div>
  
  <div class="form-group">
    <div class="form-check">
      <input class="form-check-input" type="checkbox" id="gridCheck">
   	   <label class="form-check-label" for="gridCheck">
    	    Adm
      </label>
    </div>

    		 			<div class="row">
				<div class="col flex-1">
      <label for="inputFoto">Foto de Perfil</label>
					 <input type="file" class="btn btn-dark" name="foto" accept=".png, .jpg, .jpeg">
				</div>
			</div>

  <button type="submit" class="btn btn-primary">Excluir</button>
</div>
</form>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-primary">Editar</button>
      </div>
    </div>
  </div>
</div>

<div class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div-->
	<c:choose>
		<c:when test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR'}">
			<button href="${urlNovoUsuario}" type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#exampleModal">
			<a href="${urlNovoUsuario}"
				class="btn btn-blue d-block ma-l-auto ma-t-s"
				style="max-width: 240px"> Novo usuario</a></button>
		</c:when>
		<c:otherwise>
			<a href="${raizApp}">Volte para home</a>
		</c:otherwise>
	</c:choose>
	<h1>Usuï¿½rios</h1>
	<section id="sectionCategorias">
		<h2>Usuários do sistema</h2>
		<div class="card-container">
			<c:forEach items="${usuarios}" var="usuario">
				<a class="card" href="${urlEditarUsuario}?id=${usuario.id}">
					<div class="card-header">${usuario.nome} ${usuario.sobrenome}</div>
					<div class="card-main">
						<img class="card-cover" alt="Foto do usuário"
							src="${assets}/fotos/foto_${usuario.id}">
					</div>
				</a>
			</c:forEach>
		</div>
	</section>
	</main>
</body>
</html>