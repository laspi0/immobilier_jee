<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="admin.jsp">Accueil</a></li>
            <!-- Autres liens de navigation pour l'administration -->
        </ul>
    </nav>
</header>

<main>
    <h1>Bienvenue, ${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}</h1>

    <form action="${pageContext.request.contextPath}/deconnexion" method="post">
        <button type="submit">Déconnexion</button>
    </form>

</main>

<footer>
    <p>&copy; 2024 Mon Application</p>
</footer>
</body>
</html>
