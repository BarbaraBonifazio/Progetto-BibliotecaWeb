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
<link href="./assets/css/global.css" rel="stylesheet">
</head>


<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>

			<div class="text-center">
				<div class='card-header'>
					<h4>Confermi di voler eliminare questo articolo?</h4>
				</div>


				<div class='card-body'>

					<form method="get" action="${pageContext.request.contextPath}/utente/ExecuteDeleteUtentiServlet"
						novalidate="novalidate">


						<div class="form-row">
							<div class="form-group col-md-6">
								<Input type="hidden" name="idDaInviareAExecuteDelete"
									id="idDaInviareAExecuteDelete" class="form-control"
									value="${requestScope.utenteDaEliminare.id}"> <a
									class="btn btn-danger btn-lg" role="button"
									href="${pageContext.request.contextPath}/utente/ExecuteFindUtentiServlet?
									nome=${requestScope.nomePerTornareAllaRicercaEffettuata}&
									cognome=${requestScope.cognomePerTornareAllaRicercaEffettuata}&
									username=${requestScope.usernamePerTornareAllaRicercaEffettuata}&
									stato=${requestScope.statoPerTornareAllaRicercaEffettuata}
									">Annulla</a>
							</div>
							
							<Input type="hidden" name="nomeUtentePerRicerca"
							id="nomeUtenteDaPassare" class="form-control"
							value="${requestScope.nomePerTornareAllaRicercaEffettuata}">
							
							<Input type="hidden" name="cognomeUtentePerRicerca"
							id="cognomeUtenteDaPassare" class="form-control"
							value="${requestScope.cognomePerTornareAllaRicercaEffettuata}">
							
							<Input type="hidden" name="usernameUtentePerRicerca"
							id="usernameUtenteDaPassare" class="form-control"
							value="${requestScope.usernamePerTornareAllaRicercaEffettuata}">
							
							<Input type="hidden" name="statoUtentePerRicerca"
							id="statoUtenteDaPassare" class="form-control"
							value="${requestScope.statoPerTornareAllaRicercaEffettuata}">
							
							<Input type="hidden" name="ruoliUtentePerRicerca"
							id="ruoliUtenteDaPassare" class="form-control"
							value="${requestScope.ruoliPerTornareAllaRicercaEffettuata}">
							
							
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