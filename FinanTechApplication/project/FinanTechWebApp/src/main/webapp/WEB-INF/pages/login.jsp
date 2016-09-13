<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<script type="text/javascript" src="angular.js"></script>
<body>
	<form action="login.htm">
		<div>Login ${msg}</div>
		<div>
			<table>

				<tr>
					<td>Login Id :</td>
					<td><input type="text" name="loginId" id="loginId"></td>
				<tr>
				<tr>
					<td>Password :</td>
					<td><input type="password" name="password" id="password"></td>
				<tr>
			</table>

			<input type="submit" value="login">
		</div>
	</form>
</body>
</html>