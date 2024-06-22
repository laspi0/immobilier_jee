<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/user/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>Nom</th>
                    <th>Addresse</th>
                    <th>Description</th>
                    <th>Equipements</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="property" items="${properties}">
                    <tr>
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
            </table>
        </div>
    </div>
</div>

<%@ include file="/user/footer.jsp" %>





