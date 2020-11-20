<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.utente eq null || sessionScope.utente.ruolo == 'guest'}"><c:redirect url="LogoutServlet"/></c:if> --%>
<%@page import="it.bibliotecaweb.model.utente.Utente"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Modifica</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
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

		<%-- <div class='card'>
			<div class='card-header'>
				<h5>Modifica Utente</h5>
				<a class="text-right" href="${pageContext.request.contextPath}/PrepareFindUtentiServlet"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Back
				</a>
			</div>
			<div class='card-body'> --%>

				<h6 class="card-title">
					I campi con <span class="text-danger">*</span> sono obbligatori
				</h6>

				<form method="post" action="${pageContext.request.contextPath}/utente/ExecuteUpdateUtenteServlet" 
								class="needs-validation" novalidate>

					<div class="form-row">
						<Input type="hidden" name="idUtentePerUpdate"
							id="idUtente" class="form-control"
							value="${requestScope.utentePerUpdate.id}">

						<div class="form-group col-md-6">
							<label>Nome <span class="text-danger">*</span></label> <input
								type="text" name="nome" id="nomeUtente" class="form-control"
								placeholder="${requestScope.utentePerUpdate.nome}" required
								value="${requestScope.utentePerUpdate.nome}">
						</div>

						<div class="form-group col-md-6">
							<label>Cognome <span class="text-danger">*</span></label> <input
								type="text" name="cognome" id="cognomeUtente"
								class="form-control" placeholder="${requestScope.utentePerUpdate.cognome}"
								required value="${requestScope.utentePerUpdate.cognome}">
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Username <span class="text-danger">*</span></label> <input
								type="text" class="form-control" name="username" id="usernameUtente"
								placeholder="${requestScope.utentePerUpdate.username}" required
								value="${requestScope.utentePerUpdate.username}">
						</div>
						
						<div class="form-group col-md-6">
							<label>Password <span class="text-danger">*</span></label> <input
								type="text" class="form-control" name="password" id="passwordUtente"
								placeholder="${requestScope.utentePerUpdate.password}" required
								value="${requestScope.utentePerUpdate.password}">
						</div>

						<div class="form-group col md-4">
							<label for="exampleFormControlSelect1">Ruoli</label>
							<c:forEach items="${requestScope.listRuoliAttribute}" var="ruolo">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="${ruolo.id}" id="idRuolo" name="ruolo"
										<c:forEach items="${utente.ruoli}" var="ruoloUtente">
											${ruoloUtente.id eq ruolo.id ? 'checked' : ''} 
										</c:forEach>
									>
									<label class="form-check-label" for="defaultCheck1">
										${ruolo.codice} </label>
								</div>
							</c:forEach>
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
							
							<%-- <Input type="hidden" name="ruoliUtentePerRicerca"
							id="ruoliUtenteDaPassare" class="form-control"
							value="${requestScope.ruoliPerTornareAllaRicercaEffettuata}">  --%>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>
				
				<!-- end card-body -->
					</div>
				</form>

				<script>
				
				(function() {
					  'use strict';
					  window.addEventListener('load', function() {
					   
					    var forms = document.getElementsByClassName('needs-validation');
					    
					    var validation = Array.prototype.filter.call(forms, function(form) {
					      form.addEventListener('submit', function(event) {
						        if (form.checkValidity() === false) {
						          event.preventDefault();
						          event.stopPropagation();
						        }
					        form.classList.add('was-validated');
					        
					      }, false);
					    });
					  }, false);
					})();
	
				</script>


		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>