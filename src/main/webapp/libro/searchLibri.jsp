<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.bibliotecaweb.model.utente.Utente"%>
<%@page import="it.bibliotecaweb.model.utente.StatoUtente"%>
<%@page import="it.bibliotecaweb.model.ruolo.Ruolo"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Cerca Libri</title>

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
				<h5>Cerca Libri</h5>
				<a class="text-right"
					href="${pageContext.request.contextPath}/home.jsp"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Indietro
				</a>
			</div>
			<div class='card-body'>


				<form method="get"
					action="${pageContext.request.contextPath}/libro/ExecuteFindLibriServlet"
					novalidate>

					<div class="form-group col-md-6">
						<label>Titolo</label> <input type="text" name="titolo"
							id="titoloLibro" class="form-control"
							placeholder="Inserire almeno i primi tre caratteri">
					</div>

					<div class="form-group col-md-6">
						<label>Trama</label> <input type="text" name="trama"
							id="tramaLibro" class="form-control"
							placeholder="Inserire username">
					</div>

					<div class="form-group col-md-6">
						<label>Genere</label> 
						<select id="genereLibro" name="genere"
							class="form-control">
							<option value="${stato.EMPTY}">- Seleziona Genere -</option>
							<c:forEach items="${listaGeneri}" var="genere">
								<c:if test="${genere != 'EMPTY'}">
									<option value="${genere}">${genere}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>

					<div class="form-group col-md-6">
						<label>Autore</label> 
							<select id="autoreLibro" name="autore"
								class="form-control">
								<option value="">- Seleziona Autore -</option>
								<c:forEach items="${listaAutori}" var="autore">
								<c:if test="${stato != 'EMPTY'}">
										<option value="${autore.id}">
										${autore.nome} ${autore.cognome}</option>
								</c:if>
								</c:forEach>
							</select>
					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Cerca</button>

				</form>

				<!-- end card-body -->
			</div>

		</div>
		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>
