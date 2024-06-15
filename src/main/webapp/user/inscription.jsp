<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inscription</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h2>Formulaire d'inscription</h2>

<form action="inscription" method="post">
    <label for="nom">Nom:</label>
    <input type="text" id="nom" name="nom" required><br><br>

    <label for="prenom">Prénom:</label>
    <input type="text" id="prenom" name="prenom" required><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <label for="motDePasse">Mot de passe:</label>
    <input type="password" id="motDePasse" name="motDePasse" required><br><br>

    <label for="role">Rôle:</label>
    <select id="role" name="role" required>
        <option value="admin">Admin</option>
        <option value="locataire">Locataire</option>
        <option value="proprietaire">Propriétaire</option>
    </select><br><br>

    <input type="submit" value="S'inscrire">
</form>

<%
    // Affichage du message d'erreur en cas de problème d'inscription
    String erreur = request.getParameter("erreur");
    if ("1".equals(erreur)) {
%>
<p style="color: red;">Une erreur est survenue lors de l'inscription. Veuillez réessayer.</p>
<%
    }
%>
</body>
</html>
