
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
</head>
<body>
<h2>Connexion</h2>
<form action="connexion" method="post">
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <label for="motDePasse">Mot de passe:</label>
    <input type="password" id="motDePasse" name="motDePasse" required><br>

    <button type="submit">Se connecter</button>
</form>

<%-- Gestion des erreurs --%>
</body>
</html>
