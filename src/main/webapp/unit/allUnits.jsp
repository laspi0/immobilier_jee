<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/headerRent.jsp" %>
<div class="container-fluid">
    <h2>Liste des Unités de Location</h2>


    <!-- Formulaire de filtre -->
    <form method="get" action="${pageContext.request.contextPath}/allUnits" class="form-inline mb-3">
        <div class="form-row w-100 align-items-end">
            <div class="col-md-3 mb-3">
                <label for="numberOfRooms">Nombre de Pièces</label>
                <input type="number" id="numberOfRooms" name="numberOfRooms" class="form-control">
            </div>
            <div class="col-md-4 mb-3">
                <label for="address">Adresse</label>
                <select id="address" name="address" class="form-control">
                    <option value="">Sélectionner...</option>
                    <c:forEach var="option" items="${addressOptions}">
                        <option value="${option}">${option}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-4 mb-3">
                <label for="price-range">Plage de prix (FCFA)</label>
                <input type="text" id="price-range" name="price-range" value="" />
                <input type="hidden" id="minPrice" name="minPrice">
                <input type="hidden" id="maxPrice" name="maxPrice">
            </div>
            <div class="col-md-1 mb-3">
                <button type="submit" class="btn btn-primary">Filtrer</button>
            </div>
        </div>
    </form>

    <!-- Tableau des résultats -->
    <!-- Tableau des résultats -->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Numéro d'Unité</th>
            <th>Nombre de Pièces</th>
            <th>Superficie (m²)</th>
            <th>Loyer (FCFA)</th>
            <th>Propriété</th>
            <th>Actions</th>
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
                <td>
                    <a href="${pageContext.request.contextPath}/addLocation?unitId=${unit.id}" class="btn btn-success">Louer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    $(document).ready(function () {
        $("#price-range").ionRangeSlider({
            type: "double",
            min: 0,
            max: 1000000,
            from: 0,
            to: 1000000,
            step: 1000,
            grid: true,
            prefix: "FCFA ",
            onFinish: function (data) {
                $("#minPrice").val(data.from);
                $("#maxPrice").val(data.to);
            }
        });
    });
</script>
<%@ include file="/user/footer.jsp" %>

