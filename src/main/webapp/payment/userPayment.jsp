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
            <th>Action</th>
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
                        <td>
                            <c:if test="${payment[4] == 'en attente'}">
                                <form action="${pageContext.request.contextPath}/updatePaymentStatus" method="post">
                                    <input type="hidden" name="paymentId" value="${payment[5]}" />
                                    <button type="submit" class="btn btn-success">Payer</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="6">Aucun paiement trouvé</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>

<%@ include file="/user/footer.jsp" %>
