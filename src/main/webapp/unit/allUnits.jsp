<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/headerRent.jsp" %>

<div class="row">
    <div class="col-md-12 my-4">
        <h2 class="h4 mb-1">Liste des Unités de Location</h2>
        <p class="mb-3">Informations détaillées</p>
        <div class="card shadow">
            <div class="card-body">
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
                            <div class="price-range-slider">
                                <input type="range" id="minPriceRange" name="minPriceRange" min="0" max="500000" step="1000" value="0">
                                <input type="range" id="maxPriceRange" name="maxPriceRange" min="0" max="500000" step="1000" value="500000">
                            </div>
                            <div class="price-range-values">
                                <span id="minPriceDisplay">0</span> FCFA - <span id="maxPriceDisplay">500,000</span> FCFA
                            </div>
                            <input type="hidden" id="minPrice" name="minPrice" value="0">
                            <input type="hidden" id="maxPrice" name="maxPrice" value="500000">
                        </div>
                        <div class="col-md-1 mb-3">
                            <button type="submit" class="btn btn-primary">Filtrer</button>
                        </div>
                    </div>
                </form>

                <!-- Tableau des résultats -->
                <table class="table table-hover table-borderless border-v">
                    <thead class="thead-dark">
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
                        <tr class="accordion-toggle collapsed" id="c-${unit.id}" data-toggle="collapse" data-parent="#c-${unit.id}" href="#collap-${unit.id}">
                            <td>${unit.id}</td>
                            <td>${unit.unitNumber}</td>
                            <td>${unit.numberOfRooms}</td>
                            <td>${unit.area}</td>
                            <td>${unit.rent}</td>
                            <td>${unit.property.name} - ${unit.property.address}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/addLocation?unitId=${unit.id}" class="btn btn-success btn-sm">Louer</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty units}">
                        <tr>
                            <td colspan="7">Aucune unité trouvée</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div> <!-- end section -->

<script>
    document.addEventListener('DOMContentLoaded', function () {
        var minPriceRange = document.getElementById('minPriceRange');
        var maxPriceRange = document.getElementById('maxPriceRange');
        var minPriceDisplay = document.getElementById('minPriceDisplay');
        var maxPriceDisplay = document.getElementById('maxPriceDisplay');
        var minPrice = document.getElementById('minPrice');
        var maxPrice = document.getElementById('maxPrice');

        // Initialize the displayed values
        minPriceDisplay.textContent = minPriceRange.value;
        maxPriceDisplay.textContent = maxPriceRange.value;

        // Update the hidden inputs and displayed values on input change
        minPriceRange.addEventListener('input', function () {
            minPriceDisplay.textContent = minPriceRange.value;
            minPrice.value = minPriceRange.value;
        });

        maxPriceRange.addEventListener('input', function () {
            maxPriceDisplay.textContent = maxPriceRange.value;
            maxPrice.value = maxPriceRange.value;
        });
    });
</script>

<%@ include file="/user/footer.jsp" %>
