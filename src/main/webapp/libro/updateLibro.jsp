<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.utente eq null || sessionScope.utente.ruolo == 'guest'}"><c:redirect url="LogoutServlet"/></c:if> --%>
<%@page import="it.bibliotecaweb.model.libro.Libro"%>
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
				<h5>Modifica Libro</h5>
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
			action="${pageContext.request.contextPath}/libro/ExecuteUpdateLibroServlet"
			class="needs-validation" novalidate>

			<div class="form-row">
				<Input type="hidden" name="idLibroPerUpdate" id="idLibro"
					class="form-control" value="${requestScope.libroPerUpdate.id}">

				<div class="form-group col-md-6">
					<label>Titolo <span class="text-danger">*</span></label> <input
						type="text" name="titolo" id="titoloLibro" class="form-control"
						placeholder="${requestScope.libroPerUpdate.titolo}"
						value="${requestScope.libroPerUpdate.titolo}">
				</div>

				<div class="form-group col-md-6">
					<label>Trama <span class="text-danger">*</span></label> <input
						type="text" name="trama" id="tramaLibro" class="form-control"
						placeholder="${requestScope.libroPerUpdate.trama}"
						value="${requestScope.libroPerUpdate.trama}">
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Selezionare Genere</label> 
					<select id="genereLibro" name="genere" class="form-control">			
					<option value="${libroPerUpdate.genere}" selected="selected">${libroPerUpdate.genere}</option>
						<c:forEach items="${listaGeneri}" var="genere">
							<c:if test="${genere != 'EMPTY'}">
								<option value="${genere}">${genere}</option>
							</c:if>
						</c:forEach>					
					</select>
				</div>

				<div class="form-group col-md-6">
					<label>Selezionare Autore</label> 
						<select id="autoreLibro" name="idAutore" class="form-control">	
						<option value="${libroPerUpdate.autore.id}" selected="selected" >
							${libroPerUpdate.autore.nome} 
								${libroPerUpdate.autore.cognome}</option>	
							<c:forEach items="${listAutoriAttribute}" var="autore">
							<c:if test="${stato != 'EMPTY'}">
									<option value="${autore.id}">
									${autore.nome} ${autore.cognome}</option>
							</c:if>
							</c:forEach>
						</select>
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
		

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>