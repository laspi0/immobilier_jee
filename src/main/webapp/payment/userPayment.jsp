<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/header.jsp" %>

<div class="container">
    <h2>Mes Paiements</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Date de Paiement</th>
            <th>Numéro d'Unité</th>
            <th>Nombre de Mois</th>
            <th>Montant (FCFA)</th>
            <th>Statut</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty payments}">
                <c:forEach var="payment" items="${payments}">
                    <tr>
                        <td>${payment[0]}</td> <!-- Date de Paiement -->
                        <td>${payment[1]}</td> <!-- Numéro d'Unité -->
                        <td>${payment[2]}</td> <!-- Nombre de Mois -->
                        <td>${payment[3]}</td> <!-- Montant -->
                        <td>${payment[4]}</td> <!-- Statut -->
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="5">Aucun paiement trouvé</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>

<%@ include file="/user/footer.jsp" %>
