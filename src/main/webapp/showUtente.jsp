<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.utente eq null}">
	<c:redirect url="LogoutServlet" />
</c:if>
<%@page import="it.bibliotecaweb.model.utente.Utente"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="header.jsp" />
<title>Visualizza utente</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>



<body>
	<jsp:include page="navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>
			<div class='card-header'>Visualizza dettaglio Utente</div>

			<Input type="hidden" name="articoloPerShow" id="articoloPerShow"
				class="form-control" value="${requestScope.utentePerShow.id}">
			<div class='card-body'>
				<dl class="row">


					<dt class="col-sm-3 text-right">Id Utente:</dt>
					<dd class="col-sm-9">${utentePerShow.id}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Nome:</dt>
					<dd class="col-sm-9">${utentePerShow.nome}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Cognome:</dt>
					<dd class="col-sm-9">${utentePerShow.cognome}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Username:</dt>
					<dd class="col-sm-9">${utentePerShow.username}</dd>
				</dl>
				
				<p class="col-sm-3 text-right">
				<button class="btn btn-outline-secondary" type="button" data-toggle="collapse" data-target="#collapseExample" 
				aria-expanded="false" aria-controls="collapseExample">
				    Password
				  </button>
				</p>
				<div class="collapse" id="collapseExample">
				  <div class="card card-body my-2 my-sm-0" style='width: 90px padding: 20px' align="center">
				    ${utentePerShow.password}
				  </div>
				</div>		


			</div>

			<div class='card-footer'>
				<a href="${pageContext.request.contextPath}/PrepareGestioneUtentiServlet"
					class='btn btn-outline-secondary' style='width: 90px'> <i
					class='fa fa-chevron-left'></i> Indietro
				</a>
			</div>
		</div>



		<!-- end main container -->
	</main>
	<jsp:include page="footer.jsp" />

</body>
</html>