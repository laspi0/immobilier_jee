<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Modifier Immeuble</title>
</head>
<body>
<h1>Modifier Immeuble</h1>
<form action="${pageContext.request.contextPath}/modifierImmeuble" method="post">
    <input type="hidden" name="immeubleId" value="${immeuble.id}">
    <label>Nom:</label><br>
    <input type="text" name="nom" value="${immeuble.nom}"><br>
    <label>Adresse:</label><br>
    <input type="text" name="adresse" value="${immeuble.adresse}"><br>
    <label>Description:</label><br>
    <textarea name="description">${immeuble.description}</textarea><br>
    <label>Équipements:</label><br>
    <ul>
        <c:forEach var="equipement" items="${immeuble.equipements}">
            <li><input type="text" name="equipements" value="${equipement}"></li>
        </c:forEach>
    </ul>
    <input type="submit" value="Enregistrer">
</form>
</body>
</html>
