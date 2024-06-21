<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/headerProperty.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-3">
            <div class="card">
                <div class="card-header">Ajouter une Unité de Location</div>

                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/addUnit" method="post">
                        <div class="form-group">
                            <label for="unitNumber">Numéro d'Unité</label>
                            <input type="text" id="unitNumber" name="unitNumber" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="numberOfRooms">Nombre de Pièces</label>
                            <input type="number" id="numberOfRooms" name="numberOfRooms" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="area">Superficie (m²)</label>
                            <input type="number" step="0.01" id="area" name="area" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="rent">Loyer (FCFA)</label>
                            <input type="number" step="0.01" id="rent" name="rent" class="form-control" required>
                        </div>
                        <input type="hidden" name="userId" value="${sessionScope.user.id}">

                        <div class="form-group">
                            <label for="propertyId">Propriété</label>
                            <select id="propertyId" name="propertyId" class="form-control" required>
                                <option value="">Sélectionner une propriété</option>
                                <c:forEach var="property" items="${properties}">
                                    <option value="${property.id}">${property.name} - ${property.address}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-primary">Ajouter</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/user/footer.jsp" %>


