<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.utente eq null || sessionScope.utente.ruolo == 'guest'}"><c:redirect url="LogoutServlet"/></c:if> --%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Inserisci nuovo Autore</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">

	<script type="text/javascript">
	
		 $(document).ready(function() { 
		  	$("form").submit(function( event ) {
		  			
			  	$("#errorName").hide(); 
			  	$("#errorSurname").hide(); 
			  	$("#errorDate").hide(); 
			  	
			  	var validate = true;
			  	
				  	if(!$("#nomeAutore")[0].value) { 
					  	$("#errorName").show();
					  	valida = false;
				  	}	
					  	if(!$("#cognomeAutore")[0].value){ 
						  	$("#errorSurname").show();
						  	valida = false;
						} 	
						  	if(!$("#dataNascitaAutore")[0].value){ 
							  	$("#errorDate").show();
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
				<h5>Inserisci nuovo Autore</h5>
				<%-- <a class="text-right" href="${pageContext.request.contextPath}/ListUtentiServlet"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Back
				</a> --%>
			</div>
		</div>
		<div class='card-body'>

			<h6 class="card-title">
				I campi con <span class="text-danger">*</span> sono obbligatori
			</h6>

			<form method="post"
				action="${pageContext.request.contextPath}/autore/ExecuteInsertAutoreServlet"
				id="form" class="needs-validation" novalidate>


				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Nome <span class="text-danger">*</span></label> <input
							type="text" name="nome" id="nomeAutore" class="form-control"
							placeholder="Inserire nome" value="${autorePerInsertErrato.nome}">
							<div class="invalid-feedback" id="errorName"> Il campo nome risulta vuoto!</div>
					</div>
					

					<div class="form-group col-md-6">
						<label>Cognome <span class="text-danger">*</span></label> <input
							type="text" name="cognome" id="cognomeAutore"
							class="form-control" placeholder="Inserire cognome"
							value="${autorePerInsertErrato.cognome}">
							<div class="invalid-feedback" id="errorSurname"> Il campo cognome risulta vuoto!</div>
					</div>
					
				</div>

				<div class="it-datepicker-wrapper">
					  <div class="form-group col-md-6">
					  <label for="date1">Data di nascita</label>
					    <input class="form-control" type="date" name="dataNascita" id="dataNascitaAutore" 
					    value="${autorePerInsertErrato.dataNascita}">
					    <div class="invalid-feedback" id="errorDate"> Non risulta selezionata alcuna data!</div>
					  </div>
					</div>

				<button type="submit" name="submit" value="submit" id="submit"
					class="btn btn-primary">Conferma</button>

			</form>

			<!-- end card-body -->
		</div>
		<!-- </div> -->


		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>