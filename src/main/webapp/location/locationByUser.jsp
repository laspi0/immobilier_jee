<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/headerRent.jsp" %>

<div class="row">
    <div class="col-md-12 my-4">
        <h2 class="h4 mb-1">Liste des Demandes de Location</h2>
        <p class="mb-3">detailed information</p>
        <div class="card shadow">
            <div class="card-body">
                <!-- table -->
                <table class="table table-hover table-borderless border-v">
                    <thead class="thead-dark">
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
                        <tr class="accordion-toggle collapsed" id="c-${locationRequest[0].id}" data-toggle="collapse" data-parent="#c-${locationRequest[0].id}" href="#collap-${locationRequest[0].id}">
                            <td>${locationRequest[1].firstName} ${locationRequest[1].lastName}</td> <!-- User (locataire) -->
                            <td>${locationRequest[2]}</td> <!-- Nom de l'immeuble -->
                            <td>${locationRequest[3]}</td> <!-- Adresse de l'immeuble -->
                            <td>${locationRequest[0].startDate}</td> <!-- Date de début de location -->
                            <td>${locationRequest[0].durationMonths}</td> <!-- Durée en mois -->
                            <td>${locationRequest[0].amount}</td> <!-- Montant -->
                            <td>
                                <c:choose>
                                    <c:when test="${locationRequest[0].status == 'accepte'}">
                                        <span class="badge badge-success">Accepté</span>
                                    </c:when>
                                    <c:when test="${locationRequest[0].status == 'refuse'}">
                                        <span class="badge badge-danger">Refusé</span>
                                    </c:when>
                                    <c:when test="${locationRequest[0].status == 'en attente'}">
                                        <span class="badge badge-warning">En attente</span>
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty locationRequests}">
                        <tr>
                            <td colspan="7">Aucune demande de location trouvée</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div> <!-- end section -->

<%@ include file="/user/footer.jsp" %>
