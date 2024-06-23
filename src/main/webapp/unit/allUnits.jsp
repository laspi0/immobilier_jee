<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/headerRent.jsp" %>
<div class="container">
    <h2>Liste des Unités de Location</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Numéro d'Unité</th>
            <th>Nombre de Pièces</th>
            <th>Superficie (m²)</th>
            <th>Loyer (FCFA)</th>
            <th>Propriété</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="unit" items="${units}">
            <tr>
                <td>${unit.id}</td>
                <td>${unit.unitNumber}</td>
                <td>${unit.numberOfRooms}</td>
                <td>${unit.area}</td>
                <td>${unit.rent}</td>
                <td>${unit.property.name} - ${unit.property.address}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/user/footer.jsp" %>
