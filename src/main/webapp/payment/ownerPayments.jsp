<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Owner Payments</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h2>Payments</h2>

<table border="1">
    <thead>
    <tr>
        <th>Payment Date</th>
        <th>Unit Number</th>
        <th>Duration Months</th>
        <th>Amount</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="payment" items="${payments}">
        <tr>
            <td><c:out value="${payment[0]}"/></td>
            <td><c:out value="${payment[1]}"/></td>
            <td><c:out value="${payment[2]}"/></td>
            <td><c:out value="${payment[3]}"/></td>
            <td><c:out value="${payment[4]}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
