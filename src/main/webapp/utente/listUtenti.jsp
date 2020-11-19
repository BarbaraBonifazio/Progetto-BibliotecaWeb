<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.utente eq null}"><c:redirect url="LogoutServlet"/></c:if>
<%@page import="it.bibliotecaweb.model.utente.Utente"%>
<%@page
	import="it.bibliotecaweb.servlet.utente.PrepareFindUtentiServlet"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Gestione Utenti</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

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
		<Input type="hidden" name="listaUtentiAttribute"
			id="listaUtenti" class="form-control"
			value="${requestScope.listaUtentiAttribute}">
		<div class='card'>
			<div class='card-header'>
				<h5>Lista degli utenti registrati</h5>
			</div>
			<div class='card-body'>
				<%-- <c:if
					test="${sessionScope.utente.ruoli != 'guest'}"> --%>
					<a class="btn btn-primary "
						href="PrepareInsertUtenteServlet?listaUtentiAttribute">Aggiungi</a>
				<%-- </c:if> --%>
				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Id</th>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Username</th>
								<th>Password</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.listaUtentiAttribute}"
								var="utente">
								<tr>
									<td>${utente.id}</td>
									<td>${utente.nome}</td>
									<td>${utente.cognome}</td>
									<td>${utente.username}</td>
									<td data-title="Password">
									<button type="button" id="viewPswd" class="btn btn-default"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></button>
									${utente.password}
									</td>


									
									<td><a class="btn  btn-sm btn-outline-secondary"
										href="FindByIdUtenteServlet?idParamPerDettaglioUtente=${utente.id}">Visualizza</a>
										<%-- <c:if
											test="${sessionScope.utente.ruolo != 'guest'}"> --%>
											<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
												href="PrepareUpdateUtenteServlet?idDaInviareAExecuteUpdate=${utente.id}">Modifica</a>
										<%-- </c:if> <c:if test="${sessionScope.utente.ruolo == 'admin'}"> --%>
											<a class="btn btn-outline-danger btn-sm"
												href="ConfirmDeleteUtenteServlet?idDaInviareAExecuteDelete=${utente.id}">Abilita/Disabilita</a>
										<%-- </c:if> --%></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			<div class='card-footer'>
				<a href="${pageContext.request.contextPath}/home.jsp"
					class='btn btn-outline-secondary' style='width: 90px'> <i
					class='fa fa-chevron-left'></i> Indietro
				</a>
				</div>

				<!-- end card-body -->
			</div>
		</div>

<script src="js/showPass.js"></script>

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>