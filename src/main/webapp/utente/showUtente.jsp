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
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>



<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>
			<div class='card-header'>Visualizza dettaglio Utente</div>
				
				
				<Input type="hidden" name="utente"
			id="utente" class="form-control"
			value="${requestScope.utentePerShow}">
			
			<div class='card-body'>
				<dl class="row">


					<dt class="col-sm-3 text-right">Id Utente:</dt>
					<dd class="col-sm-9">${utentePerShow.id}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Nome:</dt>
					<dd class="col-sm-9">${utentePerShow.nome}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Cognome:</dt>
					<dd class="col-sm-9">${utentePerShow.cognome}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Username:</dt>
					<dd class="col-sm-9">${utentePerShow.username}</dd>
				</dl>	
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Stato:</dt>
					<dd class="col-sm-9">${utentePerShow.stato}</dd>
				</dl>
				
				<%-- <dl class="row">
					<dt class="col-sm-3 text-right">Ruolo:</dt>
					<dd class="col-sm-9">${utentePerShow.ruoli}</dd>
				</dl> --%>


			 <%-- <Input type="hidden" name="nome"
			id="utente" class="form-control"
			value="${requestScope.nome}">
			<Input type="hidden" name="cognome"
			id="utente" class="form-control"
			value="${requestScope.cognome}">
			<Input type="hidden" name="username"
			id="utente" class="form-control"
			value="${requestScope.username}">
			<Input type="hidden" name="stato"
			id="utente" class="form-control"
			value="${requestScope.stato}">
 --%> 
			
			</div>
		
			<div class='card-footer'>
				<a href="${pageContext.request.contextPath}/utente/ExecuteFindUtentiServlet?nome=${requestScope.nomeUtenteRicercatoInput}
				&cognome=${requestScope.cognomeUtenteRicercatoInput}&username=${requestScope.usernameUtenteRicercatoInput}&
				stato=${requestScope.statoUtenteRicercatoInput}&ruoli=${requestScope.ruoliPerTornareAllaRicercaEffettuata}"
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