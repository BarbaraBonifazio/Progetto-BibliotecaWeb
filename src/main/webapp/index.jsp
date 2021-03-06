<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="it">
	<head>
	  <!-- Required meta tags -->
	  <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	  <!-- Bootstrap CSS -->
	  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" >
	
	  <title>Accedi</title>
	  
	  <!-- Favicons -->
	<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
	<link rel="icon" href="${pageContext.request.contextPath}/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
	<link rel="icon" href="${pageContext.request.contextPath}/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
	<link rel="manifest" href="${pageContext.request.contextPath}/assets/img/favicons/manifest.json">
	<link rel="mask-icon" href="${pageContext.request.contextPath}/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
	<link rel="icon" href="${pageContext.request.contextPath}/assets/img/favicons/favicon.ico">
	<script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
	<meta name="msapplication-config" content="${pageContext.request.contextPath}/assets/img/favicons/browserconfig.xml">
	
	
	<!-- Custom styles for this template -->
	  <link href="${pageContext.request.contextPath}/assets/css/signin.css" rel="stylesheet">	  
	
<!-- 	<script type="text/javascript" src="app.js"></script> -->
	<script type ="text/javascript">
	
		 $(document).ready(function() { 
		  	$("form").submit(function( event ) {
		  		
		  		var valida = true;
		  		
			  	$("#errorUname").hide(); 
			  	$("#errorPwd").hide(); 
			  	
				  	if(!$("#inputUsername")[0].value) { 
					  	$("#errorUname").show();
					  	valida = false;
				  	}	
						  	if(!$("#inputPassword")[0].value){ 
							  	$("#errorPwd").show();
							  	valida = false;
							} 	
							  	if(!valida){ 
							  		event.preventDefault(); 
							  	}
			 });
		})  
		
	  </script>  
	</head>
	
	<body class="text-center">
		
	   	<form class="form-signin" action="LoginServlet" method="post" id="form" novalidate>
	   	
	   	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		 ${errorMessage}
		</div>
		  <img class="mb-4" src="./assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
		  <h1 class="h3 mb-3 font-weight-normal">Effettua l'accesso</h1>
		  <h4 class="h4 mb-3 font-weight-normal">Inserisci le tue credenziali qui di seguito:</h4>
		  <label for="inputUsername" class="sr-only">Username</label>
		  <input type="text" id="inputUsername" name="username" class="form-control" placeholder="Username" required>
		  <div class="invalid-feedback" id="errorUname"> Il campo username risulta vuoto!</div>
		  <br>
		  <label for="inputPassword" class="sr-only">Password</label>
		  <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
		  <div class="invalid-feedback" id="errorPwd"> Il campo password risulta vuoto!</div>
		  <br>
		  <!-- <div class="checkbox mb-3">
		    <label>
		      <input type="checkbox" value="remember-me"> Ricorda credenziali
		    </label> 
		  </div>-->
		  <button class="btn btn-lg btn-primary btn-block" type="submit">Accedi</button>
		  <p class="mt-5 mb-3 text-muted">&copy; 2017-2020</p>
		</form> 
  
	</body>
</html>