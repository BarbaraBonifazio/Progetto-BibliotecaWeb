<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.bibliotecaweb.model.libro.Libro"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Lista Libri</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div
			class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
			role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		
		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		
		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Esempio di operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none"
			role="alert">
			Aggiungere d-none nelle class per non far apparire
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Lista dei libri ricercati</h5>
			</div>
			<div class='card-body'>
			<c:forEach items="${sessionScope.utente.ruoli}" var="ruolo">
				<c:if test="${ruolo.codice == 'ADMIN_ROLE' || ruolo.codice == 'CLASSIC_ROLE'}">
					<div>
						<a class="btn btn-primary "
							href="${pageContext.request.contextPath}/libro/PrepareInsertLibroServlet">Aggiungi
							Libro </a>
					</div>
				</c:if>
			</c:forEach>

				<%-- </c:if> --%>
				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Titolo</th>
								<th>Trama</th>
								<th>Genere</th>
								<th>Autore</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.libriPerResultsListLibri}"
								var="libro">
								<tr>

									<td>${libro.titolo}</td>
									<td>${libro.trama}</td>
									<td>${libro.genere}</td>
									<td>${libro.autore.nome} ${libro.autore.cognome}</td>

											
											<!-- BOTTONE VISUALIZZA -->
									<td><a class="btn  btn-sm btn-outline-secondary"
										href="${pageContext.request.contextPath}
												/libro/FindByIdLibroServlet?idParamPerDettaglioLibro=${libro.id}
												">Visualizza</a>
												
										<c:if test="${sessionScope.isAdmin eq 'true' || sessionScope.isClassicUser eq 'true'}">	
											
											<!-- BOTTONE MODIFICA -->
										<a class="btn  btn-sm btn-outline-primary"
										href="${pageContext.request.contextPath}
														/libro/PrepareUpdateLibroServlet?idDaInviareAExecuteUpdate=${libro.id}
														">Modifica</a>
														
											<!-- BOTTONE ELIMINA -->
										<a class="btn btn-outline-danger btn-sm"
										href="${pageContext.request.contextPath}
														/libro/ConfirmDeleteLibroServlet?idDaInviareAExecuteDelete=${libro.id}&
												titoloPerTornareAllaRicercaEffettuata=${requestScope.titoloPerTornareAllaRicercaEffettuata}&
												tramaPerTornareAllaRicercaEffettuata=${requestScope.tramaPerTornareAllaRicercaEffettuata}&
												generePerTornareAllaRicercaEffettuata=${requestScope.generePerTornareAllaRicercaEffettuata}&
												autorePerTornareAllaRicercaEffettuata=${requestScope.autorePerTornareAllaRicercaEffettuata}
														">Cancella</a>
										</c:if>
										
									</td>
								</tr> <!-- END tabella  -->
							</c:forEach> <!-- END forEach lista Libri per passare i parametri anche tramite href -->
						</tbody>
					</table>
				</div>

				<div class='card-footer'>
					<a
						href="${pageContext.request.contextPath}/libro/PrepareFindLibriServlet"
						class='btn btn-outline-secondary' style='width: 90px'> <i
						class='fa fa-chevron-left'></i> Indietro
					</a>
				</div>

				<!-- end card-body -->
			</div>
		</div>



		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>