<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/header.jsp" %>

<div class="container">
    <h2>Liste des Demandes de Location en Attente</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Locataire</th>
            <th>Immeuble</th>
            <th>Adresse</th>
            <th>Début de Location</th>
            <th>Durée (mois)</th>
            <th>Montant (FCFA)</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="locationRequest" items="${locationRequests}">
            <tr>
                <td>${locationRequest[1].firstName} ${locationRequest[1].lastName}</td> <!-- User (locataire) -->
                <td>${locationRequest[2]}</td> <!-- Nom de l'immeuble -->
                <td>${locationRequest[3]}</td> <!-- Adresse de l'immeuble -->
                <td>${locationRequest[0].startDate}</td> <!-- Date de début de location -->
                <td>${locationRequest[0].durationMonths}</td> <!-- Durée en mois -->
                <td>${locationRequest[0].amount}</td> <!-- Montant -->
                <td>
                    <form action="updateLocationStatus" method="post" style="display:inline;">
                        <input type="hidden" name="locationId" value="${locationRequest[0].id}">
                        <button type="submit" name="action" value="accept" class="btn btn-success">Accepter</button>
                    </form>
                    <form action="updateLocationStatus" method="post" style="display:inline;">
                        <input type="hidden" name="locationId" value="${locationRequest[0].id}">
                        <button type="submit" name="action" value="refuse" class="btn btn-danger">Refuser</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/user/footer.jsp" %>
