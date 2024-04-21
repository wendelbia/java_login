<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CURSO JAVA</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">

<style type="text/css">
h5{
position: absolute;
top: 30%;
left: 40%;
}

form{
position: absolute;
top: 40%;
left: 33%;
right: 33%;
}

.msg{
position: absolute;
top: 63%;
left: 40%;
font-size: 15px;
color: #664d03;
background-color: #fff3cd;
border-color: #ffecb5;
}

</style>
</head>
<body>
	<div class="container-fluid">
		<h5>Bem vindo ao curso de JAVA</h5>
		<form action="ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
			<input type="hidden" name="url" value="<%= request.getParameter("url") %>">
			<div class="mb-3">
				<label class="form-label" for="login">Login</label>
				<input class="form-control" id="login" name="login" type="text" required>
				<div class="invalid-feedback">
					Obrigatório
				</div>
				<div class="valid-feedback">
					Ok
				</div>
			</div>
			
			<div class="mb-3">
				<label class="form-label" for="senha">Senha</label>
				<input class="form-control" id="senha" name="senha" type="password" required>
				<div class="invalid-feedback">
					Obrigatório
				</div>
				<div class="valid-feedback">
					Ok
				</div>
			</div>
			<input type="submit" value="Acessar" class="btn btn-primary">
		</form>
		<h5 class="msg">${msg}</h5>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
<script type="text/javascript">
//example starter javaScript for disabling form submissions if there are invalid fields
(function(){
	'use strict'
	//fetch all the forms we want to apply custom Bootstrap validation styles to
	var forms = document.querySelectorAll('.needs-validation')
	//loop over them and prevent submission
	Array.prototype.slice.call(forms)
		.forEach(function(form){
			form.addEventListener('submit', function (event){
				if(!form.checkValidity()){
					event.preventDefault()
					event.stopPropagation()
				}
				form.classList.add('was-validated')
			}, false)
		})
})()
</script>
</body>
</html>