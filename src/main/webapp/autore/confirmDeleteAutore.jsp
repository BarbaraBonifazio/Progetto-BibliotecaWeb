<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.autore eq null || sessionScope.autore.ruolo == 'guest' || sessionScope.autore.ruolo == 'operator'}"><c:redirect url="LogoutServlet"/></c:if> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.bibliotecaweb.model.autore.Autore"%>
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
					<h4>Confermi di voler eliminare questo autore?</h4>
				</div>


				<div class='card-body'>

					<form method="get" action="${pageContext.request.contextPath}/autore/ExecuteDeleteAutoreServlet"
						novalidate="novalidate">


						<div class="form-row">
							<div class="form-group col-md-6">
								<Input type="hidden" name="idDaInviareAExecuteDelete"
									id="idDaInviareAExecuteDelete" class="form-control"
									value="${requestScope.autoreDaEliminare.id}"> 
									
									
									<a
									class="btn btn-danger btn-lg" role="button"
									href="${pageContext.request.contextPath}/autore/ExecuteFindAutoriServlet?
									nome=${requestScope.nomePerTornareAllaRicercaEffettuata}&
									cognome=${requestScope.cognomePerTornareAllaRicercaEffettuata}&
									dataNascita=${requestScope.dataNascitaPerTornareAllaRicercaEffettuata}
									">Annulla</a>
							</div>
							
							<Input type="hidden" name="nomeAutorePerRicerca"
							id="nomeAutoreDaPassare" class="form-control"
							value="${requestScope.nomePerTornareAllaRicercaEffettuata}">
							
							<Input type="hidden" name="cognomeAutorePerRicerca"
							id="cognomeAutoreDaPassare" class="form-control"
							value="${requestScope.cognomePerTornareAllaRicercaEffettuata}">
							
							<Input type="hidden" name="dataNascitaAutorePerRicerca"
							id="dataNascitaAutoreDaPassare" class="form-control"
							value="${requestScope.dataNascitaPerTornareAllaRicercaEffettuata}">
							
							
							
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