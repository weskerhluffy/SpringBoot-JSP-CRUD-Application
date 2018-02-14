<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<body>
	<h3>This is default exception page</h3>
	<p>
		Message: <b>${exception.message}</b>
	</p>
	<p>
		Exception: <b>${exception['class'].name}</b>
	</p>
</body>
</html>