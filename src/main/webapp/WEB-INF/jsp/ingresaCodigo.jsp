<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<<jsp:include page="header.jsp"></jsp:include>

<div class="container">

	<h1>${danger}</h1>
	<h3>usuario Registration</h3>
	<form action='/caca/ingresaCodigoo' method='post'>

		<table class='table table-hover table-responsive table-bordered'>

			<tr>
				<td><b>telefono</b></td>
				<td><input type='text' name='telefono' class='form-control'
					value="${usuario.telefono}" /></td>
			</tr>

			<tr>
				<td><b>Surname</b></td>
				<td><input type='text' name='surname' class='form-control'
					value="${usuario.surname}" /></td>
			</tr>

			<tr>
				<td><b>Adress</b></td>
				<td><input type='text' name='adress' class='form-control'
					size="20" value="${usuario.adress}" /></td>

			</tr>

			<tr>
				<td><b>biatch</b></td>
				<td><input type='text' name='shit' class='form-control'
					value="${usuario.userId}" /></td>
			</tr>

			<input type='hidden' id='id' rows='5' class='form-control' name='id'
				value="${usuario.id}" />
			<tr>
				<td></td>
				<td>
					<button type="submit" class="btn btn-primary">Update
						usuario Information</button>
				</td>
			</tr>

		</table>
	</form>


</div>

<jsp:include page="footer.jsp"></jsp:include>