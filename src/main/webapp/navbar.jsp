<!-- navbar -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.bibliotecaweb.model.utente.Utente"%>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault" aria-controls="navbarCollapse"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
<%-- 	<c:forEach items="${sessionScope.utente}"
						var="ruoli"> --%>	
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/home.jsp">Home
					<span class="sr-only">(current)</span>
			</a></li>
			<!-- <li class="nav-item"><a class="nav-link" href="#">Link</a></li>

			<li class="nav-item"><a class="nav-link disabled" href="#"
				tabindex="-1" aria-disabled="true">Disabled</a></li> -->

			<li class="nav-item dropdown active"><a
				class="nav-link dropdown-toggle" href="#" id="dropdown01"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Cerca</a>
				<div class="dropdown-menu" aria-labelledby="dropdown01">
					 <a	class="dropdown-item" href="${pageContext.request.contextPath}/libro/PrepareFindLibriServlet">Ricerca Libro</a> 
						<a class="dropdown-item" href="${pageContext.request.contextPath}/autore/PrepareFindAutoriServlet">Ricerca Autore</a>
				<c:forEach items="${sessionScope.utente.ruoli}" var="ruolo"> 
					<c:if test="${ruolo.codice == 'ADMIN_ROLE'}">
						<a class="dropdown-item" href="${pageContext.request.contextPath}/utente/PrepareFindUtentiServlet">Gestione Utenti</a>
					</c:if>
				</c:forEach>
				</div></li>

			<li class="nav-item active"><a class="nav-link" href="#">
					Benvenuto/a <c:out value="${sessionScope.utente.nome}"/> <c:out
						value="${sessionScope.utente.cognome}" />
			</a></li>

		</ul>
			<ul class="navbar-nav mr-1">
				<li class="nav-item active"><a class="nav-link active"
					href="${pageContext.request.contextPath}/LogoutServlet"> Logout </a></li>
			</ul>
		
		<!-- <form class="form-inline my-2 my-lg-0">
			 <input
				class="form-control mr-sm-2" type="text" placeholder="Search"
				aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form> -->
	</div>
</nav>
