<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Immeuble</title>
</head>
<body>
<h1>Ajouter un Immeuble</h1>
<form action="${pageContext.request.contextPath}/added" method="post">
    <label for="nom">Nom:</label>
    <input type="text" id="nom" name="nom" required><br>

    <label for="adresse">Adresse:</label>
    <input type="text" id="adresse" name="adresse" required><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" required></textarea><br>

    <label>Équipements:</label><br>
    <input type="checkbox" name="equipements" value="Ascenseur"> Ascenseur<br>
    <input type="checkbox" name="equipements" value="Parking"> Parking<br>
    <input type="checkbox" name="equipements" value="Piscine"> Piscine<br>

    <button type="submit">Ajouter</button>
</form>
</body>
</html>
