<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>

<%@ include file="/user/header.jsp" %>

<div class="row">
    <div class="col-md-12 my-4">
        <h2 class="h4 mb-1">Listes des propriétés</h2>
        <p class="mb-3">detailed information</p>
        <div class="card shadow">
            <div class="card-body">
                <!-- table -->
                <table class="table table-hover table-borderless border-v">
                    <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Adresse</th>
                        <th>Description</th>
                        <th>Equipements</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="property" items="${properties}">
                        <tr class="accordion-toggle collapsed" id="c-3954" data-toggle="collapse" data-parent="#c-3954" href="#collap-3954">
                            <td>${property.id}</td>
                            <td>${property.name}</td>
                            <td>${property.address}</td>
                            <td>${property.description}</td>
                            <td>
                                <ul>
                                    <c:forEach var="equipment" items="${property.equipments}">
                                        <li>${equipment}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/deleteProperty" method="post" onsubmit="return confirm('Are you sure you want to delete this property?');">
                                    <input type="hidden" name="propertyId" value="${property.id}">
                                    <button class="btn btn-sm btn-danger" type="submit">Supprimer</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty properties}">
                        <tr>
                            <td colspan="7">Aucune propriété trouvée</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div> <!-- end section -->

<%@ include file="/user/footer.jsp" %>
