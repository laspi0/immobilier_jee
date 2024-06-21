<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/headerProperty.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2>Liste des Unités de Location</h2>
            <table class="table table-striped">
                <thead>
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
                    <tr>
                        <td>${unit.unitNumber}</td>
                        <td>${unit.numberOfRooms}</td>
                        <td>${unit.area}</td>
                        <td>${unit.rent}</td>
                        <td>${unit.property.name}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/editUnit?unitId=${unit.id}" class="btn btn-primary">Modifier</a>
                            <form action="${pageContext.request.contextPath}/deleteUnit" method="post" style="display:inline;">
                                <input type="hidden" name="unitId" value="${unit.id}">
                                <button type="submit" class="btn btn-danger">Supprimer</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="/user/footer.jsp" %>
