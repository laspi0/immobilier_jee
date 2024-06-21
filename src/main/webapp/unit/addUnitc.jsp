<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ajouter une Unité</title>
</head>
<body>
<h1>Ajouter une Unité</h1>
<form action="${pageContext.request.contextPath}/addUnit" method="post">
    <div>
        <label for="unitNumber">Numéro d'unité :</label>
        <input type="text" id="unitNumber" name="unitNumber" required><br>
    </div>
    <div>
        <label for="numberOfRooms">Nombre de pièces :</label>
        <input type="number" id="numberOfRooms" name="numberOfRooms" required><br>
    </div>
    <div>
        <label for="area">Superficie :</label>
        <input type="number" id="area" name="area" required><br>
    </div>
    <div>
        <label for="rent">Loyer :</label>
        <input type="number" id="rent" name="rent" required><br>
    </div>
    <div>
        <label for="propertyId">Propriété :</label>
        <select id="propertyId" name="propertyId">
            <c:forEach var="property" items="${properties}">
                <option value="${property.id}">${property.name}</option>
            </c:forEach>
        </select><br>
    </div>
    <!-- Ajout du champ userId -->
    <input type="hidden" name="userId" value="${sessionScope.user.id}">

    <button type="submit">Ajouter</button>
</form>
</body>
</html>
