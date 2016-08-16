<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action="mvc?logica=EfetuarLoginLogic" method="POST">
		Usuário:<input type="text" name="login"/> <br/>
		Senha:  <input type="password" name="senha"/> <br/>
		<input type="button" value="Entrar"/>
	</form>
</body>
</html>