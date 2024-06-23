<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/headerRent.jsp" %>

<div class="container">
    <h2>Liste des Demandes de Location</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Locataire</th>
            <th>Immeuble</th>
            <th>Adresse</th>
            <th>Début de Location</th>
            <th>Durée (mois)</th>
            <th>Montant (FCFA)</th>
            <th>Status</th>
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
                <td>${locationRequest[0].status}</td> <!-- Status -->
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/user/footer.jsp" %>
