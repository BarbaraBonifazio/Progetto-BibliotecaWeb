
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.utente eq null}"><c:redirect url="LogoutServlet"/></c:if>  questa verifica si potrà fare direttamente tramite filtro--%>
<%@page import="it.bibliotecaweb.model.autore.Autore"%>

<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Search Autori</title>

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
				<h5>Cerca Autori</h5>
				<a class="text-right"
					href="${pageContext.request.contextPath}/home.jsp"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Indietro
				</a>
			</div>
			<div class='card-body'>


				<form method="post"
					action="${pageContext.request.contextPath}/autore/ExecuteFindAutoriServlet">

					<div class="form-group col-md-6">
						<label>Nome</label> <input type="text" name="nome"
							id="nomeAutore" class="form-control"
							placeholder="Inserire almeno i primi tre caratteri">
					</div>

					<div class="form-group col-md-6">
						<label>Cognome</label> <input type="text" name="cognome"
							id="cognomeAutore" class="form-control"
							placeholder="Inserire almeno i primi tre caratteri">
					</div>
					
					<div class="it-datepicker-wrapper">
					  <div class="form-group col-md-6">
					  <label for="date1">Data di nascita</label>
					    <input class="form-control it-date-datepicker" type="date" name="dataNascita" id="dataNascitaAutore">
					  </div>
					</div>
					                 

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Cerca</button>

				</form>


				<script>
				/* qui farò le verifiche javascript per quanto riguarda tutti i campi di questa jsp */
				function validateForm() {
					  var x = document.forms["myForm"]["data"].value;

					  if (x is NaD) {
					    alert("La data non ha una corretta formattazione!!");
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
