<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.utente eq null || sessionScope.utente.ruolo == 'guest'}"><c:redirect url="LogoutServlet"/></c:if> --%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Inserisci nuovo Libro</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

<script type ="text/javascript">

			function validateForm() {
				
			var ErrMess = "" ;
			  var titolo = document.forms["myForm"]["titolo"].value;
			  var trama = document.forms["myForm"]["trama"].value;
			  var genere = document.forms["myForm"]["genere"].value;
			  var idAutore = document.forms["myForm"]["idAutore"].value;
				
			 if(titolo == 0) {
				ErrMess = ErrMess+"Il campo titolo risulta vuoto!\n";
			  }
			  
			 if(trama == 0) {
				ErrMess = ErrMess+"Il campo trama risulta vuoto!\n";
			  }  
			  
			if(genere != ':listaGeneri') {
				ErrMess = ErrMess+"Non risulta selezionato alcun genere!\n";
			  }  

			if(idAutore == ':selected') { 
				ErrMess = ErrMess+"Non risulta selezionato alcun autore!\n";
			 }  
			  
			  if(ErrMess != "") {
				  alert(ErrMess)
				  event.preventDefault();
				  return;
			  }
			}
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
				<h5>Inserisci nuovo Libro</h5>
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
					action="${pageContext.request.contextPath}/libro/ExecuteInsertLibroServlet" 
					name="myForm" onsubmit="return validateForm()" novalidate>

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Titolo <span class="text-danger">*</span></label> <input
								type="text" name="titolo" id="titoloLibro" class="form-control"
								placeholder="Inserire nome" value="${libroPerInsertErrato.titolo}" required>
						</div>

						<div class="form-group col-md-6">
							<label>Trama <span class="text-danger">*</span></label> <input
								type="text" name="trama" id="tramaLibro"
								class="form-control" placeholder="Inserire cognome"
								value="${libroPerInsertErrato.trama}" required>
						</div>
					</div>


					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Selezionare Genere</label> 
							<select id="genereLibro" name="genere" class="form-control" required>			
								<option value="${libroPerInsertErrato.genere}" selected="selected">
									${libroPerInsertErrato.genere}</option>
									<c:forEach items="${listaGeneri}" var="genere">
										<c:if test="${genere != 'EMPTY'}">
											<option value="${genere}">${genere}</option>
										</c:if>
									</c:forEach>					
							</select>
						</div>
						
						
						<div class="form-group col-md-6">
							<label>Selezionare Autore</label> 
							<select id="autoreLibro" name="idAutore" class="form-control" required>	
								<option value="${libroPerInsertErrato.autore.id}" selected="selected" >
									${libroPerInsertErrato.autore.nome} 
										${libroPerInsertErrato.autore.cognome}</option>	
									<c:forEach items="${listaAutori}" var="autore">
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
		<!-- </div> -->


		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>