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
	<meta name="msapplication-config" content="${pageContext.request.contextPath}/assets/img/favicons/browserconfig.xml">
	<script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
	
<!-- 	<script type="text/javascript" src="app.js"></script> -->
	<script type ="text/javascript">
	
		  /* $(document).ready(function() { 
		  	$("myForm").submit(function(event) {
		  
			  	$("#usernameErrato").hide(); 
			  	$("#passErrata").hide(); 
			  	var valida = true;
			  	
				  	if(!$("username")[0].value){ 
					  	$("#usernameErrato").show();
					  	valida = false;
				  	}	
						  	if(!$("password")[0].value){ 
							  	$("#passErrata").show();
							  	valida = false;
							} 	
							  	if(!valida){ 
							  		event.preventDefault(); 
							  	}
			 });
		});  */
		
		function validateForm() {
			
			var ALERT_TITLE = "Oops!";
			var ALERT_BUTTON_TEXT = "Ok";

			if(document.getElementById) {
				window.alert = function(txt) {
					createCustomAlert(txt);
				}
			}

			function createCustomAlert(txt) {
				d = document;

				if(d.getElementById("modalContainer")) return;

				mObj = d.getElementsByTagName("body")[0].appendChild(d.createElement("div"));
				mObj.id = "modalContainer";
				mObj.style.height = d.documentElement.scrollHeight + "px";
				
				alertObj = mObj.appendChild(d.createElement("div"));
				alertObj.id = "alertBox";
				if(d.all && !window.opera) alertObj.style.top = document.documentElement.scrollTop + "px";
				alertObj.style.left = (d.documentElement.scrollWidth - alertObj.offsetWidth)/2 + "px";
				alertObj.style.visiblity="visible";

				h1 = alertObj.appendChild(d.createElement("h1"));
				h1.appendChild(d.createTextNode(ALERT_TITLE));

				msg = alertObj.appendChild(d.createElement("p"));
				//msg.appendChild(d.createTextNode(txt));
				msg.innerHTML = txt;

				btn = alertObj.appendChild(d.createElement("a"));
				btn.id = "closeBtn";
				btn.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
				btn.href = "#";
				btn.focus();
				btn.onclick = function() { removeCustomAlert();return false; }

				alertObj.style.display = "block";
				
			}

			function removeCustomAlert() {
				document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
			}

			
			var ErrMess = "" ;
			  var x = document.forms["myForm"]["username"].value;
			  var y = document.forms["myForm"]["password"].value;
				
			  if(x == 0) {
			   
				  ErrMess = ErrMess+"Il campo username risulta vuoto!\n"+"<br>";
			  }
			  
			  if(y == 0) {
				  
				  ErrMess = ErrMess+"Il campo password risulta vuoto!\n";
			  }  
			  
			  if(ErrMess != "") {
				  alert(ErrMess)
				  event.preventDefault();
				  return;
			  }
			 
		} 
		</script>



	<meta name="theme-color" content="#563d7c">
	
	   <style>
	    .bd-placeholder-img {
	      font-size: 1.125rem;
	      text-anchor: middle;
	      -webkit-user-select: none;
	      -moz-user-select: none;
	      -ms-user-select: none;
	      user-select: none;
	    }
	
	    @media (min-width: 768px) {
	      .bd-placeholder-img-lg {
	        font-size: 3.5rem;
	      }
	    }
	    
	    #modalContainer {
	background-color:rgba(0, 0, 0, 0.3);
	position:absolute;
	width:100%;
	height:100%;
	top:0px;
	left:0px;
	z-index:10000;
	background-image:url(tp.png); /* required by MSIE to prevent actions on lower z-index elements */
}

		#alertBox {
			position:relative;
			width:300px;
			min-height:100px;
			margin-top:50px;
			border:1px solid #563d7c;
			background-color:#fff;
			background-repeat:no-repeat;
			background-position:20px 30px;
		}
		
		#modalContainer > #alertBox {
			position:fixed;
		}
		
		#alertBox h1 {
			margin:0;
			font:bold 0.9em verdana,arial;
			background-color:#563d7c;
			color:#FFF;
			border-bottom:1px solid #000;
			padding:2px 0 2px 5px;
		}
		
		#alertBox p {
			font:0.7em verdana,arial;
			height:60px;
			padding-left:5px;
			margin-top:20px;
			margin-left:20px;
			margin-right:20px;
		}
		
		#alertBox #closeBtn {
			display:block;
			position:relative;
			margin:5px auto;
			padding:7px;
			border:0 none;
			width:70px;
			font:0.7em verdana,arial;
			text-transform:uppercase;
			text-align:center;
			color:#FFF;
			background-color:#563d7c;
			border-radius: 3px;
			text-decoration:none;
		}
		
		/* unrelated styles */
		
		#mContainer {
			position:relative;
			width:600px;
			margin:auto;
			padding:5px;
			border-top:2px solid #563d7c;
			border-bottom:2px solid #563d7c;
			font:0.7em verdana,arial;
		}
		
		h1,h2 {
			margin:0;
			padding:4px;
			font:bold 1.5em verdana;
			border-bottom:1px solid #563d7c;
		}
		
		code {
			font-size:1.2em;
			color:#069;
		}
		
		#credits {
			position:relative;
			margin:25px auto 0px auto;
			width:350px; 
			font:0.7em verdana;
			border-top:1px solid #563d7c;
			border-bottom:1px solid #563d7c;
			height:90px;
			padding-top:4px;
		}
		
		#credits img {
			float:left;
			margin:5px 10px 5px 0px;
			border:1px solid #000000;
			width:80px;
			height:79px;
		}
		
		.important {
			background-color:#F5FCC8;
			padding:2px;
		}
		
		code span {
			color:green;
		}
	    
	    
	  </style>
	  
	  	
	  
	  <!-- Custom styles for this template -->
	  <link href="./assets/css/signin.css" rel="stylesheet">	  
	</head>
	
	<body class="text-center">
		
	   	<form class="form-signin" action="LoginServlet" method="post" name="myForm" onsubmit="return validateForm()">
	   	
	   	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		 ${errorMessage}
		</div>

		<fieldset>
		  <img class="mb-4" src="./assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
		  <h1 class="h3 mb-3 font-weight-normal">Effettua l'accesso</h1>
		  <h4 class="h4 mb-3 font-weight-normal">Inserisci le tue credenziali qui di seguito:</h4>
		  <label for="inputUsername" class="sr-only">Username</label>
		  <input type="text" id="inputUsername" name="username" class="form-control" placeholder="Username">
		  <label for="inputPassword" class="sr-only">Password</label>
		  <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password">
		  <div class="checkbox mb-3">
		    <!-- <label>
		      <input type="checkbox" value="remember-me"> Ricorda credenziali
		    </label> -->
		  </div>
		  <button class="btn btn-lg btn-primary btn-block" type="submit">Accedi</button>
		  <p class="mt-5 mb-3 text-muted">&copy; 2017-2020</p>
		  </fieldset>
		  </form>
		  
	
		  
	</body>
</html>