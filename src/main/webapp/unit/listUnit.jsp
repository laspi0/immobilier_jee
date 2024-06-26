<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/headerProperty.jsp" %>

<div class="row">
    <div class="col-md-12 my-4">
        <h2 class="h4 mb-1">Liste des Unités de Location</h2>
        <p class="mb-3">detailed information</p>
        <div class="card shadow">
            <div class="card-body">
                <!-- table -->
                <table class="table table-hover table-borderless border-v">
                    <thead class="thead-dark">
                    <tr>
                        <th>Numéro d'Unité</th>
                        <th>Nombre de Pièces</th>
                        <th>Superficie</th>
                        <th>Loyer</th>
                        <th>Propriété</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="unit" items="${units}">
                        <tr class="accordion-toggle collapsed" id="c-${unit.id}" data-toggle="collapse" data-parent="#c-${unit.id}" href="#collap-${unit.id}">
                            <td>${unit.unitNumber}</td>
                            <td>${unit.numberOfRooms}</td>
                            <td>${unit.area}</td>
                            <td>${unit.rent}</td>
                            <td>${unit.property.name}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/editUnit?unitId=${unit.id}" class="btn btn-primary btn-sm">Modifier</a>
                                <form action="${pageContext.request.contextPath}/deleteUnit" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this unit?');">
                                    <input type="hidden" name="unitId" value="${unit.id}">
                                    <button type="submit" class="btn btn-sm btn-danger">Supprimer</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty units}">
                        <tr>
                            <td colspan="6">Aucune unité trouvée</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div> <!-- end section -->

<%@ include file="/user/footer.jsp" %>
