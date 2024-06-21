<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/headerProperty.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 offset-3">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">Modifier Immeuble</div>
                    <hr>
                    <form action="${pageContext.request.contextPath}/editProperty" method="post">
                        <input type="hidden" name="propertyId" value="${property.id}">
                        <div class="form-group">
                            <label for="input-1">Nom</label>
                            <input type="text" name="name" class="form-control" id="input-1" value="${property.name}" required>
                        </div>
                        <div class="form-group">
                            <label for="input-2">Adresse</label>
                            <input type="text" name="address" class="form-control" id="input-2" value="${property.address}">
                        </div>
                        <div class="form-group">
                            <label for="input-3">Description</label>
                            <textarea name="description" class="form-control" id="input-3" required>${property.description}</textarea>
                        </div>
                        <div class="form-group">
                            <label>Équipements</label>
                            <div id="checklist">
                                <c:set var="possibleEquipments" value="${['Ascenseur', 'Parking', 'Piscine']}"/>
                                <c:forEach var="equipment" items="${possibleEquipments}">
                                    <input type="checkbox" name="equipments" value="${equipment}" <c:if test="${property.equipments.contains(equipment)}">checked</c:if>> ${equipment}<br>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-light px-5"><i class="icon-lock"></i> Enregistrer</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/user/footer.jsp" %>
