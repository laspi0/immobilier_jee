<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/header.jsp" %>

<div class="container">
    <h2>Mes Paiements</h2>

    <table class="table table-striped">
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
</div>

<%@ include file="/user/footer.jsp" %>
