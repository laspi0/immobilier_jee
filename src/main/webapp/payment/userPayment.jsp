<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/header.jsp" %>

<div class="container">
    <h2>Mes Paiements</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Date de Paiement</th>
            <th>Nombre de Mois</th>
            <th>Montant (FCFA)</th>
            <th>Statut</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="payment" items="${payments}">
            <tr>
                <td>${payment.paymentDate}</td>
                <td>${payment.unit.durationMonths}</td>
                <td>${payment.unit.amount}</td>
                <td>${payment.status}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/user/footer.jsp" %>
