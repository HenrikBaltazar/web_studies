<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tela Restrita!</title>
<style>
body{
	position: fixed;
	top: 50%;
	left: 50%;
	margin-top: -300px;
    margin-left: -230px;
	color: white;
	text-shadow: 2px 2px 4px #000000;
	font-size: 24px;
	text-align: center;
	background-color: #a170a3;
}
</style>
</head>
<body>
<%
String usuario = (String)request.getSession().getAttribute("usuario");
if(request.getSession().getAttribute("usuario") == null){
	request.getRequestDispatcher("/login.html").forward(request, response);
}
%>

<h1>Bem vindo <%= usuario %>!</h1>
<iframe src="https://giphy.com/embed/pynZagVcYxVUk" width="300" height="300"></iframe>
<form action="login" method="GET">
	<input type="submit" value="logout">
</form>
</body>
</html>