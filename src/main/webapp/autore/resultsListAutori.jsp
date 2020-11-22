<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.utente eq null}"><c:redirect url="LogoutServlet"/></c:if> controllo fatto tramite filtro--%>
<%@page import="it.bibliotecaweb.model.libro.Libro"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Lista Autori</title>

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
				<h5>Lista degli autori ricercati</h5>
			</div>
			<div class='card-body'>
				<%-- <c:if
					test="${sessionScope.utente.ruolo != 'guest'}">  controllo da fare tramite filtro--%>
					<div>
					<a class="btn btn-primary "
						href="${pageContext.request.contextPath}/autore/PrepareInsertAutoreServlet
						">Aggiungi Autore </a>
					</div>
					
				<%-- </c:if> --%>
				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Data di Nascita</th>
								
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.autoriPerResultsList}"
								var="autore">
								<tr>
									
									<td>${autore.nome}</td>
									<td>${autore.cognome}</td>
									<td>${autore.dataNascita}</td>
					

									<td><a class="btn  btn-sm btn-outline-secondary"
										href="${pageContext.request.contextPath}
												/autore/FindByIdAutoreServlet?idParamPerDettaglioAutore=${autore.id}
												">Visualizza</a>
										<%-- <c:if
											test="${sessionScope.utente.ruolo != 'guest'}"> CONTROLLI DI VISIBILITA DA FARE SUI FILTRI!> --%>
										<a class="btn  btn-sm btn-outline-primary"
											href="${pageContext.request.contextPath}
												/autore/PrepareUpdateAutoreServlet?idDaInviareAExecuteUpdate=${autore.id}
												">Modifica</a>
										<%-- </c:if> <c:if test="${sessionScope.utente.ruolo == 'admin'}"> --%>
										<a class="btn btn-outline-danger btn-sm"
											href="${pageContext.request.contextPath}
												/autore/ConfirmDeleteAutoreServlet?idDaInviareAExecuteDelete=${autore.id}
												">Elimina</a>
										<%-- </c:if> --%></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			<div class='card-footer'>
				<a href="${pageContext.request.contextPath}/autore/PrepareFindAutoriServlet"
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