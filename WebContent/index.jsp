<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
</head>
<body>
	<form class="form-inline" role="form" action="mvc?logica=EfetuarLoginLogic" method="POST">
		<div class="form-group">
	    	<label for="text">Login:</label>
	    	<input type="text" class="form-control" id="login" name="login">
	  	</div>
	  
	  	<div class="form-group">
	    	<label for="pwd">Senha:</label>
	    	<input type="password" class="form-control" id="pwd" name="senha">
	  	</div>
	  	
		<a href="cadastrarLogin.jsp">Criar conta</a>
		<button type="submit" class="btn btn-default">Logar</button>
	</form>
</body>
</html>