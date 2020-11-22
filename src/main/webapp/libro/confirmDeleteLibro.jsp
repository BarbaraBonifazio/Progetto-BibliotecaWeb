<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.utente eq null || sessionScope.utente.ruolo == 'guest' || sessionScope.utente.ruolo == 'operator'}"><c:redirect url="LogoutServlet"/></c:if> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.bibliotecaweb.model.utente.Utente"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">

<jsp:include page="../header.jsp" />

<title>Conferma</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
</head>


<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>

			<div class="text-center">
				<div class='card-header'>
					<h4>Confermi di voler eliminare questo libro?</h4>
				</div>


				<div class='card-body'>

					<form method="get" action="${pageContext.request.contextPath}/libro/ExecuteDeleteLibroServlet"
						novalidate="novalidate">


						<div class="form-row">
							<div class="form-group col-md-6">
							
								<Input type="hidden" name="idDaInviareAExecuteDelete"
									id="idDaInviareAExecuteDelete" class="form-control"
									value="${requestScope.libroDaEliminare.id}"> 
									
									
									<a
									class="btn btn-danger btn-lg" role="button"
									href="${pageContext.request.contextPath}/libro/ExecuteFindLibriServlet?
									titolo=${requestScope.titoloPerTornareAllaRicercaEffettuata}&
									trama=${requestScope.tramaPerTornareAllaRicercaEffettuata}&
									genere=${requestScope.generePerTornareAllaRicercaEffettuata}&
									autore=${requestScope.autorePerTornareAllaRicercaEffettuata}
									">Annulla</a>
							</div>
							
							<Input type="hidden" name="titoloLibroPerRicerca"
							id="titoloLibroDaPassare" class="form-control"
							value="${requestScope.titoloPerTornareAllaRicercaEffettuata}">
							
							<Input type="hidden" name="tramaLibroPerRicerca"
							id="tramaLibroDaPassare" class="form-control"
							value="${requestScope.tramaPerTornareAllaRicercaEffettuata}">
							
							<Input type="hidden" name="genereLibroPerRicerca"
							id="genereLibroDaPassare" class="form-control"
							value="${requestScope.generePerTornareAllaRicercaEffettuata}">
							
							<Input type="hidden" name="autoreLibroPerRicerca"
							id="autoreLibroDaPassare" class="form-control"
							value="${requestScope.autorePerTornareAllaRicercaEffettuata}">

							
							<div class="form-group col-md-6">
								<button type="submit" name="submit" value="submit" id="submit"
									class="btn btn-primary btn-lg">Conferma</button>
							</div>
						</div>

					</form>

					<!-- end card-body -->
				</div>
			</div>
		</div>
		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>