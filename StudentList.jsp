<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/WebAppExamples.css">
<title>Student List</title>
</head>
<body>
	<h1>Student List</h1>
	<h2>JSP/JSTL + Servlet + DAO</h2>

	<div>
		<h3>List of Student</h3>

		<form action="StudentListServlet" method="GET">
			<input type="submit" value="Show student" />
		</form>
		<br /> <br />
		<%-- Display the student list --%>
		<c:if test="${ studentList != null && studentList.size() > 0 }">
			<table>
				<thead>
					<tr>
						<th>Student ID</th>
						<th>Last Name</th>
						<th>First Name</th>
						<th>Street</th>
						<th>Postcode</th>
						<th>Post Office</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${studentList}" var="studentObject">
						<tr>
							<td><c:out value="${studentObject.id}" /></td>
							<td><c:out value="${studentObject.lastName}" /></td>
							<td><c:out value="${studentObject.firstName}" /></td>
							<td><c:out value="${studentObject.address}" /></td>
							<td><c:out value="${studentObject.postCode}" /></td>
							<td><c:out value="${studentObject.postOffice}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

		<%-- If the list is empty, then show an appropriate message --%>
		<c:if test="${ studentList != null && studentList.size() == 0 }">
			No list available.
		</c:if>
	</div>
</body>
</html>