<%@ page import="sn.groupeisi.jeeappli.entiies.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Utilisateurs Non-Admin</title>
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
    <h1>Liste des Utilisateurs Non-Admin</h1>
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Email</th>
            <th>Rôle</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Utilisateur> utilisateurs = (List<Utilisateur>) session.getAttribute("utilisateurs");
            if (utilisateurs != null) {
                for (Utilisateur utilisateur : utilisateurs) {
        %>
        <tr>
            <td><%= utilisateur.getId() %></td>
            <td><%= utilisateur.getNom() %></td>
            <td><%= utilisateur.getPrenom() %></td>
            <td><%= utilisateur.getEmail() %></td>
            <td><%= utilisateur.getRole() %></td>
            <td>
                <form action="${pageContext.request.contextPath}/toggleUserStatus" method="post" style="display:inline;">
                    <input type="hidden" name="userId" value="<%= utilisateur.getId() %>">
                    <% if ("actif".equals(utilisateur.getStatus())) { %>
                    <button type="submit">Désactiver</button>
                    <% } else { %>
                    <button type="submit">Activer</button>
                    <% } %>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Aucun utilisateur trouvé</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <form action="${pageContext.request.contextPath}/deconnexion" method="post">
        <button type="submit">Déconnexion</button>
    </form>
</main>

<footer>
    <p>&copy; 2024 Mon Application</p>
</footer>
</body>
</html>
