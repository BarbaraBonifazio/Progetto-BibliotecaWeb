<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="it">
<head>

<jsp:include page="header.jsp" />

<!-- Custom styles for this template -->
<link href="./assets/css/global.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 3.5rem;
}
</style>

<title>Biblioteca web!</title>
</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>


	<main role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<div class="container">
				<div
					class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
					role="alert">
					${errorMessage}
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<h1 class="display-3">Benvenuto alla tua Biblioteca Web!</h1>
				<p>Potrai fare ricerche per Libro e per Autore. Che aspetti?</p>




				<p>
					<a class="btn btn-primary btn-lg"
						href="PrepareFindLibriServlet"
						role="button">Ricerca Libri &raquo;</a>
				</p>

				<p>
					<a class="btn btn-primary btn-lg"
						href="PrepareFindAutoriServlet"
						role="button">Ricerca Autori  &raquo;</a>
				</p>

			</div>
		</div>

		<!-- <div class="container">
			Example row of columns
			<div class="row">
				<div class="col-md-4">
					<h2>Heading</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
						ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.</p>
					<p>
						<a class="btn btn-secondary" href="#" role="button">View
							details &raquo;</a>
					</p>
				</div>
				<div class="col-md-4">
					<h2>Heading</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
						ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.</p>
					<p>
						<a class="btn btn-secondary" href="#" role="button">View
							details &raquo;</a>
					</p>
				</div>
				<div class="col-md-4">
					<h2>Heading</h2>
					<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis
						in, egestas eget quam. Vestibulum id ligula porta felis euismod
						semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris
						condimentum nibh, ut fermentum massa justo sit amet risus.</p>
					<p>
						<a class="btn btn-secondary" href="#" role="button">View
							details &raquo;</a>
					</p>
				</div>
			</div>

			<hr>

		</div> -->
		<!-- /container -->

	</main>

	<jsp:include page="footer.jsp" />
</body>
</html>