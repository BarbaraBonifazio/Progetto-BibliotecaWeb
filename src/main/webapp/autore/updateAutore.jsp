<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.autore eq null || sessionScope.autore.ruolo == 'guest'}"><c:redirect url="LogoutServlet"/></c:if> --%>
<%@page import="it.bibliotecaweb.model.autore.Autore"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Modifica</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">

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
				<h5>Modifica Autore</h5>
			</div>
		
	<div class='card-body'>
		<h6 class="card-title">
			I campi con <span class="text-danger">*</span> sono obbligatori
		</h6>

		<form method="post"
			action="${pageContext.request.contextPath}/autore/ExecuteUpdateAutoreServlet"
			class="needs-validation" novalidate>

			<div class="form-row">
				<Input type="hidden" name="idAutorePerUpdate" id="idAutore"
					class="form-control" value="${requestScope.autorePerUpdate.id}">

				<div class="form-group col-md-6">
					<label>Nome <span class="text-danger">*</span></label> <input
						type="text" name="nome" id="nomeAutore" class="form-control"
						placeholder="${requestScope.autorePerUpdate.nome}"
						value="${requestScope.autorePerUpdate.nome}">
				</div>

				<div class="form-group col-md-6">
					<label>Cognome <span class="text-danger">*</span></label> <input
						type="text" name="cognome" id="cognomeAutore" class="form-control"
						placeholder="${requestScope.autorePerUpdate.cognome}"
						value="${requestScope.autorePerUpdate.cognome}">
				</div>
			</div>

			<div class="it-datepicker-wrapper">
				  <div class="form-group col-md-6">
				  <label for="date1">Data di nascita</label>
				    <input class="form-control" type="date" name="dataNascita" id="dataNascitaAutore" 
				    value="${autorePerUpdate.dataNascita}">
				  </div>
			</div>


				<button type="submit" name="submit" value="submit" id="submit"
					class="btn btn-primary">Conferma</button>
		</form>
	<!-- end card-body -->
	</div>

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
<!-- end card-body -->
</div>
		</div>

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>