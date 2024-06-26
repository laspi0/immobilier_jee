<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/headerRent.jsp" %>

<div class="row">
    <div class="col-md-12 my-4">
        <h2 class="h4 mb-1">Mes Paiements</h2>
        <p class="mb-3">detailed information</p>
        <div class="card shadow">
            <div class="card-body">
                <!-- table -->
                <table class="table table-hover table-borderless border-v">
                    <thead class="thead-dark">
                    <tr>
                        <th>Date de Paiement</th>
                        <th>Numéro d'Unité</th>
                        <th>Durée (mois)</th>
                        <th>Montant</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="payment" items="${payments}">
                        <tr class="accordion-toggle collapsed" id="c-${payment[0]}" data-toggle="collapse" data-parent="#c-${payment[0]}" href="#collap-${payment[0]}">
                            <td><c:out value="${payment[0]}"/></td>
                            <td><c:out value="${payment[1]}"/></td>
                            <td><c:out value="${payment[2]}"/></td>
                            <td><c:out value="${payment[3]}"/></td>
                            <td><c:out value="${payment[4]}"/></td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty payments}">
                        <tr>
                            <td colspan="5">Aucun paiement trouvé</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div> <!-- end section -->

<%@ include file="/user/footer.jsp" %>
