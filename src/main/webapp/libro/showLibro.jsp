<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.utente eq null}">
	<c:redirect url="LogoutServlet" />
</c:if>
<%@page import="it.bibliotecaweb.model.utente.Utente"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Visualizza Libro</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>



<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>
			<div class='card-header'>Visualizza Dettaglio Libro</div>

			
			<div class='card-body'>
				<dl class="row">


					<dt class="col-sm-3 text-right">Id Libro:</dt>
					<dd class="col-sm-9">${libroPerShow.id}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Titolo:</dt>
					<dd class="col-sm-9">${libroPerShow.titolo}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Trama:</dt>
					<dd class="col-sm-9">${libroPerShow.trama}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Genere:</dt>
					<dd class="col-sm-9">${libroPerShow.genere}</dd>
				</dl>	
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Autore:</dt>
					<dd class="col-sm-9">${libroPerShow.autore.nome} ${libroPerShow.autore.cognome}</dd>
				</dl>
 			</div>
	
		
			<%--<div class='card-footer'>
				<a href="${pageContext.request.contextPath}/libro/PrepareFindLibroServlet"
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