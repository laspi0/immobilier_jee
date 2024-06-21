<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/headerProperty.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-3">
            <div class="card">
                <div class="card-header">Modifier l'Unité de Location</div>

                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/editUnit" method="post">
                        <input type="hidden" name="unitId" value="${unit.id}">
                        <input type="hidden" name="userId" value="${sessionScope.user.id}">

                        <div class="form-group">
                            <label for="unitNumber">Numéro d'Unité</label>
                            <input type="text" id="unitNumber" name="unitNumber" class="form-control" value="${unit.unitNumber}" required>
                        </div>

                        <div class="form-group">
                            <label for="numberOfRooms">Nombre de Pièces</label>
                            <input type="number" id="numberOfRooms" name="numberOfRooms" class="form-control" value="${unit.numberOfRooms}" required>
                        </div>

                        <div class="form-group">
                            <label for="area">Superficie (m²)</label>
                            <input type="number" step="0.01" id="area" name="area" class="form-control" value="${unit.area}" required>
                        </div>

                        <div class="form-group">
                            <label for="rent">Loyer (FCFA)</label>
                            <input type="number" step="0.01" id="rent" name="rent" class="form-control" value="${unit.rent}" required>
                        </div>

                        <div class="form-group">
                            <label for="propertyId">Propriété</label>
                            <select id="propertyId" name="propertyId" class="form-control" required>
                                <option value="">Sélectionner une propriété</option>
                                <c:forEach var="property" items="${properties}">
                                    <option value="${property.id}" ${property.id == unit.property.id ? 'selected' : ''}>${property.name} - ${property.address}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-primary">Modifier</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/user/footer.jsp" %>
