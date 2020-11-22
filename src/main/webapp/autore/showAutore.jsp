<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.utente eq null}">
	<c:redirect url="LogoutServlet" />
</c:if>
<%@page import="it.bibliotecaweb.model.utente.Utente"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Visualizza Autore</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>



<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>
			<div class='card-header'>Visualizza Dettaglio Autore</div>
				
			
			<div class='card-body'>
				<dl class="row">


					<dt class="col-sm-3 text-right">Id Autore:</dt>
					<dd class="col-sm-9">${autorePerShow.id}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Nome:</dt>
					<dd class="col-sm-9">${autorePerShow.nome}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Cognome:</dt>
					<dd class="col-sm-9">${autorePerShow.cognome}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Data di Nascita:</dt>
					<dd class="col-sm-9">${autorePerShow.dataNascita}</dd>
				</dl>	
 			</div>
	
		
			<%--<div class='card-footer'>
				<a href="${pageContext.request.contextPath}/autore/PrepareFindAutoreServlet"
					class='btn btn-outline-secondary' style='width: 90px'> <i
					class='fa fa-chevron-left'></i> Nuova Ricerca
				</a>
			</div>--%>
		</div> 



		<!-- end main container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>