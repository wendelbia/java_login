<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<title>Insert title here</title>
<style type="text/css">

form{
 position: absolute;
 top: 40%;
 left: 33%;
 right: 33%;
}


h5{
 position: absolute;
 top: 30%;
 left: 33%;
}

.msg{
 position: absolute;
 top: 10%;
 left: 33%;
 font-size: 15px;
   color: #664d03;
    background-color: #fff3cd;
    border-color: #ffecb5;

}
</style>
</head>
<body>
<div class="container">

<form action="ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
	<input type="hidden" value="<%= request.getParameter("url") %>" name="url">
	<div class="mb-3">
		<label class="form-label" for="login">Login</label>
		<input class="form-control" id="login" name="login" type="text" placeholder="Login" required>
		<div class="invalid-feedback">
			Obrigatório
		</div>
		<div class="valid-feedback">
			ok
		</div>
	</div>
	<div class="mb-3">
		<label class="form-label" for="Senha">Senha</label>
		<input class="form-control" id="senha" name="senha" type="password" placeholder="Senha" required>
		<div class="invalid-feedback">
			Obrigatório
		</div>
		<div class="valid-feedback">
			ok
		</div>
		<input type="submit" value="Acessar" class="btn btn-primary">
	</div>
	<!-- mensagem depois da execucao do submit -->
	<h5 class="msg">${msg}</h5>
</form>
<script type="text/javascript">
//Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()

</script>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
</html>