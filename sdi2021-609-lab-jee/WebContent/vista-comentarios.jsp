<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>BLOG</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	
	<jsp:useBean id="contador" class="com.uniovi.sdi.Contador"
		scope="application" />
	<jsp:setProperty name="contador" property="incremento" value="1" />

	<!-- Barra de Navegación superior -->
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li><a href="comentar.jsp">Comentar</a></li>
			<li><a href="vista-comentarios.jsp">Blog</a></li>
		</ul>
		<div class="nav navbar-right">
			<jsp:getProperty name="contador" property="total" />
			Visitas
		</div>
	</div>
	</nav>

	<jsp:useBean id="blogService" class="com.uniovi.sdi.BlogService" />

	<!-- Contenido -->
	<div class="container" id="contenedor-principal">
		<h2>Vista-Comentarios</h2>
		<ul>
			<c:forEach var="comentario" items="${blogService.comentarios}">
				<tr>
					<li>${comentario.nombre}
						<br>
						${comentario.comentario}</li>
				</tr>
			</c:forEach>
		</ul>
	</div>
</body>
</html>