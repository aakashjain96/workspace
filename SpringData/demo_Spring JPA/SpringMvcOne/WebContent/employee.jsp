<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:form action="addemployee" method="post" modelAttribute="my">
<table>
<tr>
<td>ID</td>
<td><c:input path="empId"/></td>
<td><c:errors path="empId"></c:errors>
</tr>

<tr>
<td>Name</td>
<td><c:input path="empName"/></td>
<td><c:errors path="empName"></c:errors>
</tr>

<tr>
<td>Qualification</td>
<td><c:select path="empQualification" items="${myq}"></c:select></td>
</tr>
<tr>

<td><input type="submit" value="Add Employee"></td>
</tr>
</table>

</c:form>
</body>
</html>