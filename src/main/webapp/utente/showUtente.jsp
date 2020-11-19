<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.utente eq null}">
	<c:redirect url="LogoutServlet" />
</c:if>
<%@page import="it.bibliotecaweb.model.utente.Utente"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Visualizza utente</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>



<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>
			<div class='card-header'>Visualizza dettaglio Utente</div>
				
				<c:forEach items="${requestScope.utente}"
									var="utente">
			<div class='card-body'>
				<dl class="row">


					<dt class="col-sm-3 text-right">Id Utente:</dt>
					<dd class="col-sm-9">${utente.id}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Nome:</dt>
					<dd class="col-sm-9">${utente.nome}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Cognome:</dt>
					<dd class="col-sm-9">${utente.cognome}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Username:</dt>
					<dd class="col-sm-9">${utente.username}</dd>
				</dl>	
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Stato:</dt>
					<dd class="col-sm-9">${utente.stato}</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Ruolo:</dt>
					<dd class="col-sm-9">${utente.ruolo}</dd>
				</dl>

			
			</div>
		</c:forEach>
			<div class='card-footer'>
				<a href="${pageContext.request.contextPath}/utenti/PrepareFindUtentiServlet"
					class='btn btn-outline-secondary' style='width: 90px'> <i
					class='fa fa-chevron-left'></i> Indietro
				</a>
			</div>
		</div>



		<!-- end main container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>