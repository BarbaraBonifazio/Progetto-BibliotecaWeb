<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.bibliotecaweb.model.libro.Libro"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Lista Utenti</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

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
				<h5>Lista degli utenti ricercati</h5>
			</div>
			<div class='card-body'>
				
			<c:forEach items="${sessionScope.utente.ruoli}" var="ruolo">
				<c:if test="${ruolo.codice == 'ADMIN_ROLE'}">
					<div>
					<a class="btn btn-primary "
						href="${pageContext.request.contextPath}/utente/PrepareInsertUtenteServlet?nomePerTornareAllaRicercaEffettuata=
						${requestScope.nomePerTornareAllaRicercaEffettuata}&
						
						cognomePerTornareAllaRicercaEffettuata=${requestScope.cognomePerTornareAllaRicercaEffettuata}&
						usernamePerTornareAllaRicercaEffettuata=${requestScope.usernamePerTornareAllaRicercaEffettuata}&
						statoPerTornareAllaRicercaEffettuata=${requestScope.statoPerTornareAllaRicercaEffettuata}
						<%-- &ruoliPerTornareAllaRicercaEffettuata=${requestScope.ruoliPerTornareAllaRicercaEffettuata} --%>
						">Aggiungi Utente </a>
					</div>
				</c:if>
			</c:forEach>
					
				<%-- </c:if> --%>
				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Username</th>
								<th>Stato</th>
								<th>Ruoli</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.utentiPerResultsList}"
								var="utente">
								<tr>
									
									<td>${utente.nome}</td>
									<td>${utente.cognome}</td>
									<td>${utente.username}</td>
									<td>${utente.stato}</td>
									<td>${utente.ruoli}</td>
									
									
									
						<c:forEach items="${sessionScope.utente.ruoli}" var="ruolo">
							<c:if test="${ruolo.codice == 'ADMIN_ROLE'}">				
									<c:forEach items="${utente.ruoli}" var="ruolo"> <!-- ciclo i ruoli dell'utente per poterli passare tramite href -->
												<!-- BOTTONE VISUALIZZA -->
									<td><a class="btn  btn-sm btn-outline-secondary"
										href="${pageContext.request.contextPath}
												/utente/FindByIdUtenteServlet?idParamPerDettaglioUtente=${utente.id}&
												nomePerTornareAllaRicercaEffettuata=${requestScope.nomePerTornareAllaRicercaEffettuata}&
												cognomePerTornareAllaRicercaEffettuata=${requestScope.cognomePerTornareAllaRicercaEffettuata}&
												usernamePerTornareAllaRicercaEffettuata=${requestScope.usernamePerTornareAllaRicercaEffettuata}&
												statoPerTornareAllaRicercaEffettuata=${requestScope.statoPerTornareAllaRicercaEffettuata}
												<%-- &ruoliPerTornareAllaRicercaEffettuata=${requestScope.ruoliPerTornareAllaRicercaEffettuata} --%>
												">Visualizza</a>
												
											<!-- BOTTONE MODIFICA -->
										<a class="btn  btn-sm btn-outline-primary"
											href="${pageContext.request.contextPath}
												/utente/PrepareUpdateUtenteServlet?idDaInviareAExecuteUpdate=${utente.id}&
												nomePerTornareAllaRicercaEffettuata=${requestScope.nomePerTornareAllaRicercaEffettuata}&
												cognomePerTornareAllaRicercaEffettuata=${requestScope.cognomePerTornareAllaRicercaEffettuata}&
												usernamePerTornareAllaRicercaEffettuata=${requestScope.usernamePerTornareAllaRicercaEffettuata}&
												statoPerTornareAllaRicercaEffettuata=${requestScope.statoPerTornareAllaRicercaEffettuata}
												<%-- &ruoliPerTornareAllaRicercaEffettuata=${requestScope.ruoliPerTornareAllaRicercaEffettuata} --%>
												">Modifica</a>
											
											<!-- BOTTONE ELIMINA -->
										<a class="btn btn-outline-danger btn-sm"
											href="${pageContext.request.contextPath}
												/utente/ConfirmDeleteUtenteServlet?idDaInviareAExecuteDelete=${utente.id}&
												nomePerTornareAllaRicercaEffettuata=${requestScope.nomePerTornareAllaRicercaEffettuata}&
												cognomePerTornareAllaRicercaEffettuata=${requestScope.cognomePerTornareAllaRicercaEffettuata}&
												usernamePerTornareAllaRicercaEffettuata=${requestScope.usernamePerTornareAllaRicercaEffettuata}&
												statoPerTornareAllaRicercaEffettuata=${requestScope.statoPerTornareAllaRicercaEffettuata}
												<%-- &ruoliPerTornareAllaRicercaEffettuata=${requestScope.ruoliPerTornareAllaRicercaEffettuata} --%>
												">Elimina</a>
									
									</td>
									</c:forEach> <!-- END forEach ruoli utente da ciclare per passare tramite href  -->
							</c:if>
						</c:forEach> <!-- END  forEach ruoli utente da SESSIONE per nascondere i bottoni -->					
							</tr> <!-- END TABELLA -->
							</c:forEach> <!-- END forEach listaUtenti per passare parametri tramite href -->	
						</tbody>
					</table>
				</div>

			<div class='card-footer'>
				<a href="${pageContext.request.contextPath}/utente/PrepareFindUtentiServlet"
					class='btn btn-outline-secondary' style='width: 90px'> <i
					class='fa fa-chevron-left'></i> Nuova Ricerca
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