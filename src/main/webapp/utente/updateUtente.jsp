<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.utente eq null || sessionScope.utente.ruolo == 'guest'}"><c:redirect url="LogoutServlet"/></c:if> --%>
<%@page import="it.bibliotecaweb.model.utente.Utente"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Modifica</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">

	<script type="text/javascript">
	
		 $(document).ready(function() { 
		  	$("form").submit(function( event ) {
		  			
		  		$("#errorName").hide(); 
			  	$("#errorSurname").hide(); 
			  	$("#errorUsername").hide();
			  	$("#errorStatus").hide();
			  	$("#errorRoles").hide(); 
			  	
			  	var validate = true;
			  	
			  	if(!$("#nomeUtente")[0].value) { 
				  	$("#errorName").show();
				  	valida = false;
			  	}	
				  	if(!$("#cognomeUtente")[0].value){ 
					  	$("#errorSurname").show();
					  	valida = false;
					} 	
						if(!$("#usernameUtente")[0].value){ 
						  	$("#errorUsername").show();
						  	valida = false;
						} 						  	
						  	if(!$("#statoUtente")[0].value){ 
							  	$("#errorStatus").show();
							  	valida = false;
							} 	
							  	if(!$("input[type='checkbox']").is(":checked")){ 
								  	$("#errorRoles").show();
								  	valida = false;
								} 	
								  	if(!valida){ 
								  		event.preventDefault(); 
								  		return;
								  	}
			 });
		})  
		
	  </script>  

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

	<c:forEach items="${requestScope.errorMessage}" var="errore">
		<div
			class="alert alert-danger alert-dismissible fade show ${errore==null?'d-none': ''}"
			role="alert">
			${errore}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:forEach>
<div class='card'>
			<div class='card-header'>
				<h5>Modifica Utente</h5>
				<%-- <a class="text-right" href="${pageContext.request.contextPath}/ListUtentiServlet"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Back
				</a> --%>
			</div>
		
	<div class='card-body'>
		<h6 class="card-title">
			I campi con <span class="text-danger">*</span> sono obbligatori
		</h6>

		<form method="post"
			action="${pageContext.request.contextPath}/utente/ExecuteUpdateUtenteServlet"
			id="form" class="needs-validation" novalidate>

			<div class="form-row">
				<Input type="hidden" name="idUtentePerUpdate" id="idUtente"
					class="form-control" value="${requestScope.utentePerUpdate.id}" required>

				<div class="form-group col-md-6">
					<label>Nome <span class="text-danger">*</span></label> <input
						type="text" name="nome" id="nomeUtente" class="form-control"
						placeholder="${requestScope.utentePerUpdate.nome}"
						value="${requestScope.utentePerUpdate.nome}" required>
						<div class="invalid-feedback" id="errorName"> Il campo nome risulta vuoto!</div>
				</div>

				<div class="form-group col-md-6">
					<label>Cognome <span class="text-danger">*</span></label> <input
						type="text" name="cognome" id="cognomeUtente" class="form-control"
						placeholder="${requestScope.utentePerUpdate.cognome}"
						value="${requestScope.utentePerUpdate.cognome}" required>
						<div class="invalid-feedback" id="errorSurname"> Il campo cognome risulta vuoto!</div>
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Username <span class="text-danger">*</span></label> <input
						type="text" class="form-control" name="username"
						id="usernameUtente"
						placeholder="${requestScope.utentePerUpdate.username}"
						value="${requestScope.utentePerUpdate.username}" required>
						<div class="invalid-feedback" id="errorUsername"> Il campo username risulta vuoto!</div>
				</div>

				<div class="form-group col-md-6">
					<label>Stato</label> 
					<select id="statoUtente" name="stato" class="form-control" required>
						<option value="${stato.EMPTY}" selected="selected">-Selezionare Stato-</option>
						<c:forEach items="${listaStati}" var="stato">
							<c:if test="${stato != 'EMPTY'}">
								<option value="${stato}" ${stato == utente.stato ? 'selected="selected"' : ''}>${stato}</option>
							</c:if>
						</c:forEach>
					</select>
					<div class="invalid-feedback" id="errorStatus"> Non risulta selezionato alcuno stato!</div>
				</div>

				<div class="form-group col md-4">
					<label for="exampleFormControlSelect1">Ruoli</label>
					<c:forEach items="${requestScope.listRuoliAttribute}" var="ruolo">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" required
								value="${ruolo.id}" id="idRuolo" name="ruolo"
								<c:forEach items="${requestScope.utentePerUpdate.ruoli}" var="ruoloSelezionato">
											${ruoloSelezionato.id eq ruolo.id ? 'checked' : ''} 
										</c:forEach>>
							<label class="form-check-label" for="defaultCheck1">
								${ruolo.codice} </label>	
						</div>	
					</c:forEach>
					<div class="invalid-feedback" id="errorRoles"> Non risulta selezionato alcun ruolo!</div>
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

<!-- end card-body -->
</div>
		</div>

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>