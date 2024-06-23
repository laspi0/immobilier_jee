<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/user/headerRent.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 offset-3">
            <div class="card">
                <div class="card-header">Formulaire de Location pour l'Unité ${unit.unitNumber}</div>
                <form method="post" class="col-6 offset-3" action="${pageContext.request.contextPath}/addLocation">
                    <div class="form-group">
                        <label for="durationMonths">Durée de la Location (en mois)</label>
                        <select id="durationMonths" name="durationMonths" class="form-control" required>
                            <option value="">Sélectionner la durée...</option>
                            <c:forEach var="i" begin="1" end="12"> <!-- Boucle de 1 à 12 mois -->
                                <option value="${i}">${i} mois</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="amount">Montant de la Location (FCFA)</label>
                        <input type="number" id="amount" name="amount" class="form-control" required readonly>
                    </div>
                    <div class="form-group">
                        <label for="rent">Loyer Mensuel (FCFA)</label>
                        <input type="number" id="rent" name="rent" class="form-control" value="${unit.rent}" readonly>
                    </div>
                    <input type="hidden" name="unitId" value="${unit.id}">
                    <button type="submit" class="btn btn-primary">Soumettre</button>
                </form>
                <div class="card-body">

                </div>
            </div>
        </div>

</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<script>
    $(document).ready(function() {
        $('#durationMonths').on('change', function() {
            var durationMonths = $(this).val();
            var rentPerMonth = $('#rent').val(); // Récupérez le loyer mensuel depuis l'input

            // Calcul du montant total
            var totalAmount = durationMonths * rentPerMonth;

            // Mettre à jour le champ de montant
            $('#amount').val(totalAmount);
        });
    });
</script>

<%@ include file="/user/footer.jsp" %>
