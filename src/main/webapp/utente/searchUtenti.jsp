
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.utente eq null}"><c:redirect url="LogoutServlet"/></c:if>  questa verifica si potrà fare direttamente tramite filtro--%>
<%@page import="it.bibliotecaweb.model.utente.Utente"%>
<%@page import="it.bibliotecaweb.model.utente.StatoUtente"%>
<%@page import="it.bibliotecaweb.model.ruolo.Ruolo"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Search Utenti</title>

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

		<div class='card'>
			<div class='card-header'>
				<h5>Cerca Utente</h5>
				<a class="text-right"
					href="${pageContext.request.contextPath}/home.jsp"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Back
				</a>
			</div>
			<div class='card-body'>


				<form method="post"
					action="${pageContext.request.contextPath}/utenti/ExecuteFindUtenteServlet"
					name="myForm" onsubmit="return validateForm()" novalidate>


					<div class="form-group col-md-6">
						<label>Nome</label> <input type="text" name="nomeUtente" id="nome"
							class="form-control"
							placeholder="Inserire almeno i primi tre caratteri" required>

					</div>

					<div class="form-group col-md-6">
						<label>Cognome</label> <input type="text" name="cognomeUtente"
							id="cognome" class="form-control"
							placeholder="Inserire almeno i primi tre caratteri">

					</div>

					<div class="form-group col-md-6">
						<label>Username</label> <input type="text" name="unameUtente"
							id="username" class="form-control"
							placeholder="Inserire username">

					</div>

					<Input type="hidden" name="utentiPerShow" id="utentiPerShow"
						class="form-control" value="${requestScope.listaUtentiAttribute}">

					<div class="form-group col-md-6">
						<label>Stato</label> <select id="stato" name="stato"
							class="form-control">
							<option value="${stato.EMPTY}">- Seleziona Stato -</option>
							<c:forEach items="${listaStati}" var="stato">
								<c:if test="${stato != 'EMPTY'}">
									<option>${stato}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>



					<%-- <div class="form-group col-md-6">
						<label>Ruoli</label>

						
						<div class="form-check">
							
								<!-- Default unchecked -->
						<div class="custom-control custom-checkbox">
						<c:forEach items="${requestScope.listRuoliAttribute}" var="ruolo">
						    <input type="checkbox" class="form-check-input" value="${ruolo.id}" name="ruolo" id="ruolo">
						    <label class="form-check-label" for="defaultUnchecked"><c:out
										value="${ruolo.codice}" /></label><br>
						    </c:forEach>
						</div> --%>
						
	
						<!-- </div>
					</div> -->

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Cerca</button>

				</form>


				<script>
				
				function validateForm() {
					  var x = document.forms["myForm"]["prezzo"].value;

					  if (x < 0) {
					    alert("Il prezzo non può essere un numero negativo!");
					    return false;
					  }
					  
					  else if (isNaN(document.forms["myForm"]["prezzo"].value)) {
						  
						    alert("Il campo Prezzo deve essere un numero!");
						    return false;
					  }  
					} 
	
				</script>



				<!-- end card-body -->
			</div>

		</div>
		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>
