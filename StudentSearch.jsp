<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Search</title>
</head>
<body>
	<h1>Student Search</h1>
	<h2>JSP/JSTL + Servlet + DAO</h2>

	<div>
		<h3>Search Student</h3>

		<form action="StudentSearchServlet" method="GET">
			<label>Student ID: </label> <input type="text" name="txtID" /> <input
				type="submit" value="Search" />
		</form>

		<%-- Display the student --%>
		<c:if test="${ student != null }">
			<c:out value="${ student.id }" />
			<c:out value="${ student.firstName }" />
			<c:out value="${ student.lastName }" />
		</c:if>

	</div>
</body>
</html>